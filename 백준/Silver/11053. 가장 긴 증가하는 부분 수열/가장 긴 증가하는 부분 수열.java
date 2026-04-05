import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine()); // 수열의 길이 N
		
		int[] arr = new int[N];
		int[] dp = new int[N];
		
		st = new StringTokenizer(br.readLine()); // 수열 입력
		for(int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
		
		int max = 1;
		for(int i = 0; i < N; i++) {
			dp[i] = 1; // 초기값으로 자기 자신의 길이인 1
			
			for(int j = 0; j < i; j++) {
				if(arr[j] < arr[i]) { // 탐색하다가 i 보다 작은 게 나오면?
					dp[i] = Math.max(dp[i], dp[j] + 1); // 기존 길이와 새로운 길이 비교
				}
			}
			max = Math.max(max, dp[i]); // dp[i]가 최장이란 보장이 없기 때문에 max와 비교
		}
		
		System.out.println(max);
	}
}