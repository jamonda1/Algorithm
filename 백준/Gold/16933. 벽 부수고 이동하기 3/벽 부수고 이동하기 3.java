import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 벽 부수고 이동하기 2와 비슷하다. 하지만 이번에는 낮과 밤이 바뀐다.
 * 가장 처음에는 낮이다. 한 번 이동할 때마다 낮 -> 밤, 밤 -> 낮으로 바뀐다.
 * 벽은 낮에만 부술 수 있다.
 * 그리고 움직이지 않고, 가만히 있는 것도 가능하다.
 */
public class Main {
	
	static class Move {
		int k, x, y, count;
		boolean day;
		public Move(int k, int x, int y, int count, boolean day) {
			this.k = k;
			this.x = x;
			this.y = y;
			this.count = count;
			this.day = day;
		}
	}
	static int N, M, K, result = 0;
	static boolean day = true; // true = 낮, false = 밤
	static char[][] map;
	static boolean[][][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 맵의 세로 N
		M = Integer.parseInt(st.nextToken()); // 맵의 가로 M
		K = Integer.parseInt(st.nextToken()); // 부술 수 있는 최대 횟수 K
		
		map = new char[N][M];
		visited = new boolean[K + 1][N][M];
		
		for(int i = 0; i < N; i++) {
			String input = br.readLine();
			map[i] = input.toCharArray();
		} // 맵 정보 입력 완료
		
		System.out.println(bfs() ? result : -1); // bfs에서 false가 오면 불가능하다는 뜻
	}

	private static boolean bfs() {
		int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
		Queue<Move> queue = new LinkedList<>();
		queue.add(new Move(0, 0, 0, 1, true)); // k, x, y, count, day 순
		
		while(!queue.isEmpty()) {
			Move curr = queue.poll();
			int k = curr.k;
			int x = curr.x;
			int y = curr.y;
			int cnt = curr.count;
			boolean day = curr.day;
			
			if(x == N - 1 && y == M - 1) {
				result = cnt;
				return true;
			}
			
			for(int i = 0; i < 4; i++) {
				int tx = x + dr[i];
				int ty = y + dc[i];
				
				if(tx < 0 || N <= tx || ty < 0 || M <= ty) continue;
				if(map[tx][ty] == '0' && !visited[k][tx][ty]) { // 다음이 길일 때
					visited[k][tx][ty] = true;
					// day는 직전의 것과 반대되는 것을 넣어야 한다.
					queue.add(new Move(k, tx, ty, cnt + 1, day ? false : true));
					
				} else if(map[tx][ty] == '1' && k + 1 <= K && !visited[k + 1][tx][ty]) { // 다음이 길이 아닌데, 부술 수 있을 때
					if(day) { // 낮이면 벽 부수고 이동
						visited[k + 1][tx][ty] = true; // 이동할 때만 true로 해줘야 한다.
						queue.add(new Move(k + 1, tx, ty, cnt + 1, false));
						
					} else { // 밤이면 기다렸다가 다음에 부수고 이동
						queue.add(new Move(k, x, y, cnt + 1, true));
					}
				}
			}
		}
		
		return false;
	}
}