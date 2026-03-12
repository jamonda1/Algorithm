import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 원숭이는 말의 동작을 K번만 따라할 수 있다.
 * 중간에 1이 나오면 장애물이고, 해당 방향으로는 갈 수 없다.
 * 말의 움직임, 원숭이의 사방 움직임, 두 움직임 모두 1회로 가정한다.
 * (0, 0)에서 (H-1, W-1)까지의 최소 움직임 횟수를 구해라
 * 불가능하면 -1
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
		int[] hr = {-2, -2, -1, -1, 1, 1, 2, 2}, hc = {-1, 1, -2, 2, -2, 2, -1, 1};
		
		int K = Integer.parseInt(br.readLine()); // 말처럼 움직일 수 있는 횟수 K
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int W = Integer.parseInt(st.nextToken()); // 맵의 가로 길이 W
		int H = Integer.parseInt(st.nextToken()); // 맵의 세로 길이 H
		
		char[][] map = new char[H][W];
		boolean[][][] visited = new boolean[K+1][H][W];
		
		for(int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < W; j++) {
				map[i][j] = st.nextToken().charAt(0);
			}
		} // 맵 정보 입력 완료
		
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {0, 0, 0, K}); // x, y, cnt, 말 이동 횟수
		visited[K][0][0] = true; // [1][][] 부분은 말 이동하면 체크
		
		int result = Integer.MAX_VALUE;
		while(!queue.isEmpty()) {
			int[] curr = queue.poll();
			int x = curr[0];
			int y = curr[1];
			int cnt = curr[2];
			int k = curr[3];
			
			if(cnt > result) continue;
			if(x == H - 1 && y == W - 1) {
				System.out.println(cnt);
				return;
			}
			
			for(int i = 0; i < 4; i++) { // 4방으로 이동할 때
				int tx = x + dr[i];
				int ty = y + dc[i];
				int tcnt = cnt + 1;
				
				if(tx < 0 || H <= tx || ty < 0 || W <= ty || map[tx][ty] == '1') continue;
				if(visited[k][tx][ty] || tcnt > result) continue;
				
				visited[k][tx][ty] = true;
				queue.add(new int[] {tx, ty, tcnt, k});
			}
			
			if(k > 0) { // 찬스가 있으면 말처럼 이동했을 때
				for(int i = 0; i < 8; i++) {
					int tx = x + hr[i];
					int ty = y + hc[i];
					int tcnt = cnt + 1;
					
					if(tx < 0 || H <= tx || ty < 0 || W <= ty || map[tx][ty] == '1') continue;
					if(visited[k-1][tx][ty] || tcnt > result) continue;
					
					visited[k-1][tx][ty] = true;
					queue.add(new int[] {tx, ty, tcnt, k - 1});
				}
			}
		}
		
		System.out.println(-1);
	}
}