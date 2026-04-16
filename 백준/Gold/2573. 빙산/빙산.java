import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, year = 0;
	static int[][] map;
	static boolean[][] visited, visited2;
	static Queue<int[]> queue = new LinkedList<>();
	static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 행의 개수 N
		M = Integer.parseInt(st.nextToken()); // 열의 개수 M
		
		map = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 맵 정보 입력 완료
			
		for(;;) {
			year++; // 한 번 순회하면 1년 추가
			bfs();
		}
	}

	private static void bfs() {
		visited = new boolean[N][M];
		visited[0][0] = true;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(!visited[i][j] && map[i][j] == 0) {
					visited[i][j] = true;
					for(int k = 0; k < 4; k++) {
						int tx = i + dr[k];
						int ty = j + dc[k];
						
						if(tx < 0 || N <= tx || ty < 0 || M <= ty) continue;
						if(map[tx][ty] > 0) {
							visited[tx][ty] = true;
							map[tx][ty]--;
						}
					}
				} // 방문 안 한 0이면 주변을 탐색해서 0이 아닌 것을 감소
			}
		}
		
		// 빙하를 한 차례 녹인 다음에 카운트하자
		visited2 = new boolean[N][M];
		int count = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(!visited2[i][j] && map[i][j] > 0) {
					areaCount(i, j);
					count++;
				} // 덩어리가 1개라도 있으면 탐색을 돈다.
			}
		}
		
		if(count >= 2) { // 덩어리가 2개 이상이면 바로 종료
			System.out.println(year);
			System.exit(0);
		} else if(count == 0) { // 탐색 다 돌았는데 덩어리가 0개면 종료
			System.out.println(0);
			System.exit(0);
		}
	}

	private static void areaCount(int x, int y) {
		queue.add(new int[] {x, y});
		visited2[x][y] = true;
		
		while(!queue.isEmpty()) {
			int[] curr = queue.poll();
			
			for(int i = 0; i < 4; i++) {
				int tx = curr[0] + dr[i];
				int ty = curr[1] + dc[i];
				
				if(tx < 0 || N <= tx || ty < 0 || M <= ty) continue;
				if(visited2[tx][ty] || map[tx][ty] == 0) continue;
				
				visited2[tx][ty] = true;
				queue.add(new int[] {tx, ty});
			}
		}
	}
}