import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, result = Integer.MAX_VALUE;
	static int[][] member;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); // 참석한 직원의 수 N
		visited = new boolean[N];
		
		member = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				member[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 능력치 입력 완료
		
		pickMember(0, 0, new int[N/2]);
		
		System.out.println(result);
	}
	
	static void pickMember(int depth, int start, int[] arr1) {
		if(depth == N / 2) {
			int startTeam = 0, linkTeam = 0;
			
			for(int i = 0; i < N - 1; i++) {
				for(int j = i + 1; j < N; j++) {
					if(i == j) continue;
					if(visited[i] && visited[j]) {
						// 방문한 곳들끼리 조합해서 스타트 팀으로
						startTeam += member[i][j] + member[j][i];
					}
					if(!visited[i] && !visited[j]) {
						// 방문하지 않은 곳들끼리 조합해서 링크 팀으로
						linkTeam += member[i][j] + member[j][i];
					}
				}
			}
			if(startTeam == linkTeam) { // 둘이 점수가 같으면 0
				System.out.println(0);
				System.exit(0);
			}
			// 점수가 다르면 기존의 값과 새로운 값 중에 더 작은 것으로
			result = Math.min(result, Math.abs(startTeam - linkTeam));
			
			return;
		}
		
		for(int i = start; i < N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				arr1[depth] = i;
				pickMember(depth + 1, i + 1, arr1);
				visited[i] = false;
			}
		}
	}
}