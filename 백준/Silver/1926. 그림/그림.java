import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int n, m, result = 0, max = 0;
	static char[][] paper;
	static boolean[][] visited;
	static Queue<int[]> queue = new LinkedList<>();
	static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken()); // 도화지 세로 크기 n
		m = Integer.parseInt(st.nextToken()); // 도화지 가로 크기 m
		
		paper = new char[n][m];
		visited = new boolean[n][m];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				paper[i][j] = st.nextToken().charAt(0);
			}
		} // 도화지 정보 입력 완료
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(!visited[i][j] && paper[i][j] == '1') {
					bfs(i, j);
					result++; // 그림 하나 찾았다.
				}
			}
		}
		
		System.out.println(result + "\n" + max);
	}
	
	static void bfs(int x, int y) {
		queue.add(new int[] {x, y});
		visited[x][y] = true;
		
		int count = 0;
		while(!queue.isEmpty()) {
			int[] curr = queue.poll();
			count++; // 한 번 반복 = 한 칸 탐색
			
			for(int i = 0; i < 4; i++) {
				int tx = curr[0] + dr[i];
				int ty = curr[1] + dc[i];
				
				if(tx < 0 || n <= tx || ty < 0 || m <= ty) continue;
				if(visited[tx][ty] || paper[tx][ty] == '0') continue;
				
				visited[tx][ty] = true;
				queue.add(new int[] {tx, ty});
			}
		}
		// 기존 값과 비교해서 더 큰 값 저장
		max = Math.max(max, count);
	}
}