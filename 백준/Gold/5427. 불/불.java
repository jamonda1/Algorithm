import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, sx, sy;
	static char[][] map;
	static List<int[]> fire;
	static boolean[][] visited;
	static Queue<int[]> queue1 = new LinkedList<>(); // 불이 담기는 큐
	static Queue<int[]> queue2 = new LinkedList<>(); // 상근이 위치 큐
	static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			queue1.clear();
			queue2.clear();
			
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken()); // 지도의 너비 M
			N = Integer.parseInt(st.nextToken()); // 지도의 높이 N
			
			map = new char[N][M];
			visited = new boolean[N][M];
			fire = new ArrayList<>(); // 불 위치 저장
			
			for(int i = 0; i < N; i++) {
				String input = br.readLine();
				for(int j = 0; j < M; j++) {
					map[i][j] = input.charAt(j);
					if(map[i][j] == '*') {
						queue1.add(new int[] {i, j});
						visited[i][j] = true;
					}
					if(map[i][j] == '@') {
						queue2.add(new int[] {i, j, 0});
						visited[i][j] = true;
					}
				}
			} // 맵 입력 완료
			
			int result = bfs();
			
			bw.write((result == -1) ? "IMPOSSIBLE\n" : result + "\n");
		} // 전체 테스트 케이스 종료
		bw.close();
	}

	private static int bfs() {
		while(!queue2.isEmpty()) {
			// 불 퍼트리기
			int fsize = queue1.size();
			for(int i = 0; i < fsize; i++) {
				int[] curr = queue1.poll();
				int x = curr[0];
				int y = curr[1];
				
				for(int j = 0; j < 4; j++) {
					int tx = x + dr[j];
					int ty = y + dc[j];
					
					if(tx < 0 || N <= tx || ty < 0 || M <= ty) continue;
					if(map[tx][ty] == '*' || map[tx][ty] == '#') continue;
					
					map[tx][ty] = '*';
					queue1.add(new int[] {tx, ty});
				}
			}
			// 상근이 이동하기
			int ssize = queue2.size();
			for(int i = 0; i < ssize; i++) {
				int[] curr = queue2.poll();
				int x = curr[0];
				int y = curr[1];
				
				for(int j = 0; j < 4; j++) {
					int tx = x + dr[j];
					int ty = y + dc[j];
					
					if(tx < 0 || N <= tx || ty < 0 || M <= ty) return curr[2] + 1;
					if(visited[tx][ty] || map[tx][ty] == '*' || map[tx][ty] == '#') continue;
					
					visited[tx][ty] = true;
					queue2.add(new int[] {tx, ty, curr[2] + 1});
				}
			}
		}
		
		return -1;
	}
}