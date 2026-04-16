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
		
		// 후보 범위의 최소값인 L과 최댓값 H를 넉넉하게 잡아서 이를 점점 줄여나간다.
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken()); // 가지고 있는 전선의 수
		int N = Integer.parseInt(st.nextToken()); // 필요한 전선의 수
		
		int[] lan = new int[K];
		int max = 0;
		
		for(int i = 0; i < K; i++) {
			lan[i] = Integer.parseInt(br.readLine());
			max = Math.max(max, lan[i]);
		}
		
		long left = 1;
		long right = max;
		while(left < right) {
			long middle = (left + right + 1) / 2;
			int count = 0;
			
			for(int i = 0; i < K; i++) {
				count += (lan[i] / middle);
			}
			
			if(count >= N) {
				// 내가 자른 전선이 더 많거나 같으면
				// left를 줄여서 전선 단위를 키워보자
				left = middle;
			} else {
				// 필요한 전선 수보다 자른 전선이 더 적으면
				// right를 줄여서 자를 전선의 단위를 줄여보자
				right = middle - 1;
			}
		}
		
		bw.write(left + "");
		bw.flush();
		bw.close();
	}
}
