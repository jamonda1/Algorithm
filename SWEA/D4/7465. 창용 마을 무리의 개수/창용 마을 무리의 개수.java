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
			int N = Integer.parseInt(st.nextToken()); // 창용 마을의 사람 수 N
			int M = Integer.parseInt(st.nextToken()); // 관계 비교 횟수 M
			
			parent = new int[N + 1]; // 최상위를 자신으로 초기화
			for(int i = 1; i <= N; i++) parent[i] = i;
			
			int result = N;
			
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				
				
				if(union(A, B)) {
					result--;
				} // 합칠 때마다 무리는 1개씩 줄어든다
			}
			bw.write("#" + t + " " + result + "\n");
			
		} // 전체 테스트 케이스 종료
		bw.flush();
		bw.close();
	}

	private static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a != b) {
			parent[b] = a;
			return true;
		}
		return false;
	}

	private static int find(int a) {
		if(parent[a] == a) return a;
		return parent[a] = find(parent[a]);
	}
}