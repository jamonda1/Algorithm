import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int[][] map = new int[100][100]; // 맵 전체를 0으로 감싼다
		int count = 0;
		
		int N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			
			for(int j = b; j < b+10; j++) { // 색종이 배치
				for(int k = a; k < a+10; k++) {
					if(map[j][k] == 0) count++;
					map[j][k] += 1;
					
				}
			}
		}
		
		bw.write(count + "");
		bw.flush();
		bw.close();
	}
}
