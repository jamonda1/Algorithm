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
		int n = Integer.parseInt(nm[0]); // 수의 개수
		int m = Integer.parseInt(nm[1]); // 합을 구해야 하는 개수
		
		String[] tokens = br.readLine().split(" ");
		int[] nums = new int[n]; // N개의 수 저장
		int[] sums = new int[n]; // 구간별 합을 미리 저장
		
		int sum = 0;
		for(int i = 0; i < n; i++) {
			int temp = Integer.parseInt(tokens[i]);
			nums[i] = temp; // 배열에 숫자 저장
			
			sum += temp;
			sums[i] = sum;  // 구간별로 합산해서 미리 저장
		}
		
		int result = 0;
		for(int i = 0; i < m; i++) {
			String[] ab = br.readLine().split(" ");
			int a = Integer.parseInt(ab[0]) - 1; // a부터
			int b = Integer.parseInt(ab[1]) - 1; // b까지의 합을 구해야 한다.

			// a가 0이면 0부터의 합이니깐 바로 b번째 출력, 그게 아니면 b번째에서 a-1 번째를 뺀다.
			result = (a == 0 ? sums[b] : (sums[b] - sums[a - 1]));
			
			bw.write(result + "\n");
		}
		bw.flush();
		bw.close();
	}
}
