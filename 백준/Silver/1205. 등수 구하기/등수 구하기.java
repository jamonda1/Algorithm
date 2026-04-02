import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 점수의 개수 N
		int T = Integer.parseInt(st.nextToken()); // 태수의 점수 T
		int P = Integer.parseInt(st.nextToken()); // 랭킹에 올라갈 수 있는 점수의 개수 P
		
		if(N != 0) st = new StringTokenizer(br.readLine());
		
		int count = 1;
		int same = 0;
		
		while(st.hasMoreTokens()) {
			int pivot = Integer.parseInt(st.nextToken());
			
			if(pivot > T) count++;
			if(pivot == T) same++;
		}
		
		System.out.println(same + count <= P ? count : -1);
	}
}