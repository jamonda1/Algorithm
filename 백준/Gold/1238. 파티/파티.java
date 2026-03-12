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
 * N개의 숫자로 구분된 각각의 마을에 한 명의 학생이 살고 있다.
 * N명의 학생이 X번 마을에 모여서 파티를 벌이기로 했다. 이 마을 사이에는 총 M개의 단방향 도로들이 있다.
 * i번째 길을 지나는데 T[i]의 시간을 소비한다.
 * 
 * N명의 학생 중 가장 많은 시간을 소비하는 학생은 누구인가?
 */
public class Main {
	
	static int N, M, X;
	static List<int[]>[] graph;
	static PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
		@Override
		public int compare(int[] a, int[] b) {
			return a[1] - b[1];
		}
	});
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 마을 및 학생의 수 N
		M = Integer.parseInt(st.nextToken()); // 도로의 수 M
		X = Integer.parseInt(st.nextToken()); // 파티가 열리는 장소 X
		
		graph = new ArrayList[N + 1];
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			
			graph[A].add(new int[] {B, W});
		} // 단방향 도로 정보 입력 완료
		
		
		// 모든 학생은 i에서 출발해서 X에 최단으로 도착한다음, 다시 N으로 돌아가야 한다.
		int result = 0;
		for(int i = 1; i <= N; i++) {
			if(i == X) continue; // 자기 자신을 볼 필요 없다.
			
			int goCnt = go(i, new int[N + 1]); // X까지 얼마나 걸리는가
			int backCnt = back(i, new int[N + 1]); // 다시 집까지 얼마나 걸리는가
			
			result = Math.max(result, goCnt + backCnt);
		}
		
		System.out.println(result);
	}

	private static int go(int start, int[] dist) { // start에서 출발해서 X까지 가야 한다.
		dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		pq.clear();
		pq.add(new int[] {start, 0});
		dist[start] = 0;
		

		while(!pq.isEmpty()) {
			int[] curr = pq.poll();
			int s = curr[0];
			int c = curr[1];
			
			if(c > dist[s]) continue;
			if(s == X) break;
			
			for(int[] next : graph[s]) {
				int tn = next[0];
				int tc = c + next[1];
				
				if(tc >= dist[tn]) continue;
				
				dist[tn] = tc;
				pq.add(new int[] {tn, tc});
			}
		}

		return dist[X];
	}
	
	private static int back(int end, int[] dist) { // X에서 출발해서 end까지 가야 한다.
		dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		pq.clear();
		pq.add(new int[] {X, 0});
		dist[X] = 0;
		
		while(!pq.isEmpty()) {
			int[] curr = pq.poll();
			int e = curr[0];
			int c = curr[1];
			
			if(c > dist[e]) continue;
			if(e == end) break;
			
			for(int[] next : graph[e]) {
				int tn = next[0];
				int tc = c + next[1];
				
				if(tc >= dist[tn]) continue;
				
				dist[tn] = tc;
				pq.add(new int[] {tn, tc});
			}
		}
		
		return dist[end];
	}
}