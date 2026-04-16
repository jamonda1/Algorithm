import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] num = new int[N];
		int[] result = new int[M];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(num);
		
		dfs(num, result, N, M, 0, 0);
		
		bw.flush();
		bw.close();
	}
	
	static void dfs(int[] num, int[] result, int N, int M, int depth, int start) throws IOException {
		if(depth == M) {
			for(int i = 0; i < M; i++) {
				bw.write(result[i] + " ");
			}
			bw.write("\n");
			return;
		}
		
		for(int i = start; i < N; i++) {
			result[depth] = num[i];
			dfs(num, result, N, M, depth + 1, i + 1);
		} // 4 2, 1 2 3 4를 입력했다고 가정
		// 메서드(1)에서 i=0으로 1을 배열에 담고, i+1해서 i=1을 start로 재귀(2)에 전달
		// 재귀(2)에서 i=1로 2를 배열에 담고 i+1해서 i=2를 재귀(3)에 전달했는데 depth==m으로 출력 후 재귀(2)로 리턴
		// 재귀(2)에서 반복문 돌아서 i=3 진행... 반복
	}
}
