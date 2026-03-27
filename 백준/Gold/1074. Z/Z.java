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
	static int[] dr = {0, 0, 1, 1}, dc = {0, 1, 0, 1};
	
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
		
		devide(0, 0, size);
	}
	
	static void devide(int sx, int sy, int size) {
		if(size == 2) { // 재귀 타고 들어와서 size = 2가 되면 탐색
			for(int i = 0; i < 4; i++) {
				if(sx + dr[i] == R && sy + dc[i] == C) {
					System.out.println(count);
					f = true; // 목표를 찾았으면 출력
					return;
				} count++;
			} // 탐색 종료
			return;
		}
		
		int ns = size / 2;
		int[] nx = {sx, sx, sx + ns, sx + ns}; // 다음 재귀에서의 x 범위
		int[] ny = {sy, sy + ns, sy, sy + ns}; // 다음 재귀에서의 y 범위
		
		for(int i = 0; i < 4; i++) {
			if(nx[i] <= R && R < nx[i] + ns && ny[i] <= C && C < ny[i] + ns) {
				devide(nx[i], ny[i], size / 2); // 1번
			} else if(!f) count += ns * ns;
		}
	}
}