import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
 * 방향 그래프가 주어지면, 주어진 시작점에서 다른 정점으로의 최단 경로를 구해라
 * 모든 간선의 가중치는 10 이하의 자연수이다.
 * 
 * 모든 정점에는 1부터 V까지 번호가 있다. 
 */
public class Main {
	
	static int V, E, K;
	static int[] dist;
	static List<int[]>[] graph;
	static PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
				@Override
				public int compare(int[] a, int[]b) {
					return a[1] - b[1];
				}
			});
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken()); // 정점의 개수 V
		E = Integer.parseInt(st.nextToken()); // 간선의 개수 E
		
		K = Integer.parseInt(br.readLine()); // 시작 정점의 번호 K
		
		
		graph = new ArrayList[V + 1];
		for(int i = 1; i <= V; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()); // u에서 출발해서
			int v = Integer.parseInt(st.nextToken()); // v까지 오려면
			int w = Integer.parseInt(st.nextToken()); // w만큼 걸린다.
			
			graph[u].add(new int[] {v, w});	
		} // 단방향 그래프 입력 완료
		
		dist = new int[V + 1];
		for(int i = 1; i <= V; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		
		dijkstra();
		for(int i = 1; i <= V; i++) {
			if(dist[i] == Integer.MAX_VALUE) {
				bw.write("INF\n");
			} else {
				bw.write(dist[i] + "\n");
			}
		}

		bw.flush();
		bw.close();
	}

	static void dijkstra() {
		dist[K] = 0;
		pq.add(new int[] {K, 0});
		
		while(!pq.isEmpty()) {
			int[] curr = pq.poll();
			
			// 새로 꺼낸 값이 기록된 가중치보다 크면 필요 없다.
			if(curr[1] > dist[curr[0]]) continue;
			
			for(int[] temp : graph[curr[0]]) {
				int v = temp[0]; // 다음 경로
				int w = temp[1] + curr[1]; // 다음 가중치
				
				// 기존보다 크면 쓸모가 없고, 같으면 이미 지나갔던 길이다.
				if(w >= dist[v]) continue;
				dist[v] = w;
				pq.add(new int[] {v, w});
			}
		}
	}
}