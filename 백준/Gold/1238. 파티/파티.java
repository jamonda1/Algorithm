import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
 * N명의 요원들이 1 ~ N까지의 도시에 흩어져 있다.
 * X번 도시에 모여있다가, 브리핑이 끝나면 각자의 도시로 돌아가야 한다.
 * 모두 비밀 임무를 수행하기 위해 최단 시간에 오고 가기를 원한다.
 * 도로 사이에는 일방 통행 도로 M개가 있다. 각 도로마다 이동하는 데 걸리는 시간은 다르다.
 * 
 * 모든 요원중에 X번 도시를 왕복하는 시간이 가장 오래 걸리는 요원의 시간은??
 */
public class Main {
	
	static PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
		@Override
		public int compare(int[] a, int[] b) {
			return Integer.compare(a[1], b[1]);
		}
	});
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 도시의 수 N
		int M = Integer.parseInt(st.nextToken()); // 도로의 수 M
		int X = Integer.parseInt(st.nextToken()); // 모이는 도시 X
		
		List<int[]>[] front = new ArrayList[N + 1];// 정방향 그래프
		List<int[]>[] back = new ArrayList[N + 1]; // 역방향 그래프
		int[] dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		for(int i = 1; i <= N; i++) {
			front[i] = new ArrayList<>();
			back[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken()); // 시작점
			int e = Integer.parseInt(st.nextToken()); // 도착점
			int t = Integer.parseInt(st.nextToken()); // 소요 시간
			
			front[s].add(new int[] {e, t});// 정방향
			back[e].add(new int[] {s, t}); // 역방향은 뒤집어서
		}
		
		int[] f = dijk(X, dist, front); // X에서 모든 곳으로
		int[] b = dijk(X, dist, back);	// 모든 곳에서 X로
		
		int result = 0;
		for(int i = 1; i <= N; i++) {
			int sum = f[i] + b[i]; // 왕복 시간
			result = Math.max(result, sum);
		}
		
		System.out.println(result);
	}
	
	private static int[] dijk(int start, int[] distOrg, List<int[]>[] list) {
		pq.clear();
		int[] dist = distOrg.clone();
		dist[start] = 0;
		pq.add(new int[] {start, 0});
		
		while(!pq.isEmpty()) {
			int[] curr = pq.poll();
			int x = curr[0];
			int t = curr[1];
			
			if(t > dist[x]) continue;
			
			for(int[] next : list[x]) {
				int nt = t + next[1];
				
				if(nt >= dist[next[0]]) continue;
				dist[next[0]] = nt;
				pq.add(new int[] {next[0], nt});
			}
		}
		// 탈출 조건 없이 끝까지 가자
		return dist;
	}
}