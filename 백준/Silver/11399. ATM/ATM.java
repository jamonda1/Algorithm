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
		
		int N = Integer.parseInt(br.readLine()); // 사람의 수 입력
		int[] times = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) { // 시간의 수 입력
			times[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(times); // 오름차순으로 정렬
		
		int sum = 0;
		int result = 0;
		for(int temp : times) {
			sum = sum + temp; // sum에는 각 사람의 소요 시간을 구하고
			result += sum;	  // result에는 해당 소요 시간의 전체 합을 저장한다
		}
		bw.write(result + "\n");
		bw.flush();
		bw.close();
	}
}
