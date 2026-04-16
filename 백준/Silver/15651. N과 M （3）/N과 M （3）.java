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
		
		dfs(N, M, 0);
		
		bw.flush();
		bw.close();
	}

	private static void dfs(int n, int m, int depth) throws IOException {
		if(depth == m) {
			for(int i = 0; i < m; i++) {
				bw.write(num[i] + " ");
			}
			bw.write("\n");
			return;
		}
		
		for(int i = 1; i <= n; i++) {
			num[depth] = i;
			dfs(n, m, depth + 1);
			// 4 2를 입력했을 때 처음에 1이 선택되고 다음 재귀에서 1이 선택되고 다음 재귀에서 depth==m으로 출력 후 리턴.
			// 두 번째 재귀의 루프가 돌면서 2가 선택되고 다음 재귀에서 출력 후 리턴. m은 2까지이므로 또 리턴
			// 첫 메서드에서 루프가 돌면서 2가 선택되고 재귀 호출, 거기서 다시 i는 1부터 돈다.
		}
	}
}
