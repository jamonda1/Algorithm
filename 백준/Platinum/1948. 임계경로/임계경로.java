import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * '한 걸음'에서 출발하면 어떤 도시에도 갈 수 있고, 어떤 도시에서든 '로마'로 도착할 수 있다. 이때 모든 길은 일방통행이다.
 * 모든 사람이 '한 걸음'에서 출발해서 로마에 모든 경로를 지나면서 도착한다. 이들이 모두 로마에 도착하는 데 얼마의 시간이 걸리는가?
 * 그리고 가장 오래 달린 사람들이 지난 길에 황금을 칠해야 한다. 칠할 도로의 수는 몇 개인가?
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
			return Integer.compare(o.w, this.w);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine()); // 도시의 개수 N
		int M = Integer.parseInt(br.readLine()); // 도로의 개수 M
		
		List<Node>[] graph = new ArrayList[N + 1]; // 그래프 초기화 완료
		List<Integer>[] passed = new ArrayList[N + 1]; // 이동 경로 저장
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
			passed[i] = new ArrayList<>();
		}
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()); // 출발 도시
			int v = Integer.parseInt(st.nextToken()); // 도착 도시
			int w = Integer.parseInt(st.nextToken()); // 소요 시간
			graph[u].add(new Node(v, w));
		} // 간선 정보 입력 완료
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());// 출발 지점
		int end = Integer.parseInt(st.nextToken());  // 도착 지점
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[] dist = new int[N + 1];
		Arrays.fill(dist, -1); // 가중치 배열 초기화
		
		pq.add(new Node(start, 0));
		dist[start] = 0; // 초기값 설정 완료
		
		while(!pq.isEmpty()) {
			Node curr = pq.poll();
			int x = curr.x; // 지금 위치
			int w = curr.w; // 누적 시간
			
			if(w < dist[x]) continue;
			if(x == end) continue;
			
			for(Node n : graph[x]) {
				int tx = n.x; // 다음 경로
				int tw = w + n.w; // 누적 시간
				
				if(tw == dist[tx]) { // 같은 속도로 방문했을 경우
					passed[tx].add(x);// 경로만 추가
					continue;
				} else if(tw > dist[tx]) {
					// 새롭게 갱신될 때 해당 경로의 기존 리스트는 초기화해야 한다.
					passed[tx].clear();
					passed[tx].add(x);
					dist[tx] = tw;
					pq.add(new Node(tx, tw));
				}
			}
		} // 다익스트라 종료
		
		System.out.println(dist[end]);
		printResult(passed, start, end); // 역추적 후 도로 개수 출력
	}

	private static void printResult(List<Integer>[] passed, int start, int end) {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[passed.length + 1];
		queue.add(end); // 로마에서 출발
		visited[end] = true;
		
		int result = 0;
		while(!queue.isEmpty()) { // 역추적 시작
			int x = queue.poll();
			// x에 도착한 정점의 수 더해주기
			result += passed[x].size();
			
			for(int n : passed[x]) {
				if(visited[n]) continue;
				visited[n] = true;
				queue.add(n);
			}
		}
		
		System.out.println(result);
	}
}