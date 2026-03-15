import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
 * N * M의 맵이 있다. 0은 길 1은 벽이다. 좌상단에서 우하단으로 가야 한다.
 * 벽은 K개까지 부술 수 있다.
 * 최단 거리를 구해보자. 불가능하면 -1을 출력한다. 이때 출발점과 도착점도 포함해서 센다.
 */
public class Main {
	
	static class Move implements Comparable<Move> {
		int x, y, count, k;

		public Move(int x, int y, int count, int k) {
			this.x = x;
			this.y = y;
			this.count = count;
			this.k = k;
		}

		@Override
		public int compareTo(Move m) {
			return Integer.compare(this.count, m.count);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 맵의 세로 N
		int M = Integer.parseInt(st.nextToken()); // 맵의 가로 M
		int K = Integer.parseInt(st.nextToken()); // 벽 부수기 횟수 K
		
		char[][] map = new char[N][M];
		
		for(int i = 0; i < N; i++) {
			String input = br.readLine();
			map[i] = input.toCharArray();
		} // 맵 정보 입력 완료
		
		PriorityQueue<Move> pq = new PriorityQueue<>();
		int[][][] dist = new int[K + 1][N][M];
		for(int i = 0; i <= K; i++) {
			for(int j = 0; j < N; j++) {
				Arrays.fill(dist[i][j], Integer.MAX_VALUE);
			}
		} // 가중치 배열 초기화 완료
		
		int[] dr = {-1, 1, 0, 0,}, dc = {0, 0, -1, 1};
		
		pq.add(new Move(0, 0, 1, 0)); // 마지막이 벽 부순 횟수
		dist[0][0][0] = 1;
		
		while(!pq.isEmpty()) {
			Move curr = pq.poll();
			int x = curr.x;
			int y = curr.y;
			int cnt = curr.count;
			int k = curr.k;
			
			if(cnt > dist[k][x][y]) continue;

			if(x == N - 1 && y == M - 1) { // 도착했으면 출력
				System.out.println(cnt);
				return;
			}
			
			for(int i = 0; i < 4; i++) {
				int tx = x + dr[i];
				int ty = y + dc[i];
				int tc = cnt + 1;
				
				if(tx < 0 || N <= tx || ty < 0 || M <= ty) continue;
				
				if(map[tx][ty] == '1' && (k + 1) <= K) { // 아직 벽을 부술 수 있다면?
					if(tc >= dist[k + 1][tx][ty]) continue;
					
					dist[k + 1][tx][ty] = tc;
					pq.add(new Move(tx, ty, tc, k + 1));
					
				} else if(map[tx][ty] == '0') { // 다음이 길이면 그냥 이동
					if(tc >= dist[k][tx][ty]) continue;
					
					dist[k][tx][ty] = tc;
					pq.add(new Move(tx, ty, tc, k));
				}
			} // for 종료
		} // while 종료
		
		System.out.println(-1); // 여기로 오면 이동할 수 없다는 뜻
	}
}