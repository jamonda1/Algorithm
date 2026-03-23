import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * 형택 호텔의 사장인 김형택은 수입을 늘리기 위해 홍보를 하려고 한다.
 * 형택이가 홍보할 수 있는 도시가 주어지고, 각 도시별로 홍보에 드는 비용과, 그 때 몇 명의 호텔 고객이 늘어나는지에 대한 정보가 있다.
 * 어떤 도시에 9원을 들여서 홍보를 하면 3명의 고객이 늘어난다. 와 같은 정보가 주어지고, 돈의 배수만큼 투자할 수 있다.
 * 호텔의 고객을 적어도 C명 늘이기 위해 투자해야 하는 돈의 최솟값을 구해라!
 */
public class Main {
    
    static class Hotel {
        int c, p;
        public Hotel(int c, int p) {
            this.c = c;
            this.p = p;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int C = Integer.parseInt(st.nextToken()); // 목표 고객 수 C
        int N = Integer.parseInt(st.nextToken()); // 도시의 개수 N
        
        Hotel[] hotel = new Hotel[N+1];
        
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken()); // 홍보에 필요한 비용
            int p = Integer.parseInt(st.nextToken()); // 얻을 수 있는 고객의 수
            hotel[i] = new Hotel(c, p);
        }
        
        int[] dp = new int[C + 101]; // 얻을 수 있는 고객의 수는 100 이하의 자연수
        Arrays.fill(dp, 987654321);
        dp[0] = 0;
        
        // 바로 위의 값이랑 이전 값에서 인원 수를 맞춘 값 중 더 작은 값을 구해야 한다.
        for(int i = 1; i <= N; i++) {
        	int cost = hotel[i].c;
        	int people = hotel[i].p;
        	
        	// [i]번째 도시에서 얻을 수 있는 최소 인원부터 최대 인원까지 시작
            for(int j = people; j < dp.length; j++) {
                dp[j] = Math.min(dp[j], dp[j - people] + cost);
            }
        }
        
        
        int result = Integer.MAX_VALUE;
        for(int i = C; i < dp.length; i++) {
        	result = Math.min(result, dp[i]);
        }
        
        System.out.println(result);
    }
}