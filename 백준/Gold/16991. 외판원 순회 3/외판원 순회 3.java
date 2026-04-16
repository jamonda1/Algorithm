import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static double[][] dist, dp;
	static final double INF = Double.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); // 도시의 개수 N
		
		int[][] xy = new int[N][2];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			xy[i][0] = Integer.parseInt(st.nextToken());
			xy[i][1] = Integer.parseInt(st.nextToken());
		} // 각 점의 x, y 좌표 입력 완료
		
		dist = new double[N][N];
		dp = new double[N][1 << N];
		for(int i = 0; i < N; i++) {
			Arrays.fill(dp[i], -1);
			for(int j = i; j < N; j++) {
				if(i == j) continue;
				// i 도시와 j 도시의 거리 구하기
				double w = Math.hypot(xy[i][0] - xy[j][0], xy[i][1] - xy[j][1]);
				dist[i][j] = w;
				dist[j][i] = w;
			}
		} // 가중치 배열 완성
		
		double result = tsp(0, 1);
		
		System.out.println(result);
	}

	private static double tsp(int curr, int visited) {
		if(visited == (1 << N) - 1) { // 모든 도시 방문했으면 0으로 가는 가중치 반환
			return dp[curr][0] = (dist[curr][0] != 0) ? dist[curr][0] : INF;
		}
		
		// 이미 방문했던 곳은 메모이제이션으로 넘겨주기
		if(dp[curr][visited] != -1) return dp[curr][visited];
		
		dp[curr][visited] = INF;
		
		for(int i = 0; i < N; i++) {
			if((visited & (1 << i)) == 1) continue; // 방문한 곳 패스
			if(dp[curr][i] == 0) continue; // 갈 수 없는 곳 패스
			
			dp[curr][visited] = Math.min(dp[curr][visited], tsp(i, visited | (1 << i)) + dist[curr][i]);
		}
		
		return dp[curr][visited];
	}
}