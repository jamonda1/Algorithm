import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();
        
        int[] sugar = {3, 5}; // 설탕 봉지의 종료
        int[] dp = new int[N+1];
        
        int MAX = 987654321;
        Arrays.fill(dp, MAX);
        dp[0] = 0;
        
        for(int i = 0; i < 2; i++) {
        	int pivot = sugar[i];
        	for(int j = pivot; j <= N; j++) {
        		// 직전에 값이 들어간 적이 있는가?
        		if(dp[j - pivot] != MAX) dp[j] = Math.min(dp[j], dp[j - pivot] + 1);
        	}
        }
        
        System.out.println(dp[N] == MAX ? -1 : dp[N]);
        sc.close();
    }
}