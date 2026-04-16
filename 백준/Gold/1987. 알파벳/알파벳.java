import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 세로 R칸 가로 C칸의 보드가 있고, 말은 좌상단(0, 0)에 있다.
 * 각 칸에는 알파벳이 있으며, 모두 대문자.
 * 말은 4방으로 이동 가능하다. 그런데 자신이 지금까지 지나온 알파벳과 상이한 곳으로만 갈 수 있다.
 * 최대 몇 칸 이동 가능한가? (최초의 위치도 1칸으로 친다)
 * 
 * 지금까지 지나온 알파벳의 정보를 저장하자!! 그리고 탐색할 때 contains!!
 */
public class Main {
	
	static int R, C, result = 0;
	static int[] r = {-1, 1, 0, 0};
	static int[] c = {0, 0, -1, 1};
	static char[][] board;
	static boolean[] alphabet = new boolean[26]; // 알파벳을 밟았다면 true
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken()); // 세로의 크기 R
		C = Integer.parseInt(st.nextToken()); // 가로의 크기 C
		
		board = new char[R][C];
		visited = new boolean[R][C];
		
		for(int i = 0; i < R; i++) {
			String input = br.readLine();
			for(int j = 0; j < C; j++) {
				board[i][j] = input.charAt(j);
			}
		} // 보드 정보 입력 완료
		
		dfs(0, 0, 1);
		
		System.out.println(result);
	}

	private static void dfs(int x, int y, int count) {
		result = Math.max(result, count);
		
		alphabet[board[x][y] - 'A'] = true; // 알파벳 사용 완료
		visited[x][y] = true; // 방문 완료
		
		for(int i = 0; i < 4; i++) {
			int tempX = x + r[i];
			int tempY = y + c[i];
			
			// 범위를 벗어나면 다시
			if(tempX < 0 || R <= tempX || tempY < 0 || C <= tempY) continue;
			// 방문했거나, 이미 사용된 알파벳이면 다시
			if(visited[tempX][tempY] || alphabet[board[tempX][tempY] - 'A']) continue;
			
			dfs(tempX, tempY, count + 1);
		}
		// 쭉~ 깊이 들어가서 result와 count 비교, 그 다음 리턴하면서 사용했던 거 해제
		// 해제한 후에 바로 직전으로 리턴해서 다음 반복문 수행
		alphabet[board[x][y] - 'A'] = false; // 알파벳 사용 해제
		visited[x][y] = false; // 방문 해제
	}
}