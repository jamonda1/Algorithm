import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
/*
 * 컴퓨터와 컴퓨터를 모두 연결하는 네트워크를 구축하고자 한다.
 * 하지만 허브가 없어서 컴퓨터와 컴퓨터를 직접 연결해야 한다.
 * a와 b, b와 c가 연결되어 있으면, a와 c도 연결되어 있다.
 * 
 * 그런데 이왕이면 비용을 최소로 하고 싶다. 모든 컴퓨터를 연결할 수 없는 경우는 없다.
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
		} // 가중치가 더 작은 게 우선
	}
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine()); // 노드의 수 N
		int M = Integer.parseInt(br.readLine()); // 간선의 수 M
		
		parent = new int[N + 1]; // 최상위 초기화
		for(int i = 1; i <= N; i++) parent[i] = i;
		
		List<Node> list = new ArrayList<>();
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken()); // A에서
			int B = Integer.parseInt(st.nextToken()); // B까지
			int W = Integer.parseInt(st.nextToken()); // W 소비
			
			list.add(new Node(A, B, W));
		} // 리스트에 간선 정보 입력 완료
		
		Collections.sort(list); // 가중치 기준 오름차순 정렬
		
		int result = 0, edge = 0;
		for(int i = 0; i < M; i++) {
			int a = list.get(i).a;
			int b = list.get(i).b;
			int w = list.get(i).w;
			// 이미 연결된 값은 패스
			if(find(a) == find(b)) continue;
			
			union(a, b);
			result += w;
			edge++;
			// 신장 트리의 간선은 N-1이다. 이게 완성되면 바로 break
			if(edge == N - 1) break;
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