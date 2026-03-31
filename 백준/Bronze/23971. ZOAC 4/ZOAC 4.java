import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int H = Integer.parseInt(st.nextToken()); // 맵의 세로 H
		int W = Integer.parseInt(st.nextToken()); // 맵의 가로 W
		int N = Integer.parseInt(st.nextToken()) + 1; // 세로로 비워야 하는 칸 N
		int M = Integer.parseInt(st.nextToken()) + 1; // 가로로 비워야 하는 칸 M
		
		int result = 0;
		for(int i = 0; i < H; i+=N) {
			for(int j = 0; j < W; j+=M) {
				result++;
			}
		}
		
		System.out.println(result);
	}
}