import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Node {
		// x, y, 물건 종류, 개수, 시간
		int x, y, item, c, w;
		public Node(int x, int y, int item, int c, int w) {
			this.x = x;
			this.y = y;
			this.item = item;
			this.c = c;
			this.w = w;
		}
	
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 집의 가로 길이 N
		int M = Integer.parseInt(st.nextToken()); // 집의 세로 길이 M
		
		char[][] map = new char[M][N];
		int sx = 0, sy = 0;
		
		int Xcount = 1;
		for(int i = 0; i < M; i++) {
			String input = br.readLine();
			for(int j = 0; j < N; j++) {
				map[i][j] = input.charAt(j);
				
				if(map[i][j] == 'S') {
					map[i][j] = '.';
					sx = i; sy = j;
				} else if(map[i][j] == 'X') { // 물건 종류를 번호로 변경
					String s = Xcount++ + "";
					map[i][j] = s.charAt(0);
				}
			}
		} // Xcount는 종류 + 1개가 되어 있다.
		
		int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
		boolean[][][] visited = new boolean[1 << Xcount][M][N];
		Queue<Node> queue = new LinkedList<>();
		
		queue.add(new Node(sx, sy, 0, 0, 0));
		visited[0][sx][sy] = true;
		
		// x랑 y가 E이고 물건을 집은 개수가 Xcount-1과 같으면 도착한 것이다
		String s = Xcount + "";
		char xc = s.charAt(0);
		while(!queue.isEmpty()) { // 탐색 시작
			Node curr = queue.poll();
			int x = curr.x;
			int y = curr.y;
			int c = curr.c;
			int w = curr.w;
			
			if(c == Xcount - 1 && map[x][y] == 'E') { // 종료 조건
				System.out.println(w); return;
			}
			
			for(int i = 0; i < 4; i++) {
				int tx = x + dr[i];
				int ty = y + dc[i];
				int tc = c;
				int ti = curr.item;

				if(tx < 0 || M <= tx || ty < 0 || N <= ty || map[tx][ty] == '#') continue;
				if('1' <= map[tx][ty] && map[tx][ty] < xc) {
					if((ti & 1 << (map[tx][ty] - '0')) == 0) {
						ti = (ti | 1 << (map[tx][ty] - '0'));
						tc += 1;
					}
				}
				
				if(visited[ti][tx][ty]) continue;
				visited[ti][tx][ty] = true;
				queue.add(new Node(tx, ty, ti, tc, w + 1));
			}
		}
	}
}