import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Adrs {
		int x, y; char c; boolean f;
		public Adrs(int x, int y, char c, boolean f) {
			this.x = x;
			this.y = y;
			this.c = c;
			this.f = f;
		}
	}
	static class RB {
		Adrs r, b; int cnt;
		public RB(Main.Adrs r, Main.Adrs b, int cnt) {
			this.r = r;
			this.b = b;
			this.cnt = cnt;
		}
	}
	static int N, M;
	static char[][] map;
	static boolean[][][][] visited;
	static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 맵의 세로 N
		M = Integer.parseInt(st.nextToken()); // 맵의 가로 M
		
		map = new char[N][M];
		visited = new boolean[N + 1][M + 1][N + 1][M + 1]; // [rx][ry][bx][by]
		
		Adrs r = null, b = null, o = null;
		
		for(int i = 0; i < N; i++) {
			String input = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = input.charAt(j);
				if(map[i][j] == '#') continue;
				if(map[i][j] == 'R') r = new Adrs(i, j, 'R', false);
				if(map[i][j] == 'B') b = new Adrs(i, j, 'B', false);
				if(map[i][j] == 'O') o = new Adrs(i, j, 'O', false);
				map[i][j] = '.';
			}
		} // 맵 정보 입력 완료
		
		int result = startGame(r, b, o); // 탈출 가능하면 1, 불가능하면 0이 저장된다.
		
		System.out.println(result);
	}

	private static int startGame(Adrs r, Adrs b, Adrs o) {
		Queue<RB> queue = new LinkedList<>();
		queue.add(new RB(r, b, 0));
		visited[r.x][r.y][b.x][b.y] = true; // 초기값 설정 완료
		
		while(!queue.isEmpty()) { // 큐가 비거나 count가 10 이하일 때만 수행
			RB curr = queue.poll();

			if(curr.b.f || curr.cnt > 10) continue; // 파란 구슬이 탈출하는 경우에는 패스
			if(curr.r.f) return 1; // 빨간 구슬이 탈출하는 경우에는 리턴
			
			for(int i = 0; i < 4; i++) {
				RB moved = moveReady(curr.r, curr.b, o, i); // 이동을 시키자.
				Adrs tr = moved.r;
				Adrs tb = moved.b;
				// 이미 같은 위치에 존재했던 적이 있거나, 파란 구슬이 탈출 가능한 경우는 패스
				if(visited[tr.x][tr.y][tb.x][tb.y] || tb.f) continue;
				
				visited[tr.x][tr.y][tb.x][tb.y] = true;
				queue.add(new RB(tr, tb, curr.cnt + 1));
			}
		}
		
		return 0; // 큐가 끝나도 탈출할 수 없으면 0을 출력
	}

	private static RB moveReady(Adrs r, Adrs b, Adrs o, int d) {
		// 상 x가 작은 것이 먼저 움직인다.  하 x가 큰 것이 먼저 움직인다.
		// 좌 y가 작은 것이 먼저 움직인다.  우 y가 큰 것이 먼저 움직인다.
		Adrs[][] XY = {
				{(r.x < b.x) ? r : b, (r.x < b.x) ? b : r}, {(r.x > b.x) ? r : b, (r.x > b.x) ? b : r},
				{(r.y < b.y) ? r : b, (r.y < b.y) ? b : r}, {(r.y > b.y) ? r : b, (r.y > b.y) ? b : r}
		};
		
		RB moved = move(XY[d][0], XY[d][1], o, d);
		
		return moved;
	}

	private static RB move(Adrs fm, Adrs sm, Adrs o, int d) {
		int fx = fm.x;
		int fy = fm.y;
		boolean ff = fm.f;
		while(map[fx + dr[d]][fy + dc[d]] == '.') { // 앞이 길일 때만 수행
			fx += dr[d];
			fy += dc[d];
			
			if(fx == o.x && fy == o.y) { // 구멍을 만나면 N과 M에 저장
				fx = N; fy = M;
				ff = true;
				break;
			}
		}

		int sx = sm.x;
		int sy = sm.y;
		boolean sf = sm.f;
		while(map[sx + dr[d]][sy + dc[d]] == '.') {
			if(sx + dr[d] == fx && sy + dc[d] == fy) break; // 다음 칸이 구슬이 곂치는 자리면 패스
			
			sx += dr[d];
			sy += dc[d];
			
			if(sx == o.x && sy == o.y) { // 구멍을 만나면 N과 M에 저장
				sx = N; sy = M;
				sf = true;
				break;
			}
		}
		
		Adrs r = (fm.c == 'R') ? new Adrs(fx, fy, 'R', ff) : new Adrs(sx, sy, 'R', sf);
		Adrs b = (fm.c != 'R') ? new Adrs(fx, fy, 'B', ff) : new Adrs(sx, sy, 'B', sf);
		
		return new RB(r, b, 0);
	}
}