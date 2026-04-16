import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		// 우선 순위 큐. 기본은 오름차순. 하지만 revers를 쓰면 내림차순이 된다.
		PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());
		int N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N; i++) {
			// x의 최댓값은 2^31이므로 int의 최댓값보다 1 크다.
			long x = Integer.parseInt(br.readLine());
			
			if(x > 0) {
				pq.add(x);
			} else {
				// 값이 null이면 0을 출력해야 한다.
				long temp = (pq.peek() == null ? 0 : pq.poll());
				bw.write(temp + "\n");
			}
		}
		bw.flush();
		bw.close();
	}
}
