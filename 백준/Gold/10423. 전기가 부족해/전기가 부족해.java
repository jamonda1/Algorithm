import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
 * 발전소를 만들 때 중요한 것은 발전소 건물과 도시로 전기를 공급할 케이블이다. 발번소는 이미 특정 도시에 건설되어 있다.
 * 따라서 추가적으로 드는 비용은 케이블을 설치할 때 드는 비용이 전부이다.
 * N개의 도시가 있고 M개의 두 도시를 연결하는 케이블의 정보가 K개. 한 도시가 두 발전소에서 전기를 공급 받으면 낭비다.
 */
public class Main {
	
	static class Node implements Comparable<Node> {
		int e;
		int w;
		
		public Node(int e, int w) {
			this.e = e;
			this.w = w;
		}
		
		@Override
		public int compareTo(Node n) {
			return this.w - n.w;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 도시의 개수 N
		int M = Integer.parseInt(st.nextToken()); // 설치 가능 케이블의 수 M
		int K = Integer.parseInt(st.nextToken()); // 발전소의 개수 K
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < K; i++) {
			int startNode = Integer.parseInt(st.nextToken());
			pq.add(new Node(startNode, 0));
		} // 발전소가 설치된 도시
		
		List<Node>[] list = new ArrayList[N + 1];
		boolean[] visited = new boolean[N + 1];
		
		for(int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			list[s].add(new Node(e, w)); // 양방향으로 연결해줘야 한다.
			list[e].add(new Node(s, w));
		}
		
		int cost = 0;
		int conn = 0;
		while(!pq.isEmpty()) {
			Node curr = pq.poll();
			int x = curr.e; // 지금 위치 x
			int c = curr.w; // 지금 비용 c
			
			if(visited[x]) continue;
			
			visited[x] = true;
			cost += c;
			conn++;
			
			if(conn == N) break;
			
			for(Node n : list[x]) {
				if(!visited[n.e]) { // 다음 장소를 방문하지 않았다면?
					pq.add(n);
				}
			}
		}
		
		System.out.println(cost);
	}
}