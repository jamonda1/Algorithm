import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
 * 두 개의 문자열이 주어졌을 때, 해당 두 문자열에서 가장 길게 이어지는 문자열의 길이는?
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] A = (" " + br.readLine()).toCharArray(); // 문자열을 배열로 변경
        char[] B = (" " + br.readLine()).toCharArray();

        int[][] dp = new int[A.length][B.length];

        int result = 0;
        for(int i = 1; i < A.length; i++) {
            for(int j = 1; j < B.length; j++) {
                if(A[i] == B[j]) {
                    dp[i][j] = dp[i-1][j-1] + 1; // 문자열이 이어지게 되면 직전의 길이 +1
                    result = Math.max(result, dp[i][j]); // 그리고 최댓값 비교
                } else dp[i][j] = 0; // 만약 문자열이 이어지지 않으면 0
            }
        }

        System.out.println(result);
    }
}