import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;
/*
 * cctv를 설치해서 사각지대를 최소한으로 하자
 */
public class Main {
	
	static int N, M, result = 100;
	static int[][] map;
	static List<int[]> cctv = new ArrayList<>();
	static Stack<int[]> stack = new Stack<>(); // 중복된 곳 저장 (지울 때 필요)
	static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 세로의 크기 N
		M = Integer.parseInt(st.nextToken()); // 가로의 크기 M
		
		map = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] != 0 && map[i][j] != 6) cctv.add(new int[] {i, j, map[i][j]});
			}
		} // 맵 입력과 cctv 종류, 좌표 저장
		
		setCCTV(0);
		
		System.out.println(result);
	}

	private static void setCCTV(int idx) {
		if(idx == cctv.size()) {
			// 여기서 0을 카운트해서 최솟값만 result에 저장하자
			int count = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(map[i][j] == 0) count++;
				}
			}

			result = Math.min(result, count);
			
			return;
		}

		for(int d = 0; d < 4; d++) { // 방향 조절
			int[] curr = cctv.get(idx); // 지금 인덱스의 cctv 정보 가져오기
			
			// 선을 깔다가 #이 나오면 cnt가 늘어난다.
			int cnt = setLine(curr, d, 9); // 방향과 지금 cctv의 정보를 넘기자.
			setCCTV(idx + 1);
			
			// cnt에 넘어온 만큼 팝하면서 해당 부분 #으로 채우기
			setLine(curr, d, 0); // 선 다시 지우기
			
			for(int i = 0; i < cnt; i++) { // 중복된 부분 원복
				int[] temp = stack.pop();
				map[temp[0]][temp[1]] = 9;
			}
		}
	}

	private static int setLine(int[] curr, int d, int c) {
		int x = curr[0];
		int y = curr[1];
		int num = curr[2];
		
		int stackCnt = 0;
		switch(num) {
		case 1 : // 한 번만 깔자
			stackCnt = setting(x, y, d, c);
			break;
		case 2 : // 대칭으로 깔자
			for(int i = 0; i < 2; i++) {
				stackCnt += setting(x, y, d, c);
				d += (d > 1) ? -2 : 2;
			}
			break;
		case 3 : // 직각으로 깔자
			for(int i = 0; i < 2; i++) {
				stackCnt += setting(x, y, d, c);
				d += (d == 3) ? -3 : 1;
			}
			break;
		case 4 : // 3방향으로 깔자
			for(int i = 0; i < 3; i++) {
				stackCnt += setting(x, y, d, c);
				d += (d == 3) ? -3 : 1;
			}
			break;
		case 5 : // 십자로 깔자
			for(int i = 0; i < 4; i++) {
				stackCnt += setting(x, y, d, c);
				d += (d == 3) ? -3 : 1;
			}
			break;
		}

		return stackCnt;
	}
	
	private static int setting(int x, int y, int d, int c) {
		int stackCnt = 0;

		int tx = x, ty = y;
		for(;;) { // 한 쪽 방향으로만 넣기
			tx += dr[d];
			ty += dc[d];
			
			if(tx < 0 || N <= tx || ty < 0 || M <= ty || map[tx][ty] == 6) break;
			if(1 <= map[tx][ty] && map[tx][ty] <= 5) continue;
			
			if(c == 9 && map[tx][ty] == 9) {
				stack.add(new int[] {tx, ty});
				stackCnt++;
			}
			map[tx][ty] = c;
		}
		return stackCnt;
	}
}