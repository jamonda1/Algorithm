import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
 * N개의 논에 물을 대려고 한다. 물을 대는 방법은 두 가지가 있는데, 하나는 직접 논에 우물을 파는 것이고
 * 다른 하나는 이미 물을 대고 있는 다른 논으로부터 물을 끌어오는 법이다.
 * 
 * 우물을 파는 비용과, 논에서 끌어오는 비용이 주어졌을 때 잘 비교해서 최소 비용을 만들어보자
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine()); // 우물의 개수 N
		
		int[] well = new int[N];	 // 우물 비용 따로 관리
		int[][] map = new int[N][N]; // 거의 꽉차는 정보가 주어지기 때문에 배열이 좋다
		boolean[] visited = new boolean[N];
		
		int min = 100_001;
		int index = 0;
		for(int i = 0; i < N; i++) {
			well[i] = Integer.parseInt(br.readLine());
			if(min > well[i]) {
				min = well[i];
				index = i;
			}
		} // 우물을 파는 비용 입력
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 논에서 물을 끌어오는 비용 입력
		
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				return a[1] - b[1];
			}
		});
		pq.add(new int[] {index, min}); // 처음에는 반드시 우물을 파야 한다.
		
		int cost = 0;
		int conn = 0;
		while(!pq.isEmpty()) {
			int[] curr = pq.poll();
			int x = curr[0];
			int w = curr[1];
			
			if(visited[x]) continue; // 이미 연결된 노드면 패스
			
			visited[x] = true;
			cost += w;
			conn++;
			
			if(conn == N) break;
			
			for(int i = 0; i < N; i++) {
				if(!visited[i]) { // 방문 안 한 것만 큐에 넣기
					// 우물 비용과 연결 비용중 더 작은 것 집어넣기
					int temp = (well[i] < map[x][i]) ? well[i] : map[x][i];
					pq.add(new int[] {i, temp});
				}
			}
		}
		
		System.out.println(cost);
	}
}