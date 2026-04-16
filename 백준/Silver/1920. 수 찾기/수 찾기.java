import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] firstNums = new int[N]; // 입력받은 숫자들 저장
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			firstNums[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(firstNums);
		
		int M = Integer.parseInt(br.readLine());
		int[] secondNums = new int[M]; // 여기에 들어온 숫자를 firstNums와 비교해서 있는지 확인
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			secondNums[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < M; i++) {
			if(check(secondNums[i], firstNums)) {
				bw.write(1 + "\n");
			} else {
				bw.write(0 + "\n");
			}
		}
		bw.flush();
		bw.close();
	}

	private static boolean check(int target, int[] firstNums) throws IOException {
		
		boolean f = false;
		int left = 0;
		int right = firstNums.length;
		while (left < right) {
			int middle = left + (right -left) / 2;
			
			if(target < firstNums[middle]) {
				right = middle;
			} else {
				left = middle + 1;
			}
			if(target == firstNums[middle]) f = true;
		}
		return f;
		
	}
}