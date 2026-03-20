import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
 * 출발점에서 마산까지 가야 한다. 가는 도중에 건우를 데리고 갈 수 있을까?
 * 최단경로에 도중에 건우가 있다면, SAVE HIM을 출력한다. 없다면 GOOD BYE
 */
public class Main {
	
	static class Node implements Comparable<Node> {
		int a, w; boolean f;
		public Node(int a, int w, boolean f) {
			this.a = a;
			this.w = w;
			this.f = f;
		}
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.w, o.w);
		}
	}
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int V = Integer.parseInt(st.nextToken()); // 정점의 개수 V
        int E = Integer.parseInt(st.nextToken()); // 간선의 개수 E
        int P = Integer.parseInt(st.nextToken()); // 건우의 위치 P
        
        List<Node>[] graph = new ArrayList[V + 1];
        for(int i = 1; i <= V; i++) {
        	graph[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < E; i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken()); // a와
        	int b = Integer.parseInt(st.nextToken()); // b 사이의 거리
        	int c = Integer.parseInt(st.nextToken()); // c
        	
        	graph[a].add(new Node(b, c, false));
        	graph[b].add(new Node(a, c, false));
        } // 간선 정보 입력 완료
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        pq.add(new Node(1, 0, P == 1 ? true : false));
        dist[1] = 0;
        
        boolean f = false;
        while(!pq.isEmpty()) {
        	Node curr = pq.poll();
        	int x = curr.a;
        	int w = curr.w;
        	boolean s = curr.f;
        	if(w > dist[x]) continue;
        	if(x == V) {
        		if(s) f = true;
        	}
        	
        	for(Node n : graph[x]) {
        		int tc = w + n.w;
        		
        		if(tc > dist[n.a]) continue;
        		boolean tf = (P == n.a || s) ? true : false;
        		
        		dist[n.a] = tc;
        		pq.add(new Node(n.a, tc, tf));
        	}
        }
        
        System.out.println(f ? "SAVE HIM" : "GOOD BYE");
    }
}