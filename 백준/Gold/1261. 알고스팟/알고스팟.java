import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				return a[2] - b[2];
			}
		});
		int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
		
		int M = Integer.parseInt(st.nextToken()); // 가로의 크기 M
		int N = Integer.parseInt(st.nextToken()); // 세로의 크기 N
		
		char[][] map = new char[N][M];
		int[][] dist = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE); // 배열 최댓값으로 초기화
			
			String input = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = input.charAt(j);
			}
		} // 맵 정보 저장 완료
		
		pq.add(new int[] {0, 0, 0}); // 초기값
		
		while(!pq.isEmpty()) {
			int[] curr = pq.poll();
			int x = curr[0];
			int y = curr[1];
			int c = curr[2];
			
			if(c > dist[x][y]) continue; // 기존 값보다 크면 패스
			if(x == N - 1 && y == M - 1) { // 도착하면 바로 종료
				System.out.println(c);
				return;
			}
			
			for(int i = 0; i < 4; i++) {
				int tx = x + dr[i];
				int ty = y + dc[i];
				
				if(tx < 0 || N <= tx || ty < 0 || M <= ty) continue;
				int tc = (map[tx][ty] == '1') ?  c + 1 : c; // 앞이 벽이면 1 추가
				if(tc >= dist[tx][ty]) continue; // 기존값 이상이면 큐에 넣을 필요 없다
				
				dist[tx][ty] = tc;
				pq.add(new int[] {tx, ty, tc});
			}
		}
	}
}