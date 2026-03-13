import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N, result;
	static int[] endPoint;
	static char[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
//		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
//			@Override
//			public int compare(int[] a, int[] b) {
//				return a[2] - b[2];
//			}
//		});
		Queue<int[]> pq = new LinkedList<>();
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			pq.clear();
			int[] startPoint = new int[3];
			endPoint = new int[2];
			result = Integer.MAX_VALUE;
			
			N = Integer.parseInt(br.readLine()); // 바다의 크기 N
			
			map = new char[N][N];
			visited = new boolean[N][N];
			int[][] dist = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				Arrays.fill(dist[i], Integer.MAX_VALUE);
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = st.nextToken().charAt(0);
				}
			} // 맵 정보 입력 완료
			
			st = new StringTokenizer(br.readLine()); // 출발점 입력 완료
			for(int i = 0; i < 2; i++) startPoint[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine()); // 도착점 입력 완료
			for(int i = 0; i < 2; i++) endPoint[i] = Integer.parseInt(st.nextToken());
			
			// ----- 탐색 시작 ----- //
			
			visited[startPoint[0]][startPoint[1]] = true; // 출발점 방문 완료
			pq.add(startPoint);
			
			while(!pq.isEmpty()) {
				int[] curr = pq.poll();
				int x = curr[0];
				int y = curr[1];
				int time = curr[2];
				
//				if(time > dist[x][y]) continue;
				if(x == endPoint[0] && y == endPoint[1]) {
					result = time; break;
				}
				
				for(int i = 0; i < 4; i++) {
					int tx = x + dr[i];
					int ty = y + dc[i];
					boolean checkTime = (time - 2) % 3 == 0 ? true : false;
					
					if(tx < 0 || N <= tx || ty < 0 || N <= ty) continue;// 범위 밖
					if(visited[tx][ty] || map[tx][ty] == '1') continue; // 방문했거나 장애물
					
					if(map[tx][ty] == '2') {
						if(checkTime) {
							visited[tx][ty] = true;
							pq.add(new int[] {tx, ty, time + 1});
						} else {
							pq.add(new int[] {x, y, time + 1});
						}
					} else {
						visited[tx][ty] = true;
						pq.add(new int[] {tx, ty, time + 1});
					}
				}
			}
			
			
			boolean f = result == Integer.MAX_VALUE ? true : false;
			
			bw.write("#" + t + " " + (f ? -1 : result) + "\n");
		} // 전체 테스트 케이스 종료
		bw.flush();
		bw.close();
	}
}
