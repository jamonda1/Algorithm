import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	
	static int N, X, result;
	static int[][] map;
	static int[] dr = {0, 1}, dc = {1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			result = 0;
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 맵의 크기 N
			X = Integer.parseInt(st.nextToken()); // 경사로의 길이 X
			
			map = new int[N][N];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			} // 맵 정보 입력 완료
			
			for(int i = 0; i < N; i++) {
				if(checkForward(i, 0, 0)) result++;
				if(checkForward(0, i, 1)) result++;
			}
			
			bw.write("#" + t + " " + result + "\n");
			
		} // 전체 테스트 케이스 종료
		bw.flush();
		bw.close();
	}
	
	static boolean checkForward(int x, int y, int dir) { // x가 0이면 가로 체크, y가 0이면 세로 체크
		int pivot = map[x][y];
//		System.out.println(x + " " + y + " 피벗");
		
		int tx = x, ty = y;
		int count = 1;
		for(int i = 0; i < N; i++) {
			tx += dr[dir]; // dir이 1이면 x만 증가
			ty += dc[dir]; // dir이 0이면 y만 증가
			
			if(N <= tx || N <= ty) continue;
			
			if(map[tx][ty] == pivot) { // 앞이 pivot과 같으면 평평
				count++;
				continue;
			} else if(map[tx][ty] == pivot + 1 && count >= X) { // 나보다 1칸 높고 경사로 가능?
				pivot = map[tx][ty];
				count = 1;
				continue;
			} else if(map[tx][ty] == pivot - 1) { // 나보다 한 칸 낮다면?
				if(check(tx, ty, dir)) { // 앞에 여유 있으면?
					if(dir == 0) ty += (dc[dir] * X) - 1;
					if(dir == 1) tx += (dr[dir] * X) - 1;
					pivot = map[tx][ty];
					count = 0;
					continue;
				}
			}
			
			return false; // 평평도 아니고, 경사로도 안 되면 활주로 불가능
		}
		
		return true; // 반복문이 무사히 종료 되면 활주로 가능
	}

	private static boolean check(int x, int y, int dir) { // X 만큼만 앞으로 가보자
		int pivot = map[x][y];

		int tx = x, ty = y;
		int count = 1;
		if(X == 1) return true;
		
		for(int i = 0; i <= X; i++) {
			tx += dr[dir]; // dir이 1이면 x만 증가
			ty += dc[dir]; // dir이 0이면 y만 증가

			if(N <= tx || N <= ty) continue;
			if(map[tx][ty] != pivot) return false;
			
			if(map[tx][ty] == pivot) count++;
			if(count == X) return true;
		}
		
		return false;
	}
}