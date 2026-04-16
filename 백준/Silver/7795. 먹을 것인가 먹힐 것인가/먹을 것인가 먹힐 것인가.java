import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * 두 생명체의 크기가 주어졌을 때. A의 크기가 B보다 큰 게 몇 개나 있는가?
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken()); // 테스트 케이스의 수 T
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			int[] groupA = new int[A];
			int[] groupB = new int[B];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < A; i++) {
				groupA[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < B; i++) {
				groupB[i] = Integer.parseInt(st.nextToken());
			} // 그룹 A, 그룹 B 입력 완료
			
			Arrays.sort(groupA);
			Arrays.sort(groupB);
			
			int result = 0;
			
			int Aidx = A - 1;
			int Bidx = B - 1;
			
			while(Aidx >= 0) {
				if(groupA[Aidx] > groupB[Bidx]) { // 만약 지금 값이 B보다 크다면?
					result += (Bidx + 1);
				} else if(Bidx > 0) {
					Bidx--; // 만약 지금 값이 B보다 작거나 같다면, 인덱스를 줄여서 다시 비교
					continue;
				}
				Aidx--;
			}
			
			bw.write(result + "\n");
		}
		bw.flush();
		bw.close();
	}
}