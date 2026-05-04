import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static char[] A = new char[1001];
	static char[] B = new char[1001];
	static int[][] dp = new int[1001][1001];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int TC = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= TC; tc++) {
			
			st = new StringTokenizer(br.readLine());
			String input1 = st.nextToken();
			String input2 = st.nextToken();
			
			int s1 = input1.length();
			int s2 = input2.length();
			
			input1.getChars(0, s1, A, 1); // 단일 객체 유지한 상태로 배열에 넣기
			input2.getChars(0, s2, B, 1);
			
			for(int i = 1; i <= s1; i++) {
				for(int j = 1; j <= s2; j++) {
					if(A[i] == B[j]) {
						dp[i][j] = dp[i-1][j-1] + 1;
					} else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
			
			sb.append("#").append(tc).append(" ").append(dp[s1][s2]).append("\n");
			
		} // 전체 테스트 케이스 종료
		System.out.println(sb);
	}
}