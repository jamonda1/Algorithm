import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
/*
 * 회사와 집의 위치, 그리고 각 고객의 위치는 이차원 정수 좌표로 주어진다.
 * 두 위치 사이의 거리는 맨해튼 계산식을 사용한다.
 * 
 * 회사에서 출발하여 N명의 고객을 모두 방문하고, 집으로 돌아오는 경로 중 가장 짧은 것을 찾으려고 한다.
 * 회사 -> N명의 고객의 집 -> 자신의 집
 */
public class Solution {
	
	static int N, result;
	static int[] office, home;
	static int[][] customer;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			result = Integer.MAX_VALUE;
			office = new int[2]; // 회사
			home = new int[2];   // 자신의 집
			
			N = Integer.parseInt(br.readLine()); // 고객의 수 N
			customer = new int[N][2];
			visited = new boolean[N];
			
			st = new StringTokenizer(br.readLine());
			office[0] = Integer.parseInt(st.nextToken()); // 회사 위치 입력
			office[1] = Integer.parseInt(st.nextToken());

			home[0] = Integer.parseInt(st.nextToken()); // 자신의 집 위치 입력
			home[1] = Integer.parseInt(st.nextToken());
			
			for(int i = 0; i < N; i++) {
				customer[i][0] = Integer.parseInt(st.nextToken()); // 고객들 위치 입력
				customer[i][1] = Integer.parseInt(st.nextToken());
			}
			
			// ----- 전체 입력 종료 ----- //
			
			permutation(0, new int[N][2]); // 메서드 호출
			
			bw.write("#" + t + " " + result + "\n");
		} // 전체 테스트 케이스 종료
		bw.flush();
		bw.close();
	}

	private static void permutation(int depth, int[][] arr) {
		if(depth == N) { // 순열이 완성되면 각각 비교해보기
			int x1 = office[0];
			int y1 = office[1];
			
			int len = 0;
			for(int[] c : arr) {
				int x2 = c[0];
				int y2 = c[1];
				
				len += Math.abs(x1 - x2) + Math.abs(y1 - y2);
				x1 = x2; y1 = y2;
				
				if(len >= result) return; // 계산하다가 기존 값보다 커지면 return
			}
			
			len += Math.abs(x1 - home[0]) + Math.abs(y1 - home[1]);
			
			result = Math.min(result, len); // 모든 거리를 계산해서 최저값만 저장
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(!visited[i]) { // 고객의 집만 순열 구성하기
				visited[i] = true;
				arr[depth][0] = customer[i][0];
				arr[depth][1] = customer[i][1];
				permutation(depth + 1, arr);
				visited[i] = false;
			}
		}
	}
}