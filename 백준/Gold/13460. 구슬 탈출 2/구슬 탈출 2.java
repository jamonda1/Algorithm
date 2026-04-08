import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * N*M 보드 안에 빨간 구슬과 파란 구슬이 하나씩 있다. 그 다음 빨간 구슬을 구멍을 통해 빼내야 한다.
 * 테두리는 모두 막혀있고, 구멍은 단 하나만 있다. 이때 파란 구슬은 절대로 구멍에 들어가면 안 된다.
 * 하나의 움직임에 동시에 들어가는 것도 안 된다.
 * 최소 몇 번 만에 빨간 구슬이 탈출할 수 있을까??
 */
public class Main {
    
    static class Adrs {
        int x, y;
        public Adrs(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static class Move {
        int x, y; boolean f;
        public Move(int x, int y, boolean f) {
            this.x = x;
            this.y = y;
            this.f = f;
        }
    }
    static int N, M, hollx, holly;
    static int result = Integer.MAX_VALUE;
    static char[][] map;
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken()); // 보드의 세로 N
        M = Integer.parseInt(st.nextToken()); // 보드의 가로 M
        
        map = new char[N][M];
        
        Adrs r = null, b = null;
        for(int i = 0; i < N; i++) {
            String input = br.readLine();
            for(int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j);
                // 빨간 구슬의 시작 좌표
                if(map[i][j] == 'R') r = new Adrs(i, j);
                // 파란 구슬의 시작 좌표
                if(map[i][j] == 'B') b = new Adrs(i, j);
                // 구멍의 위치 확인
                if(map[i][j] == 'O') {
                	map[i][j] = '.';
                	hollx = i; holly = j;
                }
            }
        } // 보드 정보 입력 완료
        
        startGame(0, r, b, false, false);
        
        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }
    
    static void startGame(int cnt, Adrs r, Adrs b, boolean rf, boolean bf) {
        if(cnt > 10 || bf) return;
        if(rf) { // 빨간 구슬만 true일 때!!
            result = Math.min(result, cnt);
            return;
        }
        
        // 위로 기울일 때(x) -> 빨 파 중 x값이 더 작은 걸 먼저 움직인다. y가 다르면 x 곂치기 가능
        // 아래로 기울일 때 -> x값이 더 큰 걸 먼저 움직인다. y가 다르면 x 곂치기 가능
        Adrs[][] X = {{(r.x < b.x) ? r : b, (r.x < b.x) ? b : r}, // x가 더 작은 게 앞
  			  		  {(r.x > b.x) ? r : b, (r.x > b.x) ? b : r}};// x가 더 큰 게 앞
        for(int i = 0; i < 2; i++) {
        	// 먼저 움직일 구슬이 R인가?
        	boolean c = (map[X[i][0].x][X[i][0].y] == 'R') ? true : false;
        	
        	Move fm = move(X[i][0], i); // 먼저 움직인 구슬
        	Move sm = move(X[i][1], i); // 다음에 움직인 구슬
        	
        	Adrs nr = c ? new Adrs(fm.x, fm.y) : new Adrs(sm.x, sm.y);
        	Adrs nb = c ? new Adrs(sm.x, sm.y) : new Adrs(fm.x, fm.y);
        	
        	if(r.x == nr.x && r.y == nr.y && b.x == nb.x && b.y == nb.y) {
                // 움직였더니 제자리라면 다음 경우의 수는 보지 않는다.
                map[nr.x][nr.y] = '.'; map[nb.x][nb.y] = '.';
                map[r.x][r.y] = 'R'; map[b.x][b.y] = 'B';
                continue;
            }
        	
        	startGame(cnt + 1, nr, nb, (c ? fm.f : sm.f), (c ? sm.f : fm.f));
        	map[nr.x][nr.y] = '.'; map[nb.x][nb.y] = '.';
        	map[r.x][r.y] = 'R'; map[b.x][b.y] = 'B'; // 원상복구
        }
        
        // 왼쪽으로 기울일 때(y) -> 빨 파 중 y값이 더 작은 걸 먼저 움직인다. x가 다르면 y 곂치기 가능
        // 오른쪽으로 기울일 때 -> y값이 더 큰 걸 먼저 움직인다. x가 다르면 y 곂치기 가능
        Adrs[][] Y = {{(r.y < b.y) ? r : b, (r.y < b.y) ? b : r},
        		      {(r.y > b.y) ? r : b, (r.y > b.y) ? b : r}};
        for(int i = 0; i < 2; i++) {
        	boolean c = (map[Y[i][0].x][Y[i][0].y] == 'R') ? true : false;
        	
        	Move fm = move(Y[i][0], i + 2);
        	Move sm = move(Y[i][1], i + 2);
        	
        	Adrs nr = c ? new Adrs(fm.x, fm.y) : new Adrs(sm.x, sm.y);
        	Adrs nb = c ? new Adrs(sm.x, sm.y) : new Adrs(fm.x, fm.y);
        	
        	if(r.x == nr.x && r.y == nr.y && b.x == nb.x && b.y == nb.y) {
                // 움직였더니 제자리라면 다음 경우의 수는 보지 않는다.
                map[nr.x][nr.y] = '.'; map[nb.x][nb.y] = '.';
                map[r.x][r.y] = 'R'; map[b.x][b.y] = 'B';
                continue;
            }
        	
        	startGame(cnt + 1, nr, nb, (c ? fm.f : sm.f), (c ? sm.f : fm.f));
        	map[nr.x][nr.y] = '.'; map[nb.x][nb.y] = '.';
        	map[r.x][r.y] = 'R'; map[b.x][b.y] = 'B'; // 원상복구
        }
    }

	private static Move move(Adrs ball, int dir) {		
		int x = ball.x;
		int y = ball.y;
		char RB = map[x][y]; // 기존에 어떤 구슬이 있었는가?
		map[x][y] = '.'; // 구슬의 위치 지워주기

		boolean f = false;
		while(map[x + dr[dir]][y + dc[dir]] == '.') {
			x += dr[dir];
			y += dc[dir];
			
			if(x == hollx && y == holly) {
				RB = '.';
				f = true;
				break;
			}
		}
		map[x][y] = RB;
		
		return new Move(x, y, f);
	}
}