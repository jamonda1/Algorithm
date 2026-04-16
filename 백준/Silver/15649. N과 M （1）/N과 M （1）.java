import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int[] num;
	static boolean[] visited;
	
    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       
       
       StringTokenizer st = new StringTokenizer(br.readLine());
       int N = Integer.parseInt(st.nextToken());
       int M = Integer.parseInt(st.nextToken());
       
       num = new int[N];
       visited = new boolean[N + 1];
       
       dfs(0, M, N, visited);
       
       bw.flush();
       bw.close();
    }

	private static void dfs(int depth, int m, int n, boolean[] visited) throws IOException {
		if(depth == m) {
			for(int i = 0; i < m; i++) {
				bw.write(num[i] + " ");
			}
			bw.write("\n");
			return;
		}
		
		for(int i = 1; i <= n; i++) {
			if(!visited[i]) {
				visited[i] = true;
				num[depth] = i;
				dfs(depth + 1, m, n, visited);
				visited[i] = false;
			}
		}
	}
}