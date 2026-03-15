import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
 * 방향성이 없는 그래프가 주어진다. 1번에서 N번으로 가는 최단 거기로 이동하려고 한다.
 * 하지만 임의로 주어진 두 정점을 반드시 통과해야 한다.
 * 1번 정점에서 N번 정점으로 이동할 때 주어진 두 정점을 반드시 거치면서 최단 경로로 이동하라!!
 * 그렇게 N까지 도착했을 때의 최단 경로의 길이를 출력해라. 없으면 -1
 */
public class Main {
	
	static class Node implements Comparable<Node> {
		int a, c;
		public Node(int a, int c) {
			this.a = a;
			this.c = c;
		}
		@Override
		public int compareTo(Node n) {
			return Integer.compare(this.c, n.c);
		}
	}
	static boolean f = true;
	static List<Node>[] graph;
	static PriorityQueue<Node> pq = new PriorityQueue<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 정점의 개수 N
		int E = Integer.parseInt(st.nextToken()); // 간선의 개수 E
		
		graph = new ArrayList[N + 1];
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			graph[a].add(new Node(b, c));
			graph[b].add(new Node(a, c));
		} // 간선 양방향 연결
		
		st = new StringTokenizer(br.readLine());
		int V1 = Integer.parseInt(st.nextToken()); // 첫 번째 정점
		int V2 = Integer.parseInt(st.nextToken()); // 두 번째 정점
		
		int[] dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		int first = dijk(1, V1, dist) + dijk(V1, V2, dist) + dijk(V2, N, dist);
		int second = dijk(1, V2, dist) + dijk(V2, V1, dist) + dijk(V1, N, dist);
		
		
		int result = Math.min(first, second); // 임의의 두 정점을 지났을 때 더 짧은 경로는?
		System.out.println(f ? result : -1);
	}
	
	static int dijk(int start, int end, int[] dist1) {
		int[] dist = dist1.clone(); // 배열 복사해서 활용
		
		pq.clear(); // 시작하기 전에 초기화
		pq.add(new Node(start, 0));
		dist[start] = 0;
		
		int count = 0;
		while(!pq.isEmpty()) {
			Node curr = pq.poll();
			int x = curr.a;
			int c = curr.c;
			
			if(c > dist[x]) continue;
			if(x == end) {
				return c;
			}
			
			for(Node next : graph[x]) {
				int tc = c + next.c;
				if(tc >= dist[next.a]) continue;
				
				dist[next.a] = tc;
				pq.add(new Node(next.a, tc));
			}
		}
		
		if(count == 0) f = false; // 여기까지 오면 연결된 노드가 없는 것
		return count;
	}
}