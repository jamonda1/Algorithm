import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 최대한 많은 코어를 연결해야 한다. 그리고 그때의 최소 전선 수를 출력해라
 */
public class Solution {
	
	static int N, cnt, max, min;
	static char[][] map = new char[12][12];
	static int[] x = new int[12]; // 코어의 x좌표
	static int[] y = new int[12]; // 코어의 y좌표
	static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int TC = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= TC; tc++) {
			cnt = 0; max = 0; min = 9999; // 케이스 시작 전 초기화
			
			N = Integer.parseInt(br.readLine()); // 맵의 크기 N
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = st.nextToken().charAt(0);
					if((0 < i && i < N-1) && (0 < j && j < N-1) && map[i][j] == '1') {
						x[cnt] = i; y[cnt++] = j;
					} // 벽에 안 붙은 코어들 좌표 저장
				}
			} // 맵 입력 완료

			dfs(0, 0, 0);
			
			sb.append("#").append(tc).append(" ").append(min).append("\n");
			
		} // 전체 테스트 케이스 종료
		System.out.println(sb);
	}
	
	private static void dfs(int idx, int core, int line) { // 전선 가능? -> 전선 놓기 -> 재귀
		// cnt-idx = 남은 연결 가능 코어 수, + core(연결한 코어)의 합이 기존의 max보다 작으면 패스
		if((cnt-idx+core) < max) return;
		
		if(idx == cnt) { // 개수가 같으면 전선 비교
			if(core == max) min = Math.min(min, line);
			if(core > max) { // 더 많이 연결했으면 업데이트
				max = core;
				min = line;
			}
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			if(safe(i, x[idx], y[idx])) {
				int c = setLine(i, x[idx], y[idx], '*'); // 안전하면 전선깔기
				dfs(idx + 1, core + 1, line + c);
				setLine(i, x[idx], y[idx], '0'); // 원복
			}
		}
		dfs(idx + 1, core, line); // 전선 안 깔고 넘어가기
	}
	
	private static boolean safe(int d, int x, int y) { // 전선 깔 수 있는지 확인
		while(map[x + dr[d]][y + dc[d]] != '1' && map[x + dr[d]][y + dc[d]] != '*') {
			x += dr[d]; y += dc[d];
			
			// 쭉쭉 가다가 벽에 닿으면 연결 가능
			if(x == 0 || x == N-1 || y == 0 || y == N-1) return true;
		}
		// 반복문 돌다가 1이나 *을 만나면 탈출해서 false 반환
		return false;
	}
	
	private static int setLine(int d, int x, int y, char v) { // v값에 따라 전선 깔거나 원복
		int c = 0;
		while(0 <= x+dr[d] && x+dr[d] < N && 0 <= y+dc[d] && y+dc[d] < N) {
			x += dr[d]; y += dc[d];
			map[x][y] = v;
			c++;
		}
		
		return c; // 전선 몇 개 깔았는지 카운트해서 리턴
	}
}