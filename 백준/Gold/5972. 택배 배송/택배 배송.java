import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 헛간의 수 N
		int M = Integer.parseInt(st.nextToken()); // 소들의 길 M
		
		List<int[]>[] list = new ArrayList[N + 1];
		for(int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		} // 리스트 초기화
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			list[a].add(new int[] {b, w});
			list[b].add(new int[] {a, w});
		} // 간선 정보 입력
		
		int[] dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				return Integer.compare(a[1], b[1]);
			}
		});
		pq.add(new int[] {1, 0});
		dist[1] = 0;
		
		while(!pq.isEmpty()) {
			int[] curr = pq.poll();
			int x = curr[0];
			int w = curr[1];
			
			if(w > dist[x]) continue;
			if(x == N) {
				System.out.println(w);
				return;
			}
			
			for(int[] next : list[x]) {
				int tw = w + next[1];
				
				if(tw >= dist[next[0]]) continue;
				dist[next[0]] = tw;
				pq.add(new int[] {next[0], tw});
			}
		}
	}
}