import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 공기에 노출된 치즈의 4변 중 2변 이상이 공기와 접촉해야 녹는다.
 * 치즈가 모두 녹아 없어지기까지의 시간을 구하여라
 */
public class Main {
	
	static int N, M, result = 0, cheese = 0;
	static char[][] map;
	static boolean[][] visited, check;
	static Queue<int[]> queue = new LinkedList<>();
	static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 맵의 세로 N
		M = Integer.parseInt(st.nextToken()); // 맵의 가로 M
		
		map = new char[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = st.nextToken().charAt(0);
				if(map[i][j] == '1') cheese++;
			}
		} // 치즈 정보 입력 완료
		
		while(cheese > 0) {
			bfs();
			result++;
		}
		
		System.out.println(result);
	}

	private static void bfs() {
		visited = new boolean[N][M]; // 방문 배열
		check = new boolean[N][M]; // 치즈 녹이기 배열
		
		queue.add(new int[] {0, 0}); // 초기 위치
		visited[0][0] = true;
		
		while(!queue.isEmpty()) {
			int[] curr = queue.poll();
			
			for(int i = 0; i < 4; i++) {
				int tx = curr[0] + dr[i];
				int ty = curr[1] + dc[i];
				// 범위를 벗어나면 패스
				if(tx < 0 || N <= tx || ty < 0 || M <= ty) continue;
				
				if(map[tx][ty] == '1') { // 치즈를 찾았다
					if(!check[tx][ty]) { // 처음 보는 치즈다
						check[tx][ty] = true;
					} else { // 이미 1번 노렸던 치즈다
						map[tx][ty] = '0';
						cheese--;
						visited[tx][ty] = true;
					}
					continue;
				}
				if(visited[tx][ty]) continue;
				
				visited[tx][ty] = true;
				queue.add(new int[] {tx, ty});
			}
		}
	}
}