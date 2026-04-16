import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int[][] video;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); // 영상의 크기 입력
		video = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			String input = br.readLine();
			for(int j = 0; j < N; j++) {
				video[i][j] = input.charAt(j) - 48;
			}
		}
	
		zip(0, 0, N, N);
		
		bw.flush();
		bw.close();
	}
	
	static void zip(int start_x, int start_y, int end_x, int end_y) throws IOException {
		
		int target = video[start_x][start_y];
		boolean f = true;
		
		gumi : for(int i = start_x; i < end_x; i++) {
			for(int j = start_y; j < end_y; j++) {
				if(target != video[i][j]) {
					f = false;
					bw.write("("); // 재귀로 안 쪽으로 들어갈 때 괄호를 열어주고
					
					int divX = (start_x + end_x) / 2;
					int divY = (start_y + end_y) / 2;
					zip(start_x, start_y, divX, divY); // 좌상단
					zip(start_x, divY, divX, end_y);   // 우상단
					zip(divX, start_y, end_x, divY);   // 좌하단
					zip(divX, divY, end_x, end_y);     // 우하단
					
					bw.write(")"); // 값들이 출력된 후 돌아왔을 때 괄호를 닫아준다.
					
					break gumi;
				}
			}
		}
		if(f) bw.write(target + "");
		
	}
}