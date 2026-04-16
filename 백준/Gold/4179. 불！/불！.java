import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 벽은 지나갈 수 없다.
 * 불은 사방으로 퍼지고, 지훈이도 사방으로 이동한다.
 * 범위 밖으로 지훈이가 나갈 수 있게 되면 탈출!
 */
public class Main {
	
	static int R, C, jx = 0, jy = 0, result = 0; // 지훈이와 불의 초기 위치 저장
	static List<int[]> fire = new ArrayList<>();
	static char[][] map;
	static boolean[][] visited, Jvisited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken()); // 가로 R
		C = Integer.parseInt(st.nextToken()); // 세로 C
		
		map = new char[R][C];
		visited = new boolean[R][C];
		Jvisited = new boolean[R][C];
		
		for(int i = 0; i < R; i++) {
			String input = br.readLine();
			for(int j = 0; j < C; j++) {
				map[i][j] = input.charAt(j);
				if(map[i][j] != '.') {
					visited[i][j] = true;
					Jvisited[i][j] = true;
				}
				if(map[i][j] == 'J') {
					jx = i; jy = j;
				}
				if(map[i][j] == 'F') { // 불은 1개가 아닐 수도 있다.
					fire.add(new int[] {i, j});
				}
			}
		} // 정보 입력 완료
		
		bfs();
		System.out.println(result > 0 ? result : "IMPOSSIBLE");
	}

	private static void bfs() {
		Queue<int[]> Jqueue = new LinkedList<>();
		Queue<int[]> Fqueue = new LinkedList<>();
		
		Jqueue.add(new int[] {jx, jy, 0}); // 지훈이의 초기 위치 담기
		for(int[] f : fire) {
			Fqueue.add(new int[] {f[0], f[1]}); // 불의 초기 위치 담기			
		}
		
		while(!Jqueue.isEmpty()) {
			int Fsize = Fqueue.size();
			
			for(int i = 0; i < Fsize; i++) {
				int[] Fcurr = Fqueue.poll();
				for(int j = 0; j < 4; j++) {
					int tx = Fcurr[0] + dr[j];
					int ty = Fcurr[1] + dc[j];
					
					if(tx < 0 || R <= tx || ty < 0 || C <= ty) continue;
					if(visited[tx][ty]) continue;
					
					visited[tx][ty] = true;
					Fqueue.add(new int[] {tx, ty});
				}
			}
			
			// 퍼지고 남은 곳으로 지훈이가 이동해야 한다.
			int Jsize = Jqueue.size();
			
			for(int i = 0; i < Jsize; i++) {
				int[] Jcurr = Jqueue.poll();
				
				for(int j = 0; j < 4; j++) {
					int tx = Jcurr[0] + dr[j];
					int ty = Jcurr[1] + dc[j];
					
					if(tx < 0 || R <= tx || ty < 0 || C <= ty) { // 다음 차례에 맵 밖으로 갈 수 있다면?
						result = Jcurr[2] + 1;
						return;
					}
					if(visited[tx][ty] || map[tx][ty] == '#' || Jvisited[tx][ty]) continue;
					
					Jvisited[tx][ty] = true;
					Jqueue.add(new int[] {tx, ty, Jcurr[2] + 1});
				}
			}
		}
	}
}