import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int[] r = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] c = {-1, 0, 1, -1, 1, -1, 0, 1};
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 하나의 사각형과 8방으로 연결된 곳은 걸어갈 수 있는 사각형이다.
		for(;;) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken()); // 지도의 너비
			int h = Integer.parseInt(st.nextToken()); // 지도의 높이
			
			if(w == 0 && h == 0) break; // 입력이 0 0이면 종료
			
			int[][] map = new int[h][w];
			visited = new boolean[h][w]; // 해당 좌표는 방문한 곳인가?
			
			for(int i = 0; i < h; i++) { // 지도에 정보 입력
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int island = 0;	// 섬이 몇 개 있는가?
			for(int i = 0; i < h; i++) {
				for(int j = 0; j < w; j++) {
					if(map[i][j] == 1 && !visited[i][j]) {
						island++; // 처음 방문하는 1이면 dfs 호출
						dfs(i, j, map, w, h);
					}
				}
			}
			
			bw.write(island + "\n");
		}
		bw.flush();
		bw.close();
	}
	
	static void dfs(int x, int y, int[][] map, int w, int h) {
		if(map[x][y] == 0) return;
		visited[x][y] = true;
		
		// 8방 탐색을 해서 건너편에 1이 있고, false면 건너가서 트루
		// 그리고 그쪽에서 다시 8방 탐색
		for(int i = 0; i < 8; i++) {
			if((0 <= x + r[i] && x + r[i] < h) && (0 <= y + c[i] && y + c[i] < w)) {
				if(!visited[x + r[i]][y + c[i]]) {
					dfs(x + r[i], y + c[i], map, w, h);
				}
			}
		}
	}
}