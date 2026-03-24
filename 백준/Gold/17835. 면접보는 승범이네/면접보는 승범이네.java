import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
 * 면접자들은 서로 다른 N개의 도시에 거주한다. 승범이는 면접자들의 편의를 위해 거주 중인 N개의 도시 중 K개의 도시에 면접장을 배치했다.
 * 도시끼리는 단방향 도로로 연결되며, 거리는 서로 다를 수 있다. 어떤 두 도시 사이에는 도로가 없을 수도 여러 개가 있을 수도 있다.
 * 그러나 어떻게 해서든 적어도 하나의 면접장까지는 갈 수 있다.
 * 승범이는 가장 멀리서 오는 면접자에게 교통비를 주고자 한다.
 * 가장 먼 도시의 번호와 해당 도시에서 면접장까지의 거리를 출력하라
 */
public class Main {
	
	static class Node implements Comparable<Node> {
		int a; long c;
		public Node(int a, long c) {
			this.a = a;
			this.c = c;
		}
		@Override
		public int compareTo(Node o) {
			return Long.compare(this.c, o.c);
		}
	}
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken()); // 도시의 수 N
        int M = Integer.parseInt(st.nextToken()); // 도로의 수 M
        int K = Integer.parseInt(st.nextToken()); // 면접장의 수 K
        
        List<Node>[] graph = new ArrayList[N + 1]; // 그래프 초기화
        for(int i = 1; i <= N; i++) graph[i] = new ArrayList<>();
        
        for(int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int u = Integer.parseInt(st.nextToken()); // u 도시에서
        	int v = Integer.parseInt(st.nextToken()); // v 도시까지
        	int c = Integer.parseInt(st.nextToken()); // c만큼 걸린다
        	
        	graph[v].add(new Node(u, c)); // 역방향으로 연결
        }
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        long[] dist = new long[N + 1];
        Arrays.fill(dist, Long.MAX_VALUE);

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < K; i++) {
        	int a = Integer.parseInt(st.nextToken());
        	pq.add(new Node(a, 0));
        	dist[a] = 0;
        } // 면접장들을 모두 초기위치로
        
        while(!pq.isEmpty()) {
        	Node curr = pq.poll();
        	int x = curr.a;
        	long c = curr.c;
        	
        	if(c > dist[x]) continue; // 탈출 조건 x
        	
        	for(Node n : graph[x]) {
        		long tc = c + n.c;
        		
        		if(tc >= dist[n.a]) continue;
        		dist[n.a] = tc;
        		pq.add(new Node(n.a, tc));
        	}
        }
        
        int idx = 0;
        long value = 0;
        for(int i = 1; i <= N; i++) { // 가장 멀리있는 곳의 거리와 인덱스 탐색
        	if(dist[i] > 0 && dist[i] > value) {
        		idx = i; value = dist[i];
        	}
        }
        
        System.out.println(idx + "\n" + value);
    }
}