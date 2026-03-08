import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 학생이 N명인 학교에 입학했다. 준석이는 친구비를 통해 친구를 만들고자 한다.
 * 학생 i에게 A[i]만큼의 돈을 주면 그 학생은 1달간 친구가 되어준다. 준석이는 총 k원의 돈이 있다.
 * 준석이는 친구의 친구는 친구이기 때문에 모든 친구에게 돈을 주지 않아도 된다.
 */
public class Main {
	
	static int[] parent, cost;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 학생들의 수
		int M = Integer.parseInt(st.nextToken()); // 친구 관계의 수
		int K = Integer.parseInt(st.nextToken()); // 전재산
		
		parent = new int[N + 1]; // 초기값을 자기 자신으로
		for(int i = 1; i <= N; i++) parent[i] = i;
		
		boolean[] visited = new boolean[N + 1];
		cost = new int[N + 1]; // 학생들이 원하는 친구비
		
		st = new StringTokenizer(br.readLine()); // 친구비 기입 완료
		for(int i = 1; i <= N; i++) cost[i] = Integer.parseInt(st.nextToken());

		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken()); // 둘은 친구다
			int B = Integer.parseInt(st.nextToken());

			union(A, B); // 친구비가 더 적은 쪽을 대표로 두자
		} // union-find 수행 완료
		
		int sum = 0;
		for(int i = 1; i <= N; i++) {
			int index = find(i); 
			
			if(!visited[index]) {
				visited[index] = true;
				sum += cost[index];
			}
		}
		
		System.out.println((sum <= K) ? sum : "Oh no");
	}

	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		// 친구비가 더 적은 애가 대표가 되어야 한다.
		if(cost[a] > cost[b]) parent[a] = b;
		else parent[b] = a;
	}

	private static int find(int a) {
		if(parent[a] == a) return a;
		
		return parent[a] = find(parent[a]);
	}
}