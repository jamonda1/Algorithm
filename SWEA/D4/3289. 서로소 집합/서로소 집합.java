import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 집합 원소의 수 N
			int M = Integer.parseInt(st.nextToken()); // 연산의 개수 M
			
			parent = new int[N + 1]; // 최상위 부모 자신으로 초기화
			for(int i = 1; i <= N; i++) parent[i] = i;
			
			bw.write("#" + t + " ");
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int order = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				if(order == 0) { // 합집합 수행
					union(a, b);
				} else { // 두 집합이 같은 곳에 있는가?
					int result = (find(a) == find(b)) ? 1 : 0;
					bw.write(result + "");
				}
			}
			
			bw.write("\n");
		} // 전체 테스트 케이스 종료
		bw.flush();
		bw.close();
	}

	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a > b) parent[a] = b;
		if(a < b) parent[b] = a;
	}

	private static int find(int a) {
		if(parent[a] == a) return a;
		return parent[a] = find(parent[a]);
	}
}