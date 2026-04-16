import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	static int N, max;
	static char[][] map;
	static boolean[][] visited;
	static int[] r = {-1, 1, 0, 0};
	static int[] c = {0, 0, -1, 1};
	static List<Integer> list = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine()); // 지도의 크기 N
		map = new char[N][N];
		visited = new boolean[N][N]; // 방문체크
		
		for(int i = 0; i < N; i++) {
			String input = br.readLine();
			for(int j = 0; j < N; j++) {
				map[i][j] = input.charAt(j);
			}
		} // 맵 정보 입력 완료
		
		int count = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!visited[i][j] && map[i][j] == '1') {
					// 방문 안 했고, 해당 위치가 1이라면?
					max = 0;
					dfs(i, j);
					list.add(max);
					count++;
				}
			}
		}
		Collections.sort(list); // 오름차순 정렬
		bw.write(count + "\n");
		for(int i = 0; i < list.size(); i++) {
			bw.write(list.get(i) + "\n");
		}
		
		bw.flush();
		bw.close();
	}

	private static void dfs(int x, int y) {
		visited[x][y] = true; // 방문 체크
		max++; // 해당 위치 방문했으니 탐색 횟수++
		
		for(int i = 0; i < 4; i++) {
			int tempX = x + r[i];
			int tempY = y + c[i];
			
			if(tempX < 0 || N <= tempX || tempY < 0 || N <= tempY) continue;
			if(visited[tempX][tempY] || map[tempX][tempY] == '0') continue;
			
			dfs(tempX, tempY); // 조건에 맞으면 다음으로
		}
	}
}