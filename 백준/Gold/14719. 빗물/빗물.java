import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 블록이 쌓여있다. 비가 오면 블록 사이에 빗물이 고인다.
 * 첫 줄에 세로의 길이 H와 가로의 길이 W가 주어진다.
 * 두 번째 줄에는 블럭에 쌓인 높이를 의미하는 0 ~ H 이하의 정수가 차례로 W개 주어진다.
 * 
 * 2차원 세게에서 고이는 빗물의 총량을 출력하여라
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int H = Integer.parseInt(st.nextToken()); // 세로의 길이 H
		int W = Integer.parseInt(st.nextToken()); // 가로의 길이 W
		
		boolean[][] map = new boolean[H][W];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < W; i++) {
			int size = Integer.parseInt(st.nextToken());
			for(int j = H-1; j >= H-size; j--) {
				map[j][i] = true;
			}
		} // 맵 정보 입력 완료
		
		int result = 0; // 빗물이 저장되는 양
		for(int i = 0; i < H; i++) {
			int count = 0; // 둘 다 한 층 내려갈 때마다 초기화 필요
			boolean f = false;
			
			for(int j = 0; j < W; j++) {
				if(map[i][j]) f = true; // 처음에 벽이 나오면 카운트 시작
				if(f && !map[i][j]) count++; // 카운트 시작됐을 때 빈칸을 만나면 카운팅
				if(f && map[i][j]) { // 카운팅하다가 벽이 다시 나오면
					result += count; // 결과에 값을 추가해주고 다시 초기화
					count = 0;
				}
			}
		}
		System.out.println(result);
	}
}