import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, max = 0;
	static int[][] area;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		area = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				area[i][j] = Integer.parseInt(st.nextToken());
				if(area[i][j] > max) max = area[i][j];
			}
		} // 지역의 높이 입력 완료
		
		int result = 0; // 최댓값 저장될 예정
		
		for(int i = 0; i <= max; i++) {
			visited = new boolean[N][N];
			int count = 0;
			for(int j = 0; j < N; j++) {
				for(int k = 0; k < N; k++) {
					if(!visited[j][k] && area[j][k] > i) {
						// 방문하지 않았고, i보다 높다면?
						bfs(j, k, i);
						count++;
					}
				}
			}
			if(result < count) result = count;
		}
		System.out.println(result);
	}
	
	static void bfs(int x, int y, int day) {
		int[] r = {-1, 1, 0, 0};
		int[] c = {0, 0, -1, 1};
		
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {x, y});
		visited[x][y] = true;

		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			for(int i = 0; i < 4; i++) {
				int tempX = cur[0] + r[i];
				int tempY = cur[1] + c[i];
				
				if(tempX < 0 || N <= tempX || tempY < 0 || N <= tempY) continue;
				if(visited[tempX][tempY] || area[tempX][tempY] <= day) continue;
				
				visited[tempX][tempY] = true;
				queue.add(new int[] {tempX, tempY});
			}
		}
	}
}