import java.util.Scanner;

public class Main {
	
	static int DIV = 10007;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		if(N < 3) { // 1이면 출력 후 종료
			System.out.println(N);
			return;
		}
		
		int[] dp = new int[N + 1];
		dp[1] = 1; // 초기값 설정
		dp[2] = 2;
		
		for(int i = 3; i <= N; i++) { // 모듈러 연산
			dp[i] = (dp[i - 2] % DIV + dp[i - 1] % DIV) % DIV;
		}
		
		System.out.println(dp[N]);
	}
}