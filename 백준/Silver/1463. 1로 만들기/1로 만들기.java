import java.util.Arrays;
import java.util.Scanner;
/*
 * 1. 3으로 가능하면 3으로 나누기
 * 2. 2로 가능하면 2로 나누기
 * 3. 1 빼기
 * 이 세 가지를 적절히 사용해서 X를 1로 만드는 최소 횟수를 구해라
 */
public class Main {
	
	static int[] dp;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int X = sc.nextInt(); // 주어진 X
		
		dp = new int[X + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		
		makeOne(X, 0);
		
		System.out.println(dp[1]);
		sc.close();
	}

	private static void makeOne(int x, int count) {
		if(dp[x] <= count) return;
		dp[x] = count;
		
		if(x == 1) return;
		
		if(x % 3 == 0) makeOne(x / 3, count + 1);
		if(x % 2 == 0) makeOne(x / 2, count + 1);
		makeOne(x - 1, count + 1);
	}
}