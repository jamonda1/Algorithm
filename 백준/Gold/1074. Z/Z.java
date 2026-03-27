import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * N * N의 맵이 있다.
 * 정해진 방법으로 순회한다고 할 때 (r, c)를 몇 번째로 방문하는가??
 */
public class Main {
	
	static int R, C, count = 0;
	static boolean f = false;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 맵 크기
		R = Integer.parseInt(st.nextToken()); // 도달해야 하는 R
		C = Integer.parseInt(st.nextToken()); // 도달해야 하는 C
		
		if(R == 0 && C == 0) {
			System.out.println(0); return;
		}
		
		int size = (int) Math.pow(2, N);
		
		devide(0, 0, size, size);
	}
	
	static void devide(int sx, int sy, int ex, int ey) {
		if(ex - sx == 2) {
			for(int i = sx; i < ex; i++) {
				for(int j = sy; j < ey; j++) {
					if(i == R && j == C) {
						System.out.println(count);
						f = true;
						return;
					} count++;
				}
			} // 탐색 종료
			return;
		}
		
		int tx = (sx + ex) / 2;
		int ty = (sy + ey) / 2;
		
		// R이랑 C가 범위 안에 있는지 확인
		// 범위 안에 있으면 재귀 들어가기, 범위 안에 없으면 ex - sx * 2를 더해주기
		if(sx <= R && R < tx && sy <= C && C < ty) {
			devide(sx, sy, tx, ty); // 1번
		} else if(!f) count += (tx - sx) * (tx - sx);
		
		if(sx <= R && R < tx && ty <= C && C < ey) {
			devide(sx, ty, tx, ey); // 2번
		} else if(!f) count += (tx - sx) * (tx - sx);
		
		if(tx <= R && R < ex && sy <= C && C < ty) {
			devide(tx, sy, ex, ty); // 3번
		} else if(!f) count += (ex - tx) * (ex - tx);
		
		if(tx <= R && R < ex && ty <= C && C < ey) {
			devide(tx, ty, ex, ey); // 4번
		} else if(!f) count += (ex - tx) * (ex - tx);
	}
}