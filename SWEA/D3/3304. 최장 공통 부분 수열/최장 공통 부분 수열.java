import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static char[] A = new char[1001]; // 넉넉하게 초기화
	static char[] B = new char[1001];
	static int[][] dp = new int[1001][1001];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int TC = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= TC; tc++) {
			
			st = new StringTokenizer(br.readLine());
			String s1 = st.nextToken(); // 첫 번째 문자열
			String s2 = st.nextToken(); // 두 번째 문자열
			
			s1.getChars(0, s1.length(), A, 1); // 문자열의 0번부터 끝까지, A 배열의 [1]번부터 채운다.
			s2.getChars(0, s2.length(), B, 1);
			
			for(int i = 1; i <= s1.length(); i++) {
				for(int j = 1; j <= s2.length(); j++) {
					if(A[i] == B[j]) { // 두 문자가 같으면?
						dp[i][j] = dp[i-1][j-1] + 1; // 직전의 값에서 +1 해주기
						
						// 만약 다르면 이전의 위치에서 더 큰 값을 끌어온다.
					} else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
			
			sb.append("#").append(tc).append(" ").append(dp[s1.length()][s2.length()]).append("\n");
			
		} // 전체 테스트 케이스 종료
		System.out.println(sb);
	}
}