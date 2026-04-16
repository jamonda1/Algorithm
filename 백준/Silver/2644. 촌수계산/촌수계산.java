import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// dfs든 bfs든 a부터 b까지의 거리를 구하면 된다.
		// 둘이 연결되어 있지 않다면 -1을 출력
		Queue<Integer> queue = new LinkedList<>();
		
		int n = Integer.parseInt(br.readLine()); // 가족 구성원의 수 N
		boolean[] visited = new boolean[n+1];    // 0번 노드는 사용하지 않는다.
		List<Integer>[] graph = new ArrayList[n+1]; // 0번 노드는 사용하지 않는다.
		for(int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<>();
		} // 그래프 초기화 완료
		
		st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken()); // A 사람과 B 사람 사이의
		int b = Integer.parseInt(st.nextToken()); // 촌수를 구해야 한다.
		
		int m = Integer.parseInt(br.readLine()); // 가족들간의 관계대수 M
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine()); // x가 y의 부모이다.
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			graph[x].add(y); // 양방향으로 연결
			graph[y].add(x);
		}
		
		queue.add(a); // 초기값인 a에서 출발해서 b를 찾으러 가보자!
		visited[a] = true; // 시작 정점은 방문 ok
		
		int count = 0; // 촌수 저장
		boolean f = false; // b를 찾으면 true로 변환
		first : while(!queue.isEmpty()) {
			int size = queue.size();
			count++;
			for(int i = 0; i < size; i++) {
				int current = queue.poll();
				
				for(int temp : graph[current]) {
					if(visited[temp]) continue;
					if(temp == b) { // b를 찾았으면 바로 전체 반복문 탈출!!!!
						f = true;
						break first;
					}
					visited[temp] = true; // 방문 처리 후에 큐에 담기
					queue.add(temp);
				}
			}
		}
		System.out.println(f ? count : -1);
	}
}