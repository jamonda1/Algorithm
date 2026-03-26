import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
 * 예은이는 낙하산을 타고 한 정점에 내릴 수 있다. 수색 범위가 주어지는데, 해당 범위 안에 있는 아이템은 모두 습득할 수 있다.
 * 이럴 경우 최대한 많이 얻을 수 있는 아이템의 개수를 구해라
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
	static int N, M, result = 0, MAX = Integer.MAX_VALUE;
	static int[] item;
	static List<Node>[] graph;
	static PriorityQueue<Node> pq = new PriorityQueue<>();
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken()); // 지역의 개수 N
        M = Integer.parseInt(st.nextToken()); // 수색 범위 M
        int R = Integer.parseInt(st.nextToken()); // 길의 개수 R
        
        graph = new ArrayList[N + 1];
        item = new int[N + 1]; // 각 지역에 존재하는 아이템 개수
        int[] distOrg = new int[N + 1]; // 가중치 저장 배열
        
        st = new StringTokenizer(br.readLine()); // 아이템 개수 저장 완료
        for(int i = 1; i <= N; i++) {
        	graph[i] = new ArrayList<>();
        	item[i] = Integer.parseInt(st.nextToken());
        	distOrg[i] = MAX;
        }
        
        for(int i = 0; i < R; i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	int w = Integer.parseInt(st.nextToken());
        	graph[a].add(new Node(b, w));
        	graph[b].add(new Node(a, w));
        } // 양방향으로 입력 완료
        
        for(int i = 1; i <= N; i++) {
        	pq.clear(); // 배열 복사해서 전달하기
        	dijkstra(i, distOrg.clone());
        }
        
        System.out.println(result);
    }

	private static void dijkstra(int start, int[] dist) {
		pq.add(new Node(start, 0));
		dist[start] = 0;
		
		while(!pq.isEmpty()) { // w가 M을 넘어서면 안 된다. 딱 M까지만 탐색 가능
			Node curr = pq.poll();
			int x = curr.x;
			int w = curr.w;
			
			if(w > dist[x]) continue;
			
			for(Node n : graph[x]) {
				int tx = n.x;
				int tw = w + n.w; // 이게 M을 넘으면 안 된다.
				
				if(tw > M || tw >= dist[tx]) continue;
				dist[tx] = tw;
				pq.add(new Node(tx, tw));
			}
		}
		
		int score = 0; // 탐색이 끝났으면 어디를 방문했는지 체크하기
		for(int i = 1; i <= N; i++) {
			if(dist[i] != MAX) score += item[i];
		}
		result = Math.max(result, score);
	}
}