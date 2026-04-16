import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
 * 링크는 (0, 0)의 위치에 있다.
 * 각 칸에는 소비하는 루피가 적혀있다.
 * 최소한으로 소비하면서 (N-1, N-1)로 이동해보자
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				return a[2] - b[2];
			}
		}); // 소비한 돈이 적은 순으로 정렬
		
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		
		int tc = 1;
		for(;;) {
			int N = Integer.parseInt(br.readLine()); // 수식의 길이 N
			if(N == 0) break;
			
			int[][] map = new int[N][N];
			int[][] dist = new int[N][N]; // 해당 칸에 도착했을 때의 최소 비용을 저장
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					dist[i][j] = Integer.MAX_VALUE;
				}
			} // map 입력 완료
			
			pq.add(new int[] {0, 0, map[0][0]}); // 첫 시작점과 요금
			dist[0][0] = map[0][0];
			
			int result = 0;
			while(!pq.isEmpty()) {
				int[] curr = pq.poll();
				
				if(curr[0] == N-1 && curr[1] == N-1) {
					result = curr[2];
					break;
				} // 목적지에 도착했으면 어차피 최소비용으로 오게 된다.
				
				if(curr[2] < dist[curr[0]][curr[1]]) continue;
				
				for(int i = 0; i < 4; i++) {
					int tx = curr[0] + dr[i];
					int ty = curr[1] + dc[i];
					
					if(tx < 0 || N <= tx || ty < 0 || N <= ty) continue;
					// 이미 기록된 비용보다 더 크거나 같은 값이 나오면 패스
					if(dist[tx][ty] <= curr[2] + map[tx][ty]) continue;
					
					dist[tx][ty] = curr[2] + map[tx][ty];
					pq.add(new int[] {tx, ty, dist[tx][ty]});
				}
			}
			
			bw.write("Problem " + tc + ": " + result);
			bw.write("\n");
			tc++;
			pq.clear(); // 잔여물 제거
		}
		
		bw.flush();
		bw.close();
	}
}