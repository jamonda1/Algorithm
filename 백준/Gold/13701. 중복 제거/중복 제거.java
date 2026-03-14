import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
/*
 * N개의 정수를 읽고, 반복되는 수를 제외한 남은 수를 순서대로 출력하시오
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 2^25까지 주어지지만, int는 32비트 즉 2^5
		// 그래서 2^25 / 2^5인 2^20을 배정해주어야 한다.
		int[] bitSet = new int[1 << 20];
		
		int size = st.countTokens();
		for(int i = 0; i < size; i++) {
			int X = Integer.parseInt(st.nextToken());
			
			int idx = X >> 5; // X / 32와 같다.
			int rest = X & 31;// X % 31과 같다.
			
			if((bitSet[idx] & (1 << rest)) == 0) { // 만약 bitSet[idx] == 000인데 rest가 2(010)이면 & 연산 해서 0이 반환된다.
				bw.write(X + " ");
				bitSet[idx] |= (1 << rest); // 000 | 010을 |연산한 010이 반환된다.
			}
		}
		
		bw.flush();
	}
}