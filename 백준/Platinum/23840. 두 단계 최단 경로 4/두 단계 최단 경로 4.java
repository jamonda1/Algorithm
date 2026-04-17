import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Node implements Comparable<Node> {
        int x; long w;
        public Node(int x, long w) {
            this.x = x;
            this.w = w;
        }
        @Override
        public int compareTo(Node o) {
            return Long.compare(this.w, o.w);
        }
    }
	static final long INF = 1_200_000_000_000_000_000L; // 가능한 최댓값은 1000억이니 넉넉하게 1조
	static int N, P, z;
	static List<Node>[] graph;
	static int[] point;
	static long[][] w, dp;
	static PriorityQueue<Node> pq = new PriorityQueue<>();
// ---------- main start ---------- //
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 정점의 수 N
		int M = Integer.parseInt(st.nextToken()); // 간선의 수 M
		
		graph = new ArrayList[N]; // 그래프 초기화
		for(int i = 0; i < N; i++) graph[i] = new ArrayList<>();
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken())-1; // 출발
            int v = Integer.parseInt(st.nextToken())-1; // 도착
            int w = Integer.parseInt(st.nextToken()); // 가중치
            
            graph[u].add(new Node(v, w));
            graph[v].add(new Node(u, w));
		} // 간선 정보 입력 완료
		
		st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken())-1;// 출발 정점
        z = Integer.parseInt(st.nextToken())-1;    // 도착 정점
		
		P = Integer.parseInt(br.readLine()) + 1; // 방문해야 하는 정점의 개수 P
		
		st = new StringTokenizer(br.readLine());
		point = new int[P + 1];
		
		point[0] = start; // 출발점 고정
		point[P] = z;
		for(int i = 1; i < P; i++) {
            point[i] = Integer.parseInt(st.nextToken())-1;
        } // 방문해야 하는 정점 입력 완료
		
		w = new long[P+1][P+1];
		for(int i = 0; i < P; i++) {
			long[] d = dijkstra(point[i]);
			for(int j = 0; j <= P; j++) {
				w[i][j] = d[point[j]];
			}
		}
		
		dp = new long[P][1 << P];
		for(int i = 0; i < P; i++) Arrays.fill(dp[i], -1);
		
		long result = tsp(0, 1);
		System.out.println(result == INF ? -1 : result);
	}
// ---------- main end ---------- //
	private static long[] dijkstra(int s) { // start, end
		pq.clear(); pq.add(new Node(s, 0));
		long[] dist = new long[N];
		Arrays.fill(dist, INF);
		dist[s] = 0;
		
		while(!pq.isEmpty()) { // s에서 e로 가는 다익스트라 수행
			Node curr = pq.poll();
			int x = curr.x;
			long w = curr.w;
			
			if(w > dist[x]) continue;
			
			for(Node n : graph[x]) {
				long tw = w + n.w;
				
				if(tw >= dist[n.x]) continue;
				dist[n.x] = tw;
				pq.add(new Node(n.x, tw));
			}
		}
		
		return dist;
	}
	
	private static long tsp(int idx, int visited) {
		if(visited == (1 << P) - 1) { // 모든 곳을 방문했다면 z로 가는 길 계산 후 리턴
			return w[idx][P];
		}
		// 이미 방문한 곳은 메모이제이션
		if(dp[idx][visited] != -1) return dp[idx][visited];
		
		dp[idx][visited] = INF;
		for(int i = 1; i < P; i++) {
			if((visited & (1 << i)) != 0) continue;
			if(w[idx][i] == INF) continue;
			
			dp[idx][visited] = Math.min(dp[idx][visited], tsp(i, visited | (1 << i)) + w[idx][i]);
		}
		
		return dp[idx][visited];
	}
}