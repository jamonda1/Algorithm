import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
 * 두 개의 문자열이 주어졌을 때,
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str1 = br.readLine();
        String str2 = br.readLine();

        char[] A = (" " + str1).toCharArray(); // 문자열을 배열로 변경
        char[] B = (" " + str2).toCharArray();

        int[][] dp = new int[A.length][B.length];

        for(int i = 1; i < A.length; i++) {
            for(int j = 1; j < B.length; j++) {
                if(A[i] == B[j]) dp[i][j] = dp[i-1][j-1] + 1;
                else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }

        System.out.println(dp[A.length-1][B.length-1]);
    }
}