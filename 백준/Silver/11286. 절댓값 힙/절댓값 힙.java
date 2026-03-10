import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
/*
 * 1. 배열에 정수 x를 넣는다.
 * 2. 절댓값이 가장 작은 값을 출력하고, 그 값을 배열에서 제거한다.
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer a, Integer b) {
				if(Math.abs(a) == Math.abs(b)) {
					return a - b;
				}
				return Math.abs(a) - Math.abs(b);
			} // 절대값 기준 오름차순. 0은 입력되지 않는다.
		});
		
		
		int N = Integer.parseInt(br.readLine()); // 연산의 개수 N
		for(int i = 0; i < N; i++) {
			int x = Integer.parseInt(br.readLine());
			
			// 만약 pq에 값이 없다면 null이 아닌 0을 출력해야 한다.
			if(x == 0) bw.write((pq.peek() == null ? 0 : pq.poll()) + "\n");
			if(x != 0) pq.add(x);
		}
		
		bw.flush();
		bw.close();
	}
}