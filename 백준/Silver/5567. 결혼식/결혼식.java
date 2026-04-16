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
		
		// 상근이의 학번은 1이다
		int N = Integer.parseInt(br.readLine()); // 상근이의 동기 수 N
		int M = Integer.parseInt(br.readLine()); // 리스트의 길이
		
		List<Integer>[] graph = new ArrayList[N + 1];
		boolean[] visited = new boolean[N + 1];
		
		for(int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
		} // 그래프 초기화 완료
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph[a].add(b); // 양방향
			graph[b].add(a);
		}
		
		Queue<Integer> queue = new LinkedList<>();
		queue.add(1);
		visited[1] = true;
		
		int friend = 0;
		for(int i = 0; i < 2; i++) {
			int size = queue.size();
			
			for(int j = 0; j < size; j++) {
				int curr = queue.poll();
				
				for(int temp : graph[curr]) {
					if(visited[temp]) continue;
					visited[temp] = true;
					queue.add(temp);
					friend++; // 친구를 큐에 추가했으니 ++
				}
			}
		}
		
		System.out.println(friend);
	}
}