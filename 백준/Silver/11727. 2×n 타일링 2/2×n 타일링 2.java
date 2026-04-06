import java.util.Scanner;

public class Main {
	
	static int DIV = 10007;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(); // 입력된 N
		
		if(N == 1) { // N이 1 또는 2일 때는 출력 후 종료
			System.out.println(1);
			return;
		} else if(N == 2) {
			System.out.println(3);
			return;
		}
		
		int[] dp = new int[N + 1];
		dp[1] = 1;
		dp[2] = 3;
		
		for(int i = 3; i <= N; i++) {
			dp[i] = (dp[i - 1] % DIV + dp[i - 2] % DIV * 2) % DIV;
		}
		
		System.out.println(dp[N]);
	}
}