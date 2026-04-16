import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 3차원 배열에서 탈출을 해보자.
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		Queue<int[]> queue = new LinkedList<>();
		int[] dl = {-1, 1, 0, 0, 0, 0}, dr = {0, 0, -1, 1, 0, 0}, dc = {0, 0, 0, 0, 1, -1};
		
		for(;;) {
			st = new StringTokenizer(br.readLine());
			int L = Integer.parseInt(st.nextToken()); // 빌딩의 층 수 L
			int R = Integer.parseInt(st.nextToken()); // 한 층의 행 R
			int C = Integer.parseInt(st.nextToken()); // 한 층의 열 C
			
			if(L == 0 && R == 0 && C == 0) break; // 입력 종료 조건
			
			int[] start = new int[4];
			char[][][] map = new char[L][R][C];
			boolean[][][] visited = new boolean[L][R][C];
			
			for(int i = 0; i < L; i++) {
				for(int j = 0; j < R; j++) {
					String input = br.readLine();
					for(int k = 0; k < C; k++) {
						map[i][j][k] = input.charAt(k);
						if(map[i][j][k] == 'S') {
							start[0] = i; start[1] = j; start[2] = k;
						}
					}
				}
				String temp = br.readLine();
			} // 층별 정보 입력 완료
			
			queue.clear(); // 다음 케이스에서 큐에 찌꺼기가 있을 수 있다.
			queue.add(start); // [3]에는 시간이 들어간다.
			visited[start[0]][start[1]][start[2]] = true;
			
			boolean f = false;
			int time = 0;
			while(!queue.isEmpty()) {
				int[] curr = queue.poll();
				
				if(map[curr[0]][curr[1]][curr[2]] == 'E') {
					f = true; time = curr[3];
					break;
				} // 탈출구를 찾았으면 탈출하자
				
				for(int i = 0; i < 6; i++) {
					int tl = curr[0] + dl[i];
					int tr = curr[1] + dr[i];
					int tc = curr[2] + dc[i];
					
					if(tl < 0 || L <= tl || tr < 0 || R <= tr || tc < 0 || C <= tc) continue;
					if(visited[tl][tr][tc] || map[tl][tr][tc] == '#') continue;
					
					visited[tl][tr][tc] = true;
					queue.add(new int[] {tl, tr, tc, curr[3] + 1});
				}
			}
			
			if(f) {
				bw.write("Escaped in " + time + " minute(s).\n");
			} else {
				bw.write("Trapped!\n");
			}
		} // 전체 테스트 케이스 종료
		bw.flush();
		bw.close();
	}
}