import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int[] num;
	
    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       
       StringTokenizer st = new StringTokenizer(br.readLine());
       int N = Integer.parseInt(st.nextToken());
       int M = Integer.parseInt(st.nextToken());
       
       num = new int[M];
       
       dfs(N, M, 0, 1);
       
       bw.flush();
       bw.close();
    }

	static void dfs(int N, int M, int depth, int start) throws IOException {
		if(depth == M) {
			for(int i = 0; i < M; i++) {
				bw.write(num[i] + " ");
			}
			bw.write("\n");
			return;
		}
		
		for(int i = start; i <= N; i++) {  // start부터 탐색 시작
			num[depth] = i;				   // i가 1일 경우 num에 담고
			dfs(N, M, depth + 1, i + 1);   // 다음 탐색에 i+1(2)를 보내서 2부터 탐색 시작하도록 한다.
		}
	}
}