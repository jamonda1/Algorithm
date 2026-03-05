import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * N*N 크기의 땅이 있다. 각 땅에는 나라가 하나씩 존재한다. (r, c)에는 A[r][c] 명이 살고 있다.
 * 인접국 사이에는 국경선이 존재한다. 오늘부터 인구 이동이 시작된다.
 * 인구 이동은 하루 동안 다음과 같이 진행되고, 더 이상 이동이 없을 때까지 지속된다.
 * 
 *  1. 국경선을 공유하는 두 나라의 인구 차이가 L 이상 R 이하라면, 국경을 하루 연다.
 *  2. 위 조건에 의해 열려야 하는 곳들이 모두 열리면 인구 이동 시작
 *  3. 국경이 열려서 인접한 칸만을 통해 이동 가능하면 해당 나라는 하루 동안 연합이다.
 *  4. 연합을 이루는 각 칸의 인구는 (연합의 인구) / (연합을 이루는 칸의 개수)가 된다.
 *  5. 연합 해제 후 모든 국경선을 닫는다.
 *  
 * 각 나라의 인구수가 주어질 때 모두 며칠 동안 이동이 발생하는가?
 */
public class Main {
	
	static int N, L, R;
	static int[][] map;
	static boolean[][] visited;
	static Queue<int[]> queue = new LinkedList<>();
	static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 땅의 크기 N
		L = Integer.parseInt(st.nextToken()); // 차이의 최솟값 L
		R = Integer.parseInt(st.nextToken()); // 차이의 최댓값 R
		
		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 맵 정보 입력 완료
		
		// 연합 찾기 -> 인구 이동 -> 연합 찾기 -> 인구 이동 -> 반복문 종료 -> 날짜 카운트
		int day = 0;
		for(;;) {
			boolean f = false; // 이게 반복문을 다 돌았는데도 그대로 f면 종료
			
			visited = new boolean[N][N];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					 if(!visited[i][j]) {
						 boolean tempF = check(i, j); // 주변 판단
						
						 if(tempF) {
							 f = true;
							 bfs(i, j); // 변동이 필요하면 호출
						 }
					 }
				}
			}
			if(f) day++; // 변동이 있을 때만 날짜 증가
			if(!f) break; // 인구 변동이 없으면 탈출
		}
		
		System.out.println(day);
	}

	private static boolean check(int r, int c) {
		for(int i = 0; i < 4; i++) { // 4방 탐색으로 주변에 연합할 곳이 있는지 확인
			int x = r + dr[i];
			int y = c + dc[i];
			
			if(x < 0 || N <= x || y < 0 || N <= y) continue;
			int temp = Math.abs(map[x][y] - map[r][c]);
			if(temp < L || R < temp || visited[x][y])continue;
			
			return true;
		}
		// 여기까지 왔으면 연합할 곳이 없다는 뜻
		return false;
	}

	private static void bfs(int r, int c) {
		boolean[][] tVisited = new boolean[N][N];
		tVisited[r][c] = true; // 이 값을 인구이동에 써야 한다.
		visited[r][c] = true;
		queue.add(new int[] {r, c});
		
		int sum = map[r][c];
		int count = 1;
		while(!queue.isEmpty()) {
			int[] curr = queue.poll();
			int x = curr[0];
			int y = curr[1];
			
			for(int i = 0; i < 4; i++) {
				int tx = x + dr[i];
				int ty = y + dc[i];
				
				if(tx < 0 || N <= tx || ty < 0 || N <= ty) continue;
				// 다음 나라의 인구 차이가 조건에 부합하지 않으면 패스
				int temp = Math.abs(map[x][y] - map[tx][ty]);
				if(temp < L || R < temp || visited[tx][ty])continue;
				
				// 연합 인구 더하기, 연합 개수 증가
				sum += map[tx][ty]; count++;
				visited[tx][ty] = true;
				tVisited[tx][ty] = true;
				queue.add(new int[] {tx, ty});
			}
		} // 이제 visited가 true인 곳은 모두 연합이다.
		
		int people = sum / count;
		move(people, tVisited); // 전체 합에서 개수만큼 나눠서 넘겨주기
	}

	private static void move(int people, boolean[][] f) { // 인구 변경
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(f[i][j]) map[i][j] = people;
			}
		}
	}
}