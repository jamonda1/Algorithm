import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
/*
 * N개의 마을로 이루어진 나라가 있고, 각 마을에는 1부터 N까지 번호가 부여되어 있다.
 * 마을 간에는 도로가 있는데, 도로마다 걸리는 시간이 다르다.
 * 1번 마을에 있는 음식점에서 각 마을로 음식 배달을 하려고 한다.
 * 그런데 N개의 마을 중에서 K시간 이하로 배달이 가능한 마을에서만 주문을 받고자 한다.
 */
class Solution {
	public int solution(int N, int[][] road, int K) {
		PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				return a[1] - b[1];
			}
		});
		
		// 마을의 개수 N
		List<int[]>[] list = new ArrayList[N + 1];
		int[] dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		for(int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < road.length; i++) {
			int a = road[i][0];
			int b = road[i][1];
			int w = road[i][2];
			
			list[a].add(new int[] {b, w});
			list[b].add(new int[] {a, w});
		} // 간선 정보 입력 완료
		
		queue.add(new int[] {1, 0});
		dist[1] = 0;
        
		int count = 0;
		while(!queue.isEmpty()) {
			int[] curr = queue.poll();
			int x = curr[0];
			int time = curr[1];
			
			if(time > dist[x]) continue;
			if(time <= K) count++;
			
			for(int[] next : list[x]) {
				int nx = next[0];
				int nc = time + next[1];
				
				if(nc >= dist[nx]) continue;
				dist[nx] = nc;
				queue.add(new int[] {nx, nc});
			}
		}
		
		return count;
    }
}
