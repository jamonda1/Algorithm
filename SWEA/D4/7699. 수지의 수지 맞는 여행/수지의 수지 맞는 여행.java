import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
/*
 * 수지는 이번에 새로운 섬에 도착했다.
 * 섬의 각 칸에는 알파벳이 적혀있다. 이 알파벳은 섬의 명물이고, 같은 알파벳은 같은 명물이다.
 * 
 * 이 섬에서 명물을 볼 때마다 요금을 지급해야 하는데, 각 알파벳을 처음 볼 때는 무료로 볼 수 있다.
 * 그래서 명물은 최대한 많이 보되, 요금은 지불하지 않는 최대 개수를 구해라
 */
public class Solution {
	
	static int R, C, result;
	static char[][] map;
	static boolean[] visited;
	static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			result = 1;
			
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken()); // 섬의 세로 R
			C = Integer.parseInt(st.nextToken()); // 섬의 가로 C
			
			map = new char[R][C];
			visited = new boolean[26];
			
			for(int i = 0; i < R; i++) {
				String input = br.readLine();
				for(int j = 0; j < C; j++) {
					map[i][j] = input.charAt(j);
				}
			} // 맵 정보 입력 완료
			
			visited[map[0][0] - 'A'] = true; // 초기 위치는 항상 true
			dfs(0, 0, 1);
			
			bw.write("#" + t + " " + result + "\n");
			
		} // 전체 테스트 케이스 종료
		bw.flush();
		bw.close();
	}
	
	static void dfs(int x, int y, int count) {
		for(int i = 0; i < 4; i++) {
			int tx = x + dr[i];
			int ty = y + dc[i];
			
			if(tx < 0 || R <=  tx || ty < 0 || C <= ty) continue;
			if(visited[map[tx][ty] - 'A']) continue;
			
			visited[map[tx][ty] - 'A'] = true;
			result = Math.max(result, count + 1);
			dfs(tx, ty, count + 1);
			visited[map[tx][ty] - 'A'] = false;
		}
	}
}