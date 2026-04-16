import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 문제의 수 N
		int M = Integer.parseInt(st.nextToken()); // 정보의 개수 M
		
		int[] degree = new int[N + 1];
		
		List<Integer>[] graph = new ArrayList[N + 1];
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken()); // A번 문제는
			int B = Integer.parseInt(st.nextToken()); // B번 문제보다 먼저!!
			graph[A].add(B);
			degree[B]++;
		}
		
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int i = 1; i <= N; i++) {
			if(degree[i] == 0) pq.add(i);
		} // 난이도는 낮을 걸 먼저 풀어야 한다.
		
		while(!pq.isEmpty()) {
			int curr = pq.poll();
			bw.write(curr + " ");
			
			for(int next : graph[curr]) {
				degree[next]--;
				if(degree[next] == 0) {
					pq.add(next);
				}
			}
		}
		
		bw.flush();
		bw.close();
	}
}