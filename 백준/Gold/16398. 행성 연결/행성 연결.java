import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
 * 홍익 제국의 중심은 행성 T이다. 효과적으로 통치하기 위해 N개의 행성 간에 플로우를 설치하려고 한다.
 * 제국의 모든 행성을 연결하면서, 플로우 관리 비용을 최소한으로 하려고 한다.
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
		} // 가중치 기준 오름차순
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine()); // 행성의 개수 N
		
		List<Node>[] graph = new ArrayList[N + 1];
		boolean[] visited = new boolean[N + 1];
		
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		} // 그래프 초기화 완료
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				int w = Integer.parseInt(st.nextToken());
				if(i == j) continue; // 자기 자신일 때는 추가x
				graph[i].add(new Node(j, w));
			}
		} // 그래프에 값 입력 완료
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(1, 0));
		
		long result = 0;
		int conn = 0;
		while(!pq.isEmpty()) {
			Node curr = pq.poll();
			
			// 이미 방문했다면 패스
			if(visited[curr.e]) continue;
			
			visited[curr.e] = true; // 방문처리 하고
			result += curr.w;		// 결과값에 가중치 더하고
			conn++;					// 연결됐으니 +1
			
			if(conn == N) break; // 모든 행성이 다 연결됐으면?
			
			for(Node n : graph[curr.e]) {
				if(!visited[n.e]) pq.add(n);
			}
		}
		
		System.out.println(result);
	}
}