import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
/*
 * 이 앱은 사용자들을 위해 사심 경로를 제공한다. 이 경로는 3가지 특징을 가지고 있다.
 * 
 * 	1. 사심 경로는 사심을 만족시키기 위해 남초 대학교와 여초 대학교들을 연결하는 도로로만 이루어져 있다.
 *  2. 다양한 사람과 미팅할 수 있도록, 어떤 대학교에서든 모든 대학교로 이동이 가능하다.
 *  3. 시간을 낭비하지 않고 미팅해야 하기 때문에, 최단 거리가 되어야 한다.
 *  
 *  모든 학교를 연결할 수 없으면 -1을 출력하자
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
		}
	}
	static int[] parent;
	static char[] gender;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 학교의 수 N
		int M = Integer.parseInt(st.nextToken()); // 학교를 연결하는 도로의 수 M
		
		parent = new int[N + 1];
		gender = new char[N + 1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			parent[i] = i;
			gender[i] = st.nextToken().charAt(0);
		} // 최상위 초기화 및 성별 입력 완료
		
		List<Node> list = new ArrayList<>();
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			
			list.add(new Node(A, B, W));
		} // 간선 정보 입력 완료
		
		Collections.sort(list);
		
		int count = 0, cost = 0;
		for(int i = 0; i < M; i++) {
			int a = list.get(i).a;
			int b = list.get(i).b;
			int w = list.get(i).w;
			
			// 같은 성별이거나, 이미 연결했으면 패스
			if(find(a) == find(b) || gender[a] == gender[b]) continue;
			
			union(a, b);
			count++;
			cost += w;
			
			if(count == N - 1) break;
		}
		
		System.out.println((count == N - 1) ? cost : -1);
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