import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] nm = br.readLine().split(" ");
		int n = Integer.parseInt(nm[0]); // N * N 배열
		int m = Integer.parseInt(nm[1]); // 합을 M 번 구할 예정
		
		int[][] numbers = new int[n][n];
		int[][] sums = new int[n + 1][n + 1];
		
		for(int i = 0; i < n; i++) {
			String[] tokens = br.readLine().split(" "); // 배열 한 줄씩 입력
			for(int j = 0; j < n; j++) {
				int temp = Integer.parseInt(tokens[j]);
				numbers[i][j] = temp; // 배열 집어넣기
			}
		}
		
		for(int i = 1; i <= n; i++) { // 구간합 구하기
			for(int j = 1; j <= n; j++) {
				sums[i][j] = sums[i][j - 1] + sums[i - 1][j] - sums[i - 1][j - 1] + numbers[i - 1][j - 1];
			}
		}
		
		for(int i = 0; i < m; i++) {
			String[] tokens = br.readLine().split(" ");   // 범위 구하기
			int startX = Integer.parseInt(tokens[0]); // x1
			int startY = Integer.parseInt(tokens[1]); // y1
			int endX = Integer.parseInt(tokens[2]);   // x2
			int endY = Integer.parseInt(tokens[3]);   // y2
			
			int result = sums[endX][endY] - sums[endX][startY - 1] - sums[startX - 1][endY] + sums[startX - 1][startY - 1];
			
			bw.write(result + "\n");
		}
		bw.flush();
		bw.close();
	}
}
