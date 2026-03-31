import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
/*
 * 찬민이는 최다 M원까지만 사용해서 LA로 가야 한다. 가는 길에 공항을 여러 곳을 들러야 할 수도 있다.
 * 1번은 항상 인천이고 N번은 항상 LA다. M원 이하를 소비하면서 최대한 빨리 LA로 가자.
 * 도착할 수 있다면, 가장 짧은 소요 시간을 출력하고, 도착이 불가능하다면 Poor KCM을 출력하자!
 */
public class Main {
    
    static class Tiket implements Comparable<Tiket> {
        int v, c, d;
        public Tiket(int v, int c, int d) {
            this.v = v;
            this.c = c;
            this.d = d;
        }
        @Override
        public int compareTo(Tiket o) {
            return Integer.compare(this.d, o.d);
        }
    }
    static int MAX = Integer.MAX_VALUE;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스는 항상 1이 입력된다.
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 공항의 수 N
        int M = Integer.parseInt(st.nextToken()); // 총 지원 비용 M
        int K = Integer.parseInt(st.nextToken()); // 티켓 정보의 수 K
        
        List<Tiket>[] graph = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) graph[i] = new ArrayList<>();
        
        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()); // 출발
            int v = Integer.parseInt(st.nextToken()); // 도착
            int c = Integer.parseInt(st.nextToken()); // 비용
            int d = Integer.parseInt(st.nextToken()); // 시간
            graph[u].add(new Tiket(v, c, d));
        } // 티켓 정보들 입력 완료
        
        
        int[][] dp = new int[M + 1][N + 1];
        for(int i = 1; i <= M; i++) Arrays.fill(dp[i], MAX);
        
        for(Tiket t : graph[1]) { // 1번 공항을 가져와서, 모든 비용의 자리에 값을 뿌려놓는다.
			dp[t.c][t.v] = Math.min(dp[t.c][t.v], t.d);
		}
        
        // 탐색 시작
        for(int i = 1; i <= M; i++) { // 비용 i원으로 갈 수 있는 곳은??
        	for(int j = 2; j <= N; j++) {
        		for(Tiket t : graph[j]) {
        			int cost = i + t.c;
        			if(dp[i][j] != MAX && cost <= M) {
        				dp[cost][t.v] = Math.min(dp[cost][t.v], dp[i][j] + t.d);
        			}
        		}
        	}
        }
        
        int result = MAX;
    	for(int i = 1; i <= M; i++) {
    		if(dp[i][N] != MAX) result = Math.min(result, dp[i][N]);
    	}
    	
    	System.out.println(result == MAX ? "Poor KCM" : result);
    }
}