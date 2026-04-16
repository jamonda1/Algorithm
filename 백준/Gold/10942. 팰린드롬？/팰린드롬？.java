import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
/*
 * 홍준이는 자연수 N개를 칠판에 적는다. 그리고 명우에게 질문을 M번 한다.
 * 각 질문은 S번째 수부터 E번째 수까지가 팰린드롬인지 물어보는 내용이다.
 * 
 * 맞으면 1, 아니면 0을 출력하자!
 */
public class Main {
	
	static int[] nums;
	static boolean[][] dp = new boolean[2000][2000];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 칠판에 적은 자연수의 개수 N
		
		nums = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		} // 자연수들 입력 완료
		
		int M = Integer.parseInt(br.readLine());
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			boolean f = check(S - 1, E - 1);
			bw.write((f ? 1 : 0) + "\n");
		}
		
		bw.flush();
		bw.close();
	}

	private static boolean check(int s, int e) {
		int left = s;
		int right = e;
		while(left <= right) {
			// left랑 right가 기존에 탐색한 적이 있다면?
			if(dp[left][right]) return true;
			if(nums[left] != nums[right]) return false;
			
			left++;
			right--;
		}
		
		dp[s][e] = true; // 팰린드롬이라면 dp에 저장
		return true;
	}
}