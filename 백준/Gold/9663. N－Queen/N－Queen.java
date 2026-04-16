import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	static int count = 0;
	
    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
       
       int N = Integer.parseInt(br.readLine()); // N 값 입력
       
       // queen[1] = 2라면 (1,2)에 퀸이 있다고 생각하면 된다.
       int[] queen = new int[N]; 
       
       setQueen(queen, N, 0);
       
       bw.write(count + "");
       bw.flush();
       bw.close();
    }

	private static void setQueen(int[] queen, int n, int depth) {
		if(depth == n) { // 퀸을 모두 배치했으면 카운트
			count++;
			return;
		}
		
		for(int i = 1; i <= n; i++) {
			queen[depth] = i; // 일단 퀸을 배치하고
			
			if(depth == 0 || isSafe(queen, n, depth)) {
				setQueen(queen, n, depth + 1); // 깊이가 0이거나 배치한 곳이 안전하면 재귀
			}
		}
	}

	private static boolean isSafe(int[] queen, int n, int depth) {
		for(int i = 0; i < depth; i++) {
			// 값이 같으면 세로로 퀸 간섭이 발생하니 false
			if(queen[i] == queen[depth]) return false;
			// 기존 좌표와 배치하려는 곳의 좌표 차의 절대값과, 두 요소 차의 절대값이 같으면 사선 간섭
			if(Math.abs(i - depth) == Math.abs(queen[i] - queen[depth])) return false;
		}
		// 통과되면 안전
		return true;
	}
}