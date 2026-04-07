import java.util.Scanner;

public class Main {
	
	static final int DIV = 1000000007;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(); // 입력된 N
		
		long[] dp = new long[N + 1];
		
		if(N == 1) {
			System.out.println(2);
			return;
		} else if(N == 2) {
			System.out.println(7);
			return;
		}
		
		dp[0] = 1; dp[1] = 2; dp[2] = 7;
		
		for(int i = 3; i <= N; i++) {
			long a = (dp[i - 1] % DIV) * 3;
			long b = dp[i - 2] % DIV - dp[i - 3] % DIV;
			
			dp[i] = ((a + b) % DIV + DIV) % DIV;
		}
		
		System.out.println(dp[N]);
	}
}