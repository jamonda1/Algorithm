import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * n가지 종류의 동전이 있다. 이 동전들을 적당히 사용해서 k원이 되도록 하고 싶다.
 * 그러면서 동전의 개수는 최소가 되어야 한다.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken()); // 동전의 수
        int k = Integer.parseInt(st.nextToken()); // 목표
        
        int[] coin = new int[n];
        int[] dp = new int[k + 1];
                
        for(int i = 0; i < n; i++) {
        	coin[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(coin); // 오름차순 정렬
        Arrays.fill(dp, 100001); // 초기값은 최대로
        
        dp[0] = 0; // 0번은 항상 0이어야 한다.
        for(int i = 1; i <= k; i++) {
        	for(int c : coin) {
        		if(i >= c) { // c만큼 전에 있는 값에서 +1한 값과 지금 저장된 값 중 최솟값
    				dp[i] = Math.min(dp[i], dp[i - c] + 1);
        		}
        	}
        }
        
        System.out.println((dp[k] == 100001) ? -1 : dp[k]);
    }
}