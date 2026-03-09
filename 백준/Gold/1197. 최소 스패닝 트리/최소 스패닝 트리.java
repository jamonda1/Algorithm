import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
/*
 * 최소 스패닝 트래를 구해보자.
 * A 정점, B 정점, C 가중치가 주어진다. 그것을 잘 활용해서 최소 비용으로 최소 스패닝 트리를 만들어보자
 * (스패닝 트리 : 모든 정점이 다 연결되어 있고, 순환이 없는 트리)
 */
public class Main {
	
	static class Node implements Comparable<Node> {
		int a;
		int b;
		int w;
		
		public Node(int a, int b, int w) {
			this.a = a;
			this.b = b;
			this.w = w;
		}
		
		@Override
		public int compareTo(Node n) {
			return this.w - n.w;
		} // 가중치가 작은 순으로 정렬
	}
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken()); // 노드의 개수 V
		int E = Integer.parseInt(st.nextToken()); // 간선의 개수 E
		
		parent = new int[V + 1]; // 최상위 초기화
		for(int i = 1; i <= V; i++) parent[i] = i;
		
		List<Node> list = new ArrayList<>(); // 간선들의 정보 저장

		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			
			list.add(new Node(A, B, W));
		} // 간선 정보 입력 완료
		
		Collections.sort(list); // 가중치가 작은 순으로 정렬
		
		int result = 0;
		for(int i = 0; i < E; i++) {
			int a = list.get(i).a;
			int b = list.get(i).b;
			int w = list.get(i).w;
			// 최상위 부모가 같으면 이미 연결된 것이므로 패스
			if(find(a) == find(b)) continue;
			
			union(a, b);
			result += w;
		}
		
		System.out.println(result);
	}

	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a != b) parent[b] = a;
	}

	private static int find(int a) {
		if(parent[a] == a) return a;
		
		return parent[a] = find(parent[a]);
	}
}