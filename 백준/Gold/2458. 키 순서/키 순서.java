import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 1번부터 N번까지 학생들에게 번호가 붙어있다. M번만큼 키를 비교한 자료가 있을 때
 * 자신의 순서를 확실하게 알 수 있는 학생은 몇 명이 있는가?
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 학생들의 수 N
		int M = Integer.parseInt(st.nextToken()); // 키 비교 횟수 M
		
		int result = 0;
		List<Integer>[] front = new ArrayList[N + 1]; // 정방향 탐색을 위해
		List<Integer>[] back = new ArrayList[N + 1];  // 역방향 탐색을 위해
		
		for(int i = 1; i <= N; i++) {
			front[i] = new ArrayList<>();
			back[i] = new ArrayList<>();
		} // 그래프 초기화 완료
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); // a 학생보다
			int b = Integer.parseInt(st.nextToken()); // b 학생이 더 크다.
			
			front[a].add(b); // 정방향 그래프에 추가
			back[b].add(a);  // 역방향 그래프에 추가
		}
		
		for(int i = 1; i <= N; i++) {
			int taller = bfs(front, i);
			int shorter = bfs(back, i);
			// 자신보다 큰 사람과 작은 사람 수의 합이 N-1이 되면 ++
			if(taller + shorter == N - 1) result++;
		}
		
		System.out.println(result);
	}

	private static int bfs(List<Integer>[] graph, int start) {
		boolean[] visited = new boolean[graph.length];
		visited[start] = true; // 초기값 방문 완료
		
		Queue<Integer> queue = new LinkedList<>();
		queue.add(start); // 초기값 추가
		
		int count = 0; // 자신을 기준으로 탐색할 수 있는 노드가 몇 개인가?
		while(!queue.isEmpty()) {
			int curr = queue.poll();
			
			for(int next : graph[curr]) {
				if(!visited[next]) {
					visited[next] = true;
					queue.add(next);
					count++;
				}
			}
		}
		
		return count;
	}
}