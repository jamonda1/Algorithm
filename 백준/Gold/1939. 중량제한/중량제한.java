import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * N개의 섬으로 이루어진 나라가 있다. 이 중 몇 개의 섬 사이에는 다리가 설치되어 있다.
 * 회사는 두 개의 섬에 공장을 세우고 물건을 생산하고 있다. 도중에 A공장에서 B공장으로 물건을 수송할 일이 생긴다.
 * 그런데 각 다리에는 중량 제한이 있어서 무턱대고 옮길 수가 없다.
 * 한 번의 이동에서 옮길 수 있는 중량의 최댓값을 구해라! (경로는 항상 존재)
 */
public class Main {
	
	static class Node implements Comparable<Node> {
		int x; int w;
		public Node(int x, int w) {
			this.x = x;
			this.w = w;
		}
		@Override
		public int compareTo(Node o) {
			return Integer.compare(o.w, this.w);
		}
	}
	static int MAX = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 섬의 개수 N
		int M = Integer.parseInt(st.nextToken()); // 다리의 개수 M
		
		Map<Integer, Integer>[] graph = new HashMap[N + 1]; // 그래프 초기화
		for(int i = 1; i <= N; i++) graph[i] = new HashMap<>();
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken()); // 섬 A와
			int B = Integer.parseInt(st.nextToken()); // 섬 B를 잇는
			int C = Integer.parseInt(st.nextToken()); // 다리의 중량 C
			
			if(!graph[A].containsKey(B) || graph[A].get(B) < C) { // 해당 값이 그래프에 없거나, 있어도 더 C보다 더 작은 값이면?
				graph[A].put(B, C);
			}
			if(!graph[B].containsKey(A) || graph[B].get(A) < C) { // 해당 값이 그래프에 없거나, 있어도 더 C보다 더 작은 값이면?
				graph[B].put(A, C);
			}
		} // 다리 정보 입력 완료
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());// 출발 공장
		int end = Integer.parseInt(st.nextToken());  // 도착 공장
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[] dist = new int[N + 1];
		Arrays.fill(dist, -1);
		
		pq.add(new Node(start, MAX));
		dist[start] = MAX;
		
		int result = 0;
		while(!pq.isEmpty()) {
			Node curr = pq.poll();
			int x = curr.x;
			int w = curr.w;
			
			if(w < dist[x]) continue;
			if(x == end) { // 가장 작았던 값들 중에서 가장 큰 값을 출력해야 한다.
				result = Math.max(result, w);
				continue;
			}
			
			for(Entry<Integer, Integer> m : graph[x].entrySet()) {
				int key = m.getKey();
				int tw = Math.min(w, m.getValue());
				
				if(tw <= dist[key]) continue;
				dist[key] = tw;
				pq.add(new Node(key, tw));
			}
		}
		
		System.out.println(result);
	}
}