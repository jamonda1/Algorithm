import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 사과를 먹으면 뱀의 길이가 길어진다. 벽이나 자기 자신과 부딪히면 게임이 끝난다.
 * 초기 뱀의 길이는 1, (0, 0) 좌표에서 오른쪽을 보고 있다.
 * 
 * 1. 뱀은 몸 길이를 늘려서 머리를 다음 칸으로 보낸다.
 * 2. 벽이나 자기 자신과 부딪히면 종료 시킨다.
 * 3. 이동한 칸에 사과가 있다면, 그 칸에 있던 사과가 없어지고, 꼬리는 움직이지 않는다.
 * 4. 사과가 없으면 꼬리를 당긴다.
 */
public class Main {
	static class Order {
		int X;
		char C;
		
		public Order(int x, char c) {
			X = x;
			C = c;
		}
	}
	
	static int N, time = 0, dir = 3;
	static boolean exit = false;
	static int[][] map;
	static boolean[][] apple;
	static int[] dr = {-1, 0, 1, 0}, dc = {0, -1, 0, 1};
	static Deque<int[]> dq = new ArrayDeque<>();
	static Queue<Order> queue = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); // 보드의 크기 N
		int K = Integer.parseInt(br.readLine()); // 사과의 개수 K
		
		map = new int[N][N];
		apple = new boolean[N][N];
		
		map[0][0] = 1; // 뱀의 초기 위치에 1
		dq.add(new int[] {0, 0});
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			apple[r-1][c-1] = true;
		} // 맵 정보 입력 완료
		
		
		int L = Integer.parseInt(br.readLine()); // 방향 변환 횟수 L
		for(int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			char C = st.nextToken().charAt(0);
			
			queue.add(new Order(X, C));
		}
		move2();
		
		System.out.println(time);
	}
	
	private static void move2() {
		Order order = queue.poll();
		
		for(;;) {
			boolean f = false;
			time++;
			
			int[] head = dq.peekFirst();
			int tx = head[0] + dr[dir]; // 다음 머리의 위치
			int ty = head[1] + dc[dir]; // 다음 머리의 위치
			
			if(tx < 0 || N <= tx || ty < 0 || N <= ty) { // 벽에 부딪히면?
				return;
			}
			if(map[tx][ty] == 1) { // 앞을 봤는데 몸이면?
				return;
			}
			if(apple[tx][ty]) { // 앞을 봤는데 사과면?
				f = true;
				apple[tx][ty] = false; // 사과 먹었으니깐 다시 false
			}
			
			map[tx][ty] = 1;
			dq.addFirst(new int[] {tx, ty}); // 머리 값 변경 후 다시 집어넣기
			
			if(!f) { // 사과를 먹은 게 아닐 때만 당겨주자
				int[] tail = dq.pollLast();
				map[tail[0]][tail[1]] = 0;
			} // ---- 뱀 직진 완료 ----- //
			
			if(order != null && time == order.X) {
				changeDir(order.C);
				order = queue.poll();
			} // 뱀 방향 전환 완료
		}
	}
	
	private static void changeDir(char c) {
		if(c == 'L') { // 왼쪽
			if(dir == 3) dir = 0;
			else dir++;
		}
		if(c == 'D') { // 오른쪽
			if(dir == 0) dir = 3;
			else dir--;
		}
	}
}