import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static int[][] map, dp;
	static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken()); // 맵의 세로 N
        M = Integer.parseInt(st.nextToken()); // 맵의 가로 M
        
        map = new int[N][M];
        
        for(int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j = 0; j < M; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        	}
        } // 맵 정보 입력 완료
        
        dp = new int[N][M];
        for(int i = 0; i < N; i++) {
        	Arrays.fill(dp[i], -1);
        }
        
        int result = memoization(0, 0);
        
        System.out.println(result);
    }

	private static int memoization(int x, int y) {
		if(x == N-1 && y == M-1) { // 목적지에 도착하면 1 리턴
			return 1;
		}
		
		if(dp[x][y] != -1) return dp[x][y]; // 이미 방문한 곳이면 1 리턴
		
		dp[x][y] = 0; // -1을 0으로 초기화
		for(int i = 0; i < 4; i++) {
			int tx = x + dr[i];
			int ty = y + dc[i];
			
			if(tx < 0 || N <= tx || ty < 0 || M <= ty) continue;
			if(map[x][y] <= map[tx][ty]) continue; // 다음 갈 곳이 [x][y]보다 작아야 한다.
			
			dp[x][y] += memoization(tx, ty);
		}
		
		return dp[x][y];
	}
}