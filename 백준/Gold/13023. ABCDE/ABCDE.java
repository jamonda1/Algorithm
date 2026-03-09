import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, result = 0;
	static List<Integer>[] graph;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 사람의 수 N
		M = Integer.parseInt(st.nextToken()); // 친구 관계의 수 M
		
		visited = new boolean[N];
		graph = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			graph[A].add(B);
			graph[B].add(A);
		}
		
		
		for(int i = 0; i < N; i++ ) {
			visited[i] = true;
			dfs(1, i); // 한 곳에서 출발해서 끝까지 갈 수 있어야 한다.
			visited[i] = false;
			
			if(result == 1) break;
		}
		
		System.out.println(result);
	}

	private static void dfs(int depth, int idx) {
		if(depth == 5) { // depth가 5까지만 가면 된다.
			result = 1;
			return;
		}

		for(int node : graph[idx]) {
			if(!visited[node]) {
				visited[node] = true;
				dfs(depth + 1, node);
				
				visited[node] = false;
				
				if(result == 1) return;
			}
		}
	}
}