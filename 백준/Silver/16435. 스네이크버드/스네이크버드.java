import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 과일의 개수
		int L = Integer.parseInt(st.nextToken()); // 스네이크버드의 초기 길이
		
		int[] fruite = new int[N];
		st = new StringTokenizer(br.readLine());  // 과일 입력과 저장
		for(int i = 0; i < N; i++) {
			fruite[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(fruite); // 오름차순으로 정렬
		
		for(int i = 0; i < N; i++) {
			if(fruite[i] <= L) { // 과일의 길이가 작거나 같으면 ++
				L++;
			} else {
				break;				
			}
		}
		bw.write(L + "\n");
		bw.flush();
		bw.close();
	}
}
