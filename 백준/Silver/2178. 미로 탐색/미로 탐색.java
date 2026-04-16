import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// 1은 이동할 수 있는 칸, 0은 벽
		// (1, 1)에서 출발하여, (N, M)의 위치까지 이동하는 최소 거리
		// 칸을 셀 때는 시작 위치도 포함이다!
		// 1만 따라가자~
		int[] r = {-1, 1, 0, 0};
		int[] c = {0, 0, -1, 1};
		
		Queue<int[]> queue = new LinkedList<>();
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 맵의 세로 N
		int M = Integer.parseInt(st.nextToken()); // 맵의 가로 M
		
		int[][] map = new int[N][M];
		boolean[][] visited = new boolean[N][M];
		
		for(int i = 0; i < N; i++) {
			String input = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = input.charAt(j) - 48;
			}
		} // 맵 입력 완료
		
		// 0, 0에서 시작해서 4방 탐색 시작
		// 맵 안에 있고, 1이면 전진
		
		queue.add(new int[] {0, 0, 1}); // 초기 위치인 (0, 0)
		visited[0][0] = true; // 내가 있는 위치는 방문 완료
		
		int count = 0; // 내가 있는 위치도 카운트하니깐 1부터 시작
		first : while(!queue.isEmpty()) {
			int[] current = queue.poll();
			count = current[2];
			
			for(int i = 0; i < 4; i++) {
				int X = current[0] + r[i];
				int Y = current[1] + c[i];
				
				// 범위를 벗어나면 안 된다.
				if(X < 0 || N <= X || Y < 0 || M <= Y) continue;
				// 범위 안에 있는데 0이거나 이미 방문했으면 안 된다.
				if(map[X][Y] == 0 || visited[X][Y]) continue;
				
				if(X == N-1 && Y == M-1) {
					count = current[2] + 1;
					break first;
				}
				
				visited[X][Y] = true;
				queue.add(new int[] {X, Y, current[2] + 1});
			}
		}
		System.out.println(count);
	}
}