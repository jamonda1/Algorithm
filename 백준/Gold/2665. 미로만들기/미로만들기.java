import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
 * N*N의 맵이 일부는 검정, 나머지는 흰 방으로 이루어져 있다. 검은 방은 사방이 벽이라서 진입이 불가능하다.
 * 두 개의 흰 방 사이에는 문이 있어서 통행 가능. (0, 0)과 (N-1, N-1)은 항상 흰 방이다.
 * 
 * 시작 방에서 출발해서 끝 방으로 가고자 한다. 하지만 갈 수가 없는 경우도 있다.
 * 그럴 경우 방의 색을 최대한 적게 바꿔서 통행할 수 있게 해보자.
 * 
 * 즉 검은 방에서 흰 방으로 변경해야 하는 최솟값을 구하자!
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				return a[2] - b[2];
			} // 방을 변경한 값이 적은 게 우선
		});
		
		int N = Integer.parseInt(st.nextToken()); // 맵의 크기
		
		char[][] map = new char[N][N];
		int[][] dist = new int[N][N];
		
		int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
		
		for(int i = 0; i < N; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}
		
		for(int i = 0; i < N; i++) {
			String input = br.readLine();
			for(int j = 0; j < N; j++) {
				map[i][j] = input.charAt(j);
			}
		} // 맵 정보 입력 완료
		
		// 초기 방의 위치는 (0, 0)이고 항상 흰 방이다.
		pq.add(new int[] {0, 0, 0});
		dist[0][0] = 0;
		
		int result = 0;
		while(!pq.isEmpty()) {
			int[] curr = pq.poll();
			int x = curr[0]; // 지금 좌표
			int y = curr[1];
			int cnt = curr[2]; // 색 변경 횟수
			
			if(cnt > dist[x][y]) continue; // 지금 값이 기존보다 크면 필요없다.
			if(x == N - 1 && y == N - 1) {
				result = cnt;
				break;
			}
			
			for(int i = 0; i < 4; i++) {
				int tx = x + dr[i];
				int ty = y + dc[i];
				
				if(tx < 0 || N <= tx || ty < 0 || N <= ty) continue;
				// 내 앞이 검은 방이면 횟수 증가해야 한다.
				int temp = (map[tx][ty] == '0') ? 1 : 0;
				if(cnt + temp >= dist[tx][ty]) continue;
				
				dist[tx][ty] = cnt + temp; // 값 갱신
				pq.add(new int[] {tx, ty, dist[tx][ty]});
			}
		}
		
		System.out.println(result);
	}
}