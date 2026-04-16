import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(); // 3 * N의 기준
		
		if(N % 2 == 1) {
			System.out.println(0);
			return;
		}
		
		int[] dp = new int[N + 1];
		dp[2] = 3; // N = 2일때는 3가지밖에 없다.
		
		int sum = 0;
		for(int i = 4; i <= N; i+=2) {
			// 직전값에서 2가 붙은 거니깐 *3, 직전값의 더 전값들까지의 합(sum) *2, 해당 i의 특수 모양 2개
			dp[i] = (dp[i-2] * 3) + (sum * 2) + 2;
			sum += dp[i - 2];
		}
		
		System.out.println(dp[N]);
	}
}