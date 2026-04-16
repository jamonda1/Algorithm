import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int[] num;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		num = new int[N];
		
		dfs(N, M, 0, 1);
		
		bw.flush();
		bw.close();
	}

	private static void dfs(int n, int m, int depth, int start) throws IOException {
		if(depth == m) {
			for(int i = 0; i < m; i++) {
				bw.write(num[i] + " ");
			}
			bw.write("\n");
			return;
		}
		
		for(int i = start; i <= n; i++) {
			num[depth] = i;
			dfs(n, m, depth + 1, i); // 4 2 입력 가정
			// i부터 start하도록 해야 한다. 초기값은 1일 때 메서드(1) 에서 i=1로 담고 i=1을 재귀(2)
			// i=1이므로 1을 담고 재귀(3), 재귀(3)은 depth==m이므로 출력 후 재귀(2)로 리턴
			// 루프가 돌면서 i=2가 된다..... 메서드(1)의 루프가 돌면서 i=2를 담고 i=2로 재귀(2)
			// i=2이므로 2를 담고 재귀(3)
		}
	}
}
