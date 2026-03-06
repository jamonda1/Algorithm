import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, result;
	static char[][] map;
	static boolean[][] visited;
	static Queue<int[]> queue = new LinkedList<>();
	static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			result = 0;
			
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken()); // 밭의 가로 길이 M
			N = Integer.parseInt(st.nextToken()); // 밭의 세로 길이 N
			int K = Integer.parseInt(st.nextToken()); // 배추 위치의 개수 K
			
			map = new char[N][M];
			visited = new boolean[N][M];
			
			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				map[y][x] = '1';
			} // 배추 입력 완료
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(!visited[i][j] && map[i][j] == '1') {
						bfs(i, j);
						result++;
					}
				}
			}
			
			bw.write(result + "\n");
			
		} // 전체 테스트 케이스 종료
		bw.flush();
		bw.close();
	}

	private static void bfs(int i, int j) {
		visited[i][j] = true;
		queue.add(new int[] {i, j});
		
		while(!queue.isEmpty()) {
			int[] curr = queue.poll();
			int x = curr[0];
			int y = curr[1];
			
			for(int k = 0; k < 4; k++) {
				int tx = x + dr[k];
				int ty = y + dc[k];
				
				if(tx < 0 || N <= tx || ty < 0 || M <= ty || visited[tx][ty]) continue;
				if(map[tx][ty] != '1') continue;
				
				visited[tx][ty] = true;
				queue.add(new int[] {tx, ty});
			}
		}
	}
}
