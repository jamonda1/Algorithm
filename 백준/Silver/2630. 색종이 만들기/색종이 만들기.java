import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] paper; // 종이의 크기
	static int white = 0, blue = 0; // 흰 색종이, 파란 색종이
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		paper = new int[N][N];
		
		StringTokenizer st;
		for(int i = 0; i < N; i++) { // 종이에 값 입력
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		cuttingPaper(0, 0, N, N); // 메서드 호출
		
		bw.write(white + "\n" + blue);
		
		bw.flush();
		bw.close();
	}
	
	static void cuttingPaper(int start_x, int start_y, int end_x, int end_y) {
		int target = paper[start_x][start_y];
		boolean f = true;
		
		gumi : for(int i = start_x; i < end_x; i++) {
			for(int j = start_y; j < end_y; j++) {
				if(paper[i][j] != target) { // 처음에 타겟으로 잡은 값과 범위 내에서 다른 값이 오면 컷팅
					f = false;
					int X = (start_x + end_x) / 2;
					int Y = (start_y + end_y) / 2;
					cuttingPaper(start_x, start_y, X, Y); // 1 좌상단
					cuttingPaper(start_x, Y, X, end_y);   // 2 우상단
					cuttingPaper(X, start_y, end_x, Y);	  // 3 좌하단
					cuttingPaper(X, Y, end_x, end_y);	  // 4 우하단
					break gumi; // 만약 종이를 잘라야 하는 상황이 오면 바로 반복문 종료
				}
			}
		}
		if(f) { // 색종이를 자를 필요가 없을 때는 해당 면의 숫자가 모두 같은 상태이므로 카운팅
			if(target == 0) white++;
			if(target == 1) blue++;
		}
	}
}
