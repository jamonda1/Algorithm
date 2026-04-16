import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[][] map;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); // 맵의 크기
		map = new int[N][N];
		visited = new boolean[N][N];
		
		for(int i = 0; i < N; i++) { // 맵 입력
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // ---------- 변수 선언 및 입력 끝 ----------
		
		if(map[0][0] != 0 || map[0][0] > N) { // 초기 위치가 0이거나 맵을 벗어나는 수치면 안 된다.
			jump(0, 0);
		}
		System.out.println("Hing"); // 여기까지 왔다는 것은 실패라는 뜻으로 Hing을 출력
	}
	
	static void jump(int x, int y) {
		int current = map[x][y];
		if(current == -1) { // -1에 도착하면 HaruHaru를 출력하고 바로 종료하자
			System.out.println("HaruHaru");
			System.exit(0);
		}
		// 지금 위치에서 이동할 위치가 0이 아니거나 범위 안인가?
		// 다음 발판이 0이 아니고, 그 발판이 범위 안에 있을 때만 이동
		if(x + current < N) {
			if(map[x + current][y] != 0 && !visited[x + current][y]) {
				visited[x + current][y] = true;
				jump(x + current, y);			
			}
		}
		if(y + current < N) {
			if(map[x][y + current] != 0 && !visited[x][y + current]) {
				visited[x][y + current] = true;
				jump(x, y + current);
			}
		}
	}
}