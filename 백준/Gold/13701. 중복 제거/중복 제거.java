import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.StringTokenizer;
/*
 * N개의 정수를 읽고, 반복되는 수를 제외한 남은 수를 순서대로 출력하시오
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		HashSet<Integer> hs = new HashSet<>();
		int size = st.countTokens();
		for(int i = 0; i < size; i++) {
			int temp = Integer.parseInt(st.nextToken());
			if(hs.add(temp)) {
				bw.write(temp + " ");
			}
		}
		
		bw.flush();
	}
}