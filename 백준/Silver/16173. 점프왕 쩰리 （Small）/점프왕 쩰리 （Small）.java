import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int canJumpCnt = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		
		StringTokenizer st;
		for(int i = 0; i < N; i++) { // 발판 번호가 적힌 map 입력
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(N, map, 0, 0);
		// -1에 도달했던 분기가 있으면 점프 가능하다는 뜻
		String result = (canJumpCnt > 0 ? "HaruHaru" : "Hing");
		bw.write(result);
		bw.flush();
		bw.close();
	}
	
	static void dfs(int N, int[][] map, int x, int y) {
		int jumpNum = map[x][y]; // 지금 있는 곳의 값만큼만 점프 가능
		if(jumpNum == -1) { // 만약 지금 있는 곳이 -1이면 카운트
			canJumpCnt++;
			return;
		}
		
		if(jumpNum == 0) return;
		// 맵을 벗어나지 않는 경우에만 점프
		if(x + jumpNum < N) dfs(N, map, x + jumpNum, y); // 아래로 점프
		if(y + jumpNum < N) dfs(N, map, x, y + jumpNum); // 오른쪽으로 점프
	}
}