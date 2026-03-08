import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
 * n 개의 도시가 있다. m 개의 버스가 있다.
 * A에서 B까지 가는 버스 비용을 최소화 시키려고 한다.
 * 
 * 첫 줄에는 N, 다음 줄에는 M, 그 다음부터는 버스의 정보가 주어진다.
 * 정보는 출발점, 도착점, 비용의 순이다.
 */
public class Main {
	
	static int N, M;
	static int[] dist;
	static List<int[]>[] graph;
	static PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
		@Override
		public int compare(int[] a, int[] b) {
			return a[1] - b[1];
		}
	});
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); // 도시의 개수
		M = Integer.parseInt(br.readLine()); // 버스의 개수
		
		graph = new ArrayList[N + 1]; // 그래프 초기화
		for(int i = 1; i <= N; i++) graph[i] = new ArrayList<>();
		
		dist = new int[N + 1]; // 가중치 초기화
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph[start].add(new int[] {end, weight});
		} // 그래프 입력 완료
		
		st = new StringTokenizer(br.readLine());
		int from = Integer.parseInt(st.nextToken());// 여기서 출발해서
		int to = Integer.parseInt(st.nextToken());  // 여기에 도착해야 한다.
		
		dijkstra(from, to); // 다익스트라 시작
	}

	private static void dijkstra(int a, int b) {
		queue.add(new int[] {a, 0});
		dist[a] = 0;
		
		while(!queue.isEmpty()) {
			int[] curr = queue.poll();
			int x = curr[0];
			int cost = curr[1];
			
			if(cost > dist[x]) continue;
			if(x == b) {
				System.out.println(cost);
				return;
			} // 도착했으면 출력 후 종료
			
			for(int[] next : graph[x]) {
				// 기존에 저장된 값 이상이면 패스
				if(dist[next[0]] <= cost + next[1]) continue;
				
				dist[next[0]] = cost + next[1];
				queue.add(new int[] {next[0], cost + next[1]});
			}
		}
	}
}