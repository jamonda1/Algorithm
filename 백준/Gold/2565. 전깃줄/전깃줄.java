import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * 전봇대 A와 B 사이에 전선이 있다. 그리고 해당 전선들이 교차하는 경우가 있다.
 * 이럴 때 최소한의 전선을 제거해서 남은 모든 전신이 교차하지 않도록 하자.
 */
public class Main {
	
	static class Obj implements Comparable<Obj> {
		int a, b;
		public Obj(int a, int b) {
			this.a = a;
			this.b = b;
		}
		@Override
		public int compareTo(Obj o) {
			return Integer.compare(this.a, o.a);
		} // a 전봇대 기준으로 오름차순 정렬
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine()); // 전선의 개수 N

		Obj[] wire = new Obj[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			wire[i] = new Obj(a, b);
		} // 전선 정보 입력 완료
		
		Arrays.sort(wire); // a 전봇대를 기준으로 오름차순 정렬
		
		int[] dp = new int[N];
		int max = 0; // 최대로 남길 수 있는 전선의 수 저장
		
		for(int i = 0; i < N; i++) {
			dp[i] = 1; // 자기 자신 한 개는 기본적으로 무조건 남길 수 있다.
			
			for(int j = 0; j < i; j++) { // i 앞에 있는 전선들을 탐색
				// i의 b에 저장된 번호가 j에 저장된 b보다 크면 전선이 안 꼬였다는 뜻
				if(wire[i].b > wire[j].b) dp[i] = Math.max(dp[i], dp[j] + 1);
			}
			
			max = Math.max(max, dp[i]); // dp[i]와 비교해서 큰 값으로 갱신
		}
		
		System.out.println(N - max); // 전체 주어진 전선의 수에서 최대로 남긴 전선 수를 뺀다.
	}
}
