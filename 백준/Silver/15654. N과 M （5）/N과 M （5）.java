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
		int[] result = new int[N];
		boolean[] visited = new boolean[N];
		
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(num);
		
		dfs(result, num, visited, N, M, 0);
		
		
		bw.flush();
		bw.close();
	}
	
	static void dfs(int[] result, int[] num, boolean[] visited, int N, int M, int depth) throws IOException {
		if(depth == M) {
			for(int i = 0; i < M; i++) {
				bw.write(result[i] + " ");
			}
			bw.write("\n");
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				result[depth] = num[i];
				dfs(result, num, visited, N, M, depth + 1);
				visited[i] = false;
				// 메서드(1)에서 i=1을 찜해서 배열에 넣은 후 재귀(2)로 전달. 재귀(2)에서 i=1은 찜이 되어 있으니 i=2 담고 재귀(3) 전달
				// 재귀(3)에서 depth==m이므로 출력 후 재귀(2)로 리턴, 재귀(2)에서 i=2를 찜해제하고 i=3 담아서 재귀(3) 전달...
			}
		}
	}
}
