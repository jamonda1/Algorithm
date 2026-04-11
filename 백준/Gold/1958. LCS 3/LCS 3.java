import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] a = (" " + br.readLine()).toCharArray();
        char[] b = (" " + br.readLine()).toCharArray();
        char[] c = (" " + br.readLine()).toCharArray();

        int[][][] dp = new int[a.length][b.length][c.length];

        for(int i = 1; i < a.length; i++) {
            for(int j = 1; j < b.length; j++) {
                for(int k = 1; k < c.length; k++) {
                    if(a[i] == b[j] && b[j] == c[k]) dp[i][j][k] = dp[i-1][j-1][k-1] + 1;
                    else dp[i][j][k] = Math.max(dp[i-1][j][k], Math.max(dp[i][j-1][k], dp[i][j][k-1]));
                }
            }
        }

        System.out.println(dp[a.length-1][b.length-1][c.length-1]);
    }
}