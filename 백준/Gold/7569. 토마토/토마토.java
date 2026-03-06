import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 창고에는 익은 것도 있지만, 안 익은 토마토도 있다.
 * 보관 후 하루 뒤에는 익은 토마토의 인접한 곳에 있는 토마토는 익게 된다.
 * 
 * 0은 안 익은 토마토, 1은 익은 토마토, -1은 빈 칸이다. 모든 토마토가 있는 최소 일수를 구해라
 */
public class Main {
	
	static int N, M, H, tomato = 0, result = Integer.MAX_VALUE;
	static int[][][] box;
	static boolean[][][] visited;
	static int[] dr = {-1, 1, 0, 0, 0, 0}, dc = {0, 0, -1, 1, 0, 0}, ud = {0, 0, 0, 0, -1, 1};
	static Queue<int[]> queue = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken()); // 가로 M
		N = Integer.parseInt(st.nextToken()); // 세로 N
		H = Integer.parseInt(st.nextToken()); // 높이 H
		
		box = new int[H][N][M];
		visited = new boolean[H][N][M];
		
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k = 0; k < M; k++) {
					box[i][j][k] = Integer.parseInt(st.nextToken());
					if(box[i][j][k] == 1) queue.add(new int[] {i, j, k, 0});
					if(box[i][j][k] == 0) tomato++; // 안 익은 토마토의 수
				}
			}
		}
		
		bfs();
		
		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
	}
	
	static void bfs() {
		int count = 0, time = 0;
		while(!queue.isEmpty()) {
			int size = queue.size();

			for(int i = 0; i < size; i++) {
				int[] curr = queue.poll();
				int h = curr[0];
				int r = curr[1];
				int c = curr[2];
				time = curr[3];
				
				for(int j = 0; j < 6; j++) {
					int th = h + ud[j];
					int tx = r + dr[j];
					int ty = c + dc[j];
					
					if(tx < 0 || N <= tx || ty < 0 || M <= ty || th < 0 || H <= th) continue;
					
					if(box[th][tx][ty] == 0) {
						count++;
						box[th][tx][ty] = 1;
						queue.add(new int[] {th, tx, ty, time + 1});
					}
				}
			}
		}
		
		if(count == tomato) {
			result = Math.min(result, time);
		}
	}
}
