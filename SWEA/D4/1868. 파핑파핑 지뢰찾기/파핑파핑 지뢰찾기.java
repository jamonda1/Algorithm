import java.io.*;

public class Solution {
	
	static int N;
	static char[][] map = new char[301][301];
	static boolean[][] visited;
	static int[] xq = new int[300 * 300];
	static int[] yq = new int[300 * 300];
	static int[] dr = {-1, 1, 0, 0, -1, -1, 1, 1}, dc = {0, 0, -1, 1, -1, 1, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= TC; tc++) {
			
			N = Integer.parseInt(br.readLine()); // 맵의 크기 N
			
			for(int i = 0; i < N; i++) {
				String input = br.readLine();
				for(int j = 0; j < N; j++) {
					map[i][j] = input.charAt(j);
				}
			} // 지뢰 정보 입력 완료
			
			visited = new boolean[N][N];
			
			int result = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(!visited[i][j] && map[i][j] == '.' && mineCheck(i, j)) {
						bfs(i, j); // 주변에 지뢰가 하나도 없으면 연쇄 클릭 시작
						result++;
					}
				}
			}
			
			for(int i = 0; i < N; i++) { // 연쇄클릭 후에 남은 방문 안 된 .들 카운트
				for(int j = 0; j < N; j++) {
					if(map[i][j] == '.' && !visited[i][j]) result++;
				}
			}
			
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.print(sb);
	}
	
	static void bfs(int sx, int sy) {
		visited[sx][sy] = true;
		
		int h = 0, t = 0;
		xq[t] = sx;
		yq[t++] = sy;
		
		while(h != t) {
			int x = xq[h];
			int y = yq[h++];
			
			for(int i = 0; i < 8; i++) { // 8방 탐색
				int tx = x + dr[i];
				int ty = y + dc[i];
				
				if(checkAround(tx, ty)) continue;
				visited[tx][ty] = true; // 범위 안이면 방문처리
				
				if(!mineCheck(tx, ty)) continue; // 주변에 지뢰가 있으면 큐x
				xq[t] = tx; // 주변에 지뢰가 없는 것만 큐에 다시 넣기
				yq[t++] = ty;
			}
		}
	}
	
	static boolean mineCheck(int x, int y) { // (x, y)의 8방에 지뢰가 있으면 false, 없으면 true
		for(int i = 0; i < 8; i++) {
			int tx = x + dr[i];
			int ty = y + dc[i];
			if(checkAround(tx, ty)) continue;
			if(map[tx][ty] == '*') return false;
		}
		return true;
	}
	
	static boolean checkAround(int x, int y) { // 범위와 방문 확인 체크 메서드
		return (x < 0 || N <= x || y < 0 || N <= y || visited[x][y]);
	}
}