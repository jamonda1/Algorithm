import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] map;
	static boolean f = false;
	static int[] dr = {-1, 0, 1, 1}, dc = {1, 1, 1, 0};
	static int[] br = {1, 0, -1, -1}, bc = {-1, -1, -1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		map = new int[19][19];
		
		for(int i = 0; i < 19; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 19; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 맵 정보 입력 완료
		
		for(int i = 0; i < 19; i++) {
			for(int j = 0; j < 19; j++) {
				// 1 또는 2이면서 오목 체크까지 true면 출력 후 종료
				if(map[j][i] == 1 || map[j][i] == 2) {
					for(int k = 0; k < 4; k++) { // 주변에 이어지는 돌이 있는가?
						int tx = j + dr[k];
						int ty = i + dc[k];
						
						if(tx < 0 || 19 <= tx || ty < 0 || 19 <= ty) continue;
						if(map[j][i] == map[tx][ty]) {
							omokCheck(map[j][i], j, i, k);
							if(f) {
								System.out.println(map[j][i]);
								System.out.println((j + 1) + " " + (i + 1));
								return;
							}
						}
					}
				}
			}
		}
		System.out.println(0);
	}

	private static void omokCheck(int pivot, int x, int y, int d) {
		// 바로 뒤가 같은 거면 안 된다.
		if(0 <= x + br[d] && x + br[d] < 19 && 0 <= y + bc[d] && y + bc[d] < 19) {
			if(map[x + br[d]][y + bc[d]] == pivot) return;
		}
		
		int tx = x, ty = y, count = 1;
		for(int i = 0; i < 6; i++) {
			tx += dr[d]; // 한 방향으로 쭉쭉
			ty += dc[d];
			
			if(tx < 0 || 19 <= tx || ty < 0 || 19 <= ty) break;
			if(map[tx][ty] != pivot) break;
			if(map[tx][ty] == pivot) count++;
		}
		
		if(count == 5) f = true;
	}
}