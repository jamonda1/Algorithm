import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
 * 총 N개의 문제가 있고, 각 문제마다 보상과 데드라인이 있다. 데드라인은 N 이하의 자연수인 것이 보장된다.
 * 문제를 적절히 풀어서 최대한 컵라면을 많이 받도록 해보자
 */
public class Main {
	
	static class Problem implements Comparable<Problem> {
		int d, c;
		public Problem(int d, int c) {
			this.d = d;
			this.c = c;
		}
		@Override
		public int compareTo(Problem o) {
			return Integer.compare(o.d, this.d);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine()); // 숙제의 개수 N
		
		PriorityQueue<Problem> pq = new PriorityQueue<>();
		PriorityQueue<Integer> cup = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer a, Integer b) {
				return Integer.compare(b, a);
			}
		});
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken()); // 데드라인
			int c = Integer.parseInt(st.nextToken()); // 컵라면 수
			pq.add(new Problem(d, c));
		} // PQ에 모두 집어넣기
		
		int result = 0;
		int deadLine = N; // 데드라인 역순으로 탐색
		while(deadLine > 0) {
			while(!pq.isEmpty() && pq.peek().d >= deadLine) {
				cup.add(pq.poll().c);
			} // 데드라인 이상이 되는 것만 cup에 넣기
			
			// cup은 내림차순 정렬이라 가장 큰 값을 뽑을 수 있다.
			if(!cup.isEmpty()) result += cup.poll();
			deadLine--;
		}
		
		System.out.println(result);
	}
}