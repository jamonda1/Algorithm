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
		StringTokenizer st;
		
		for(;;) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			if(a == 0 && b == 0 && c == 0) break;
			
			if(a == b && b == c) { // 세 변의 길이가 모두 같은 경우
				bw.write("Equilateral\n");
				continue;
			}
			
			int[] arr = {a, b, c};
			Arrays.sort(arr); // [0]이 가장 짧고, [2]가 가장 길다
			
			// 두 변의 길이만 같은 경우
			if((arr[2] == arr[1] && arr[2] + arr[1] > arr[0]) || (arr[0] == arr[1] && arr[0] + arr[1] > arr[2])) {
				bw.write("Isosceles\n");
				continue;
			}
			
			if(arr[0] + arr[1] > arr[2]) {
				bw.write("Scalene\n");
				continue;
			}
			
			bw.write("Invalid\n");
		}
		bw.close();
	}
}