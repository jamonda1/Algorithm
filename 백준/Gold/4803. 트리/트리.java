import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
/*
 * 두 정점 사이에 경로가 있다면, 두 정잠은 연결되어 있다고 한다.
 * 트리는 사이클이 없는 연결 요소이다.
 * 그래프가 주어졌을 때, 트리의 개수를 세는 프로그램을 작성해보자.
 */
public class Main {
	
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int tc = 1;
		for(;;) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()); // 정점의 개수
			int m = Integer.parseInt(st.nextToken()); // 간선의 개수
			
			if(n == 0 && m == 0) break;
			
			parent = new int[n + 1]; // 최상위 부모는 자기 자신
			for(int i = 1; i <= n; i++) parent[i] = i;
			
			boolean[] visited = new boolean[n + 1];
			
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				
				if(find(A) == find(B)) { // 최상위 부모가 같은 것끼리 연결하면 순환 발생하니깐 트리x
					union(0, A);
				} // 더 작은 값에 연결하는 것이기 때문에, 순환이 발생하면 0으로 union
				
				union(A, B); // 노드 연결하기
			}
			
			int count = 0;
			for(int i = 1; i <= n; i++) {
				int index = find(i);
				if(index == 0) continue;
				if(!visited[index]) { // 방문하지 않은 최상위 노드만 방문
					visited[index] = true;
					count++;
				}
			}
			
			if(count == 0) { // 트리가 존재하지 않을 때
				bw.write("Case " + tc + ": " + "No trees.\n");
			} else if(count == 1) { // 트리가 단 하나만 존재할 때
				bw.write("Case " + tc + ": " + "There is one tree.\n");
			} else { // 트리가 많을 때
				bw.write("Case " + tc + ": " + "A forest of " + count + " trees.\n");
			}
			tc++;
		} // 전체 테스트 케이스 종료
		bw.flush();
		bw.close();
	}

	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		// 통일성 유지를 위해 더 작은 쪽 등록
		if(a > b) parent[a] = b;
		if(a < b) parent[b] = a;
	}

	private static int find(int a) {
		if(parent[a] == a) return a;
		
		return parent[a] = find(parent[a]);
	}
}