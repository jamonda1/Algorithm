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
		
		int[][] map = new int[102][102]; // 맵 전체를 0으로 감싼다
		
		int N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			for(int j = b+1; j <= b+10; j++) {
				for(int k = a+1; k <= a+10; k++) {
					map[j][k] += 1;
				}
			}
		}
		int count = check(map);
		
		bw.write(count + "");
		bw.flush();
		bw.close();
	}

	private static int check(int[][] map) {
		int[] row = {-1, 1, 0, 0};
		int[] col = {0, 0, -1, 1};
		
		int count = 0;
		
		for(int i = 0; i < 102; i++) {
			for(int j = 0; j < 102; j++) {
				if(map[i][j] == 0) continue;
				
				// 4방 탐색해서 1주변에 있는 0의 수만큼 카운트
				for(int k = 0; k < 4; k++) {
					if(map[i + row[k]][j + col[k]] == 0) count++;
				}
			}
		}
		return count;
	}
}
