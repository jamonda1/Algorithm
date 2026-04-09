import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
/*
 * [거의 최단 경로]란 최단 경로에 포함되지 않은 길로만 이루어진 경로 중의 최단 경로를 말한다.
 * 각 도시들과 단방향 길의 정보가 주어진다. 이때 거의 최단 경로를 구해보자. 거최경이 없으면 -1을 출력
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
		} // 오름차순
	}
	static int N, M, S, D, MAX = Integer.MAX_VALUE;
	static PriorityQueue<Node> pq = new PriorityQueue<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		while(true) {
			pq.clear();
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 장소의 수 N
			M = Integer.parseInt(st.nextToken()); // 도로의 수 M
			if(N == 0 && M == 0) break; // 종료 조건
			
			st = new StringTokenizer(br.readLine());
			S = Integer.parseInt(st.nextToken()); // 출발점 S
			D = Integer.parseInt(st.nextToken()); // 도착점 D
			
			List<Node>[] graph = new ArrayList[N];
			List<Integer>[] pass = new ArrayList[N];
			for(int i = 0; i < N; i++) {
				graph[i] = new ArrayList<>();
				pass[i] = new ArrayList<>();
			}
			
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken()); // 출발 도시
				int v = Integer.parseInt(st.nextToken()); // 도착 도시
				int p = Integer.parseInt(st.nextToken()); // 거리
				graph[u].add(new Node(v, p));
			} // 도로 정보 입력 완료
			
			int[] dist = new int[N];
			Arrays.fill(dist, MAX);
			
			pq.add(new Node(S, 0));
			dist[S] = 0; // 초기값 설정 완료
			
			while(!pq.isEmpty()) { // 다익스트라 수행
				Node curr = pq.poll();
				int x = curr.x; // 지금 위치
				int w = curr.w; // 누적 거리
				
				if(w > dist[x]) continue;
				for(Node n : graph[x]) {
					int tx = n.x;
					int tw = w + n.w;
					
					if(tw == dist[tx]) { // 기록된 시간과 동일한 값으로 왔을 때
						pass[tx].add(x);
						continue;
					} else if(tw < dist[tx]) { // 기록된 시간보다 더 빨리 도착
						pass[tx].clear();
						pass[tx].add(x);
						dist[tx] = tw;
						pq.add(new Node(tx, tw)); // 갱신될 때만 pq에 삽입하면 된다.
					}
				}
			} // 다익스트라 종료

			Set<Integer>[] hs = find(pass);

			int result = go(graph, hs);
			bw.write(result + "\n");
		} // 전체 테스트 케이스 종료
		bw.close();
	}

	private static int go(List<Node>[] graph, Set<Integer>[] hs) {
		pq.clear();
		int[] dist = new int[N];
		Arrays.fill(dist, MAX);
		
		pq.add(new Node(S, 0));
		dist[S] = 0; // 초기값 설정 완료
		
		while(!pq.isEmpty()) {
			Node curr = pq.poll();
			int x = curr.x; // 지금 위치
			int w = curr.w; // 누적 거리

			if(w > dist[x]) continue;
			if(x == D) return w; // 정답 리턴
			
			for(Node n : graph[x]) {
				int tw = w + n.w;
				if(hs[x].contains(n.x) || tw >= dist[n.x]) continue;
				
				dist[n.x] = tw;
				pq.add(new Node(n.x, tw));
			}
		}
		
		return -1; // 여기까지 오면 거최경 x
	}

	private static Set<Integer>[] find(List<Integer>[] pass) {
		Set<Integer>[] hs = new HashSet[N];
		for(int i = 0; i < N; i++) hs[i] = new HashSet<>();
		Queue<Integer> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		
		queue.add(D);
		
		while(!queue.isEmpty()) { // 최단 경로만 뽑아서 저장
			int x = queue.poll();
			
			for(int n : pass[x]) {
				if(visited[n][x]) continue;
				visited[n][x] = true;
				hs[n].add(x);
				queue.add(n);
			}
		}
		return hs;
	}
}