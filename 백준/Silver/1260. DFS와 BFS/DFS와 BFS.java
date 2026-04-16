import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 그래프를 DFS로 탐색한 결과와 BFS로 탐색한 결과를 출력하라
 */
public class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int N, M, V;
	static List<Integer>[] graph;
	static boolean[] dfsVisited, bfsVisited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 정점의 개수 N
		M = Integer.parseInt(st.nextToken()); // 간선의 개수 M
		V = Integer.parseInt(st.nextToken()); // 시작 정점의 번호 V
		
		graph = new ArrayList[N + 1];
		dfsVisited = new boolean[N + 1]; // DFS 방문 배열
		bfsVisited = new boolean[N + 1]; // BFS 방문 배열
		
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			graph[A].add(B);
			graph[B].add(A);
		} // 그래프 입력 완료
		
		dfs(0, new int[N], V);
		bw.write("\n");
		bfs(); // 너비 우선 탐색
		
		bw.flush();
		bw.close();
	}
	
	private static void dfs(int depth, int[] arr, int v) throws IOException {
		if(dfsVisited[v]) return;
		
		dfsVisited[v] = true;
		bw.write(v + " ");
		
		Collections.sort(graph[v]); // 접근할 수 있는 곳이 여럿이면 오름차순으로
		for(int i : graph[v]) {
			arr[depth] = v;
			dfs(depth + 1, arr, i);
		}
	}

	private static void bfs() throws IOException {
		Queue<Integer> queue = new LinkedList<>();
		
		queue.add(V);
		bfsVisited[V] = true;
		
		while(!queue.isEmpty()) {
			int curr = queue.poll();
			bw.write(curr + " ");
			
			Collections.sort(graph[curr]); // 접근할 수 있는 곳이 여럿이면 오름차순으로
			for(int i : graph[curr]) {
				if(!bfsVisited[i]) {
					bfsVisited[i] = true;
					queue.add(i);
				}
			}
		}
	}
}
