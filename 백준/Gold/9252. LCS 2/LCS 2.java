import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str1 = br.readLine();
        String str2 = br.readLine();

        char[] A = (" " + str1).toCharArray();
        char[] B = (" " + str2).toCharArray();

        int[][] dp = new int[A.length][B.length];

        for(int i = 1; i < A.length; i++) {
            for(int j = 1; j < B.length; j++) {
                if(A[i] == B[j]) dp[i][j] = dp[i-1][j-1] + 1;
                else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }

        Deque<Character> deque = new ArrayDeque<>();

        int i = A.length - 1;
        int j = B.length - 1;
        bw.write(dp[i][j] + "\n");

        while(i > 0 && j > 0) {
            if(A[i] == B[j]) {
                deque.push(A[i]);
                i--; j--;
            } else if(dp[i][j] == dp[i-1][j]) i--;
            else j--;
            // Math.max를 통해서 전달되는 것이기 때문에 최댓값을 따라서 역추적을 진행
        }

        while(!deque.isEmpty()) {
            bw.write(deque.poll() + "");
        }

        bw.close();
    }
}