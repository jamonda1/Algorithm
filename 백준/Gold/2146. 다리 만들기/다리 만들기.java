import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 가장 짧은 다리를 하나 놓아라
 */
public class Main {
	
	static int N, idx = 0;
	static char[][] map;
	static List<List<int[]>> list;
	static boolean[][] visited;
	static Queue<int[]> queue = new LinkedList<>();
	static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); // 맵의 크기 N
		
		map = new char[N][N];
		visited = new boolean[N][N];
		list = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = st.nextToken().charAt(0);
			}
		} // 맵 정보 입력 완료
		
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!visited[i][j] && map[i][j] == '1') {
					list.add(new ArrayList<>());
					bfs(i, j);
					idx++;
				}
			}
		}
		
		int min = 1000;
		first : for(int i = 0; i < list.size() - 1; i++) {
			for(int j = 0; j < list.get(i).size(); j++) {
				int[] currJ = list.get(i).get(j);
				for(int k = i + 1; k < list.size(); k++) {
					for(int l = 0; l < list.get(k).size(); l++) {
						int[] currL = list.get(k).get(l);
						int len = Math.abs(currJ[0] - currL[0]) + Math.abs(currJ[1] - currL[1]);
						min = Math.min(min, len);
						if(min == 1) break first;
					}
				}
			}
		}
		
		System.out.println(min - 1);
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
				
				if(tx < 0 || N <= tx || ty < 0 || N <= ty) continue;
				if(visited[tx][ty]) continue;
				if(map[tx][ty] == '0') {
					list.get(idx).add(new int[] {x, y});
					continue;
				} // 탐색하다 주변에 0이 있으면 해당 좌표를 리스트에 추가
				
				visited[tx][ty] = true;
				queue.add(new int[] {tx, ty});
			}
		}
	}
}