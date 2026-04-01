import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

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
	static int MAX = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine()); // 도시의 개수 N
		int M = Integer.parseInt(br.readLine()); // 버스의 개수 M
		
		List<Node>[] graph = new ArrayList[N + 1]; // 그래프 초기화
		for(int i = 1; i <= N; i++) graph[i] = new ArrayList<>();
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); // a에서
			int b = Integer.parseInt(st.nextToken()); // b까지
			int w = Integer.parseInt(st.nextToken()); // w원 필요
			graph[a].add(new Node(b, w));
		} // 간선 정보 입력 완료
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());// 출발지
		int end = Integer.parseInt(st.nextToken());  // 도착지
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[] dist = new int[N + 1]; // 가중치 저장
		int[] parent = new int[N + 1]; // 경로 저장
		Arrays.fill(dist, MAX);
		Arrays.fill(parent, MAX);
		
		pq.add(new Node(start, 0));
		dist[start] = 0;
		parent[start] = start;
		
		while(!pq.isEmpty()) {
			Node curr = pq.poll();
			int x = curr.x;
			int w = curr.w;
			
			if(w > dist[x]) continue;
			if(x == end) { // 도착했으면 시간 출력하고, 탈출
				bw.write(w + "\n"); break;
			}
			
			for(Node n : graph[x]) {
				int tw = w + n.w;
				
				if(tw >= dist[n.x]) continue;
				dist[n.x] = tw;
				parent[n.x] = x;
				pq.add(new Node(n.x, tw));
			}
		}
		
		Stack<Integer> stack = new Stack<>();
		int pivot = end;
		while(pivot != start) {
			stack.push(pivot);
			pivot = parent[pivot];
		} stack.push(start);
		
		bw.write(stack.size() + "\n"); // 경로의 개수 출력
		
		while(!stack.isEmpty()) {
			bw.write(stack.pop() + " ");
		}
		bw.close();
	}
}