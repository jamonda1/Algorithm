import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 광대는 s에서 출발했다. 그들이 g와 h 교차로 사이의 도로를 지났다는 것을 알고 있다.
 * 목적지 후보가 주어졌을 때, 해당 목적지가 특정 도로를 지나는지 확인하자. 길은 양방향 도로다.
 * 주어진 목적지 후보들 중에서 불가능한 경우를 제외한 목적지를 오름차순으로 출력!
 */
public class Main {
	
	static class Node implements Comparable<Node> {
		int x, w;
		public Node(int x, int w) {
			this.x = x;
			this.w = w;
		}
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.w, o.w);
		}
	}
	static int N, s, g, h, MAX = Integer.MAX_VALUE;
	static List<Integer>[] parent;
	static Queue<Integer> queue = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int TC = Integer.parseInt(br.readLine());
		for(int t = 1; t <= TC; t++) {
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 교차로의 개수 N
			int M = Integer.parseInt(st.nextToken()); // 도로의 개수 M
			int T = Integer.parseInt(st.nextToken()); // 목적지 후보의 개수 T
			
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken()); // 출발지 s
			g = Integer.parseInt(st.nextToken()); // g와
			h = Integer.parseInt(st.nextToken()); // h 사이의 도로를 지나야 한다.
			
			List<Node>[] graph = new ArrayList[N + 1]; // 그래프 초기화
			parent = new ArrayList[N + 1];
			for(int i = 1; i <= N; i++) {
				graph[i] = new ArrayList<>();
				parent[i] = new ArrayList<>();
			}
			
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				graph[a].add(new Node(b, d)); // 도로는 양방향이다.
				graph[b].add(new Node(a, d));
			} // 도로 정보 입력 완료
			
			PriorityQueue<Node> pq = new PriorityQueue<>();
			int[] dist = new int[N + 1];
			Arrays.fill(dist, MAX);
			
			pq.add(new Node(s, 0)); // 초기값 설정
			dist[s] = 0;
			
			while(!pq.isEmpty()) { // 탈출 조건 없이 다익스트라 수행
				Node curr = pq.poll();
				int x = curr.x;
				int w = curr.w;
				
				if(w > dist[x]) continue;
				
				for(Node n : graph[x]) {
					int tw = w + n.w;
					
					if(tw > dist[n.x]) continue;
					if(tw == dist[n.x]) parent[n.x].add(x);
					if(tw < dist[n.x]) {
						parent[n.x].clear();
						parent[n.x].add(x);
						dist[n.x] = tw;
						pq.add(new Node(n.x, tw));
					}
				}
			}
			
			PriorityQueue<Integer> result = new PriorityQueue<>();
			// T번 역추적 수행
			for(int i = 0; i < T; i++) {
				int x = Integer.parseInt(br.readLine()); // 목적지 후보 x
				if(x < 1 || N < x) continue;
				
				if(find(x)) result.add(x); // 조건에 맞으면 도달 가능 목적지
			}
			
			while(!result.isEmpty()) bw.write(result.poll() + " ");
		
			bw.write("\n");
		} // 전체 테스트 케이스 종료
		bw.close();
	}
	
	private static boolean find(int x) {
		boolean[] visited = new boolean[N + 1];
		queue.clear();
		queue.add(x);
		visited[x] = true;
		
		while(!queue.isEmpty()) {
			int curr = queue.poll();
			
			if(curr == s) break;
			for(int n : parent[curr]) { // 조건에 맞으면 바로 리턴
				if((curr == g && n == h) || (curr == h && n == g)) return true;
				if(visited[n]) continue;
				visited[n] = true;
				queue.add(n);
			}
		}
		
		return false;
	}
}