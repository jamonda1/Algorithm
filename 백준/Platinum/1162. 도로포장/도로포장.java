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
		int x, c; long t;
		public Node(int x, int c, long t) {
			this.x = x;
			this.c = c;
			this.t = t;
		}
		@Override
		public int compareTo(Node o) {
			return Long.compare(this.t, o.t);
		}
	}
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken()); // 도시의 수 N
        int M = Integer.parseInt(st.nextToken()); // 도로의 수 M
        int K = Integer.parseInt(st.nextToken()); // 포장 가능 도로의 수 K
        
        List<Node>[] graph = new ArrayList[N + 1]; // 그래프 초기화
        for(int i = 1; i <= N; i++) graph[i] = new ArrayList<>();
        
        for(int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	int c = Integer.parseInt(st.nextToken());
        	
        	graph[a].add(new Node(b, 0, c)); // 양방향 도로이므로 양방향 연결
        	graph[b].add(new Node(a, 0, c));
        } // 그래프 입력 완료
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        long[][] dist = new long[K + 1][N + 1];
        for(int i = 0; i <= K; i++) Arrays.fill(dist[i], Long.MAX_VALUE);
        
        pq.add(new Node(1, 0, 0));
        dist[0][1] = 0;
        
        long result = Long.MAX_VALUE;
        while(!pq.isEmpty()) {
        	Node curr = pq.poll();
        	int x = curr.x; // 지금 위치
        	int c = curr.c; // 도로 포장 횟수
        	long t = curr.t;// 소요 시간
        	
        	if(t > dist[c][x]) continue;
        	
        	if(x == N) {
        		result = Math.min(result, t);
        		continue;
        	}
        	
        	for(Node n : graph[x]) { // 도로를 포장하는 경우의 수와 포장하지 않는 경우의 수
        		int tx = n.x;
        		long tt = t + n.t;
        		
        		if(c < K && t < dist[c + 1][tx]) { // 포장 가능
        			dist[c + 1][tx] = t;
        			pq.add(new Node(tx, c + 1, t));
        		}
        		
        		if(tt >= dist[c][tx]) continue;
        		dist[c][tx] = tt;
        		pq.add(new Node(tx, c, tt));
        	}
        }
        
        System.out.println(result);
    }
}