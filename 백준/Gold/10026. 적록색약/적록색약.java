import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
/*
 * 적록 색약은 빨간색과 초록색의 차이를 거의 느끼지 못한다.
 * N * N 배열의 R G B가 주어지면 일반인이 봤을 때의 구역 수와
 * 색약인이 봤을 때의 구역 수를 구해보자
 * 
 * 구역은 상하좌우로 같은 색이 인접한 경우에 같은 구역에 속한다.
 */
public class Main {
	
	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		char[][] paint1 = new char[N][N]; // 일반인이 보는 그림
		char[][] paint2 = new char[N][N]; // 색약인이 보는 그림
		
		for(int i = 0; i < N; i++) {
			String input = br.readLine();
			for(int j = 0; j < N; j++) {
				paint1[i][j] = input.charAt(j);
				paint2[i][j] = input.charAt(j);
				// 색약인의 경우 G에 전부 R로 입력
				if(paint2[i][j] == 'G') paint2[i][j] = 'R';
			}
		}
		countArea(paint1); // 일반인의 경우
		countArea(paint2); // 색약인의 경우
	}
	static void countArea(char[][] paint) {
		boolean[][] visited = new boolean[N][N];
		// 반복문을 돌면서 하나를 찾으면 bfs탐색 돌면서 같은 알파벳을 전부 true로 변환한다.
		int count = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(visited[i][j]) continue;
				// 방문하지 않은 곳이면 탐색 시작
				bfs(visited, paint, i, j);
				count++; // 탐색이 끝나면 구역 하나 끝
			}
		}
		System.out.print(count + " ");
	}
	static boolean[][] bfs(boolean[][] visited, char[][] paint, int x, int y) {
		int[] r = {-1, 1, 0, 0};
		int[] c = {0, 0, -1, 1};
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {x, y}); // 전달된 값을 초기값으로 삽입
		
		while(!queue.isEmpty()) {
			int[] current = queue.poll();
			
			for(int i = 0; i < 4; i++) {
				int X = current[0] + r[i];
				int Y = current[1] + c[i];
				// 범위를 벗어나면 다시
				if(X < 0 || N <= X || Y < 0 || N <= Y) continue;
				// 찾아야 하는 색상과 다른 색상이거나, 이미 방문한 곳이면 다시
				if(paint[X][Y] != paint[x][y] || visited[X][Y]) continue;
				
				visited[X][Y] = true; // 방문 처리 후 큐에 넣기
				queue.add(new int[] {X, Y});
			}
		}
		return visited; // 하나의 구역을 모두 방문한 후 리턴
	}
}