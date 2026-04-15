import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[][] map, dp;
	static int INF = 987654321;
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine()); // 도시의 수 N
        
        map = new int[N][N];
        
        for(int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j = 0; j < N; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        	}
        } // 도시 가중치 입력 완료
        
        dp = new int[N][1 << N]; // 순열 조합 수만큼 배열 생성
        for(int i = 0; i < N; i++) {
        	Arrays.fill(dp[i], -1);
        }
        
        int result = tsp(0, 1); // 0번도시부터 시작, 1은 visited
        
        System.out.println(result);
    }

	private static int tsp(int curr, int visited) {
		if(visited == (1 << N) - 1) { // N이 4면 10000이 되고 거기서 1을 빼면 1111 즉 4개의 도시 방문 완료
			// 모든 도시를 방문한 후에 지금 도시(curr)에서 출발점(0)으로 갈 수 있냐?
			// 갈 수 있으면 해당 가중치 반환, 없으면 INF
			return dp[curr][visited] = (map[curr][0] != 0) ? map[curr][0] : INF;
		}
		
		// -1이 아니면 이미 방문한 곳이므로 저장된 값 반환 -> 메모이제이션
		if(dp[curr][visited] != -1) return dp[curr][visited];
		
		dp[curr][visited] = INF; // 탐색 전에 초기화 수행
		
		for(int i = 0 ; i < N; i++) {
			// curr에서 i로 진입할 수 없거나, 이미 방문 완료했으면 패스
			if(map[curr][i] == 0 || (visited & (1 << i)) != 0) continue;
			
			// i번 도시에 도착했으니 다음 curr을 i로, 기존 visited에 1을 << i만큼 넘긴 것을 or 연산
			dp[curr][visited] = Math.min(dp[curr][visited], tsp(i, visited | (1 << i)) + map[curr][i]);
		}
		
		return dp[curr][visited];
	}
}