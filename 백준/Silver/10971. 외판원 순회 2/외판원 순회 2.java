import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 1번부터 N번까지 번호가 매겨진 도시들이 있다.
 * 도시들 사이에는 길이 있을 수도 있고 없을 수도 있다.
 * 한 외판원이 어느 한 도시에서 출발해 N개의 도시를 모두 거쳐 다시 원래 도시로 돌아오는 순회를 계획 중
 * 
 * 단 한 번 갔던 도시로는 갈 수 없다. 가장 적은 비용으로 여행을 하고 싶다.
 * 
 * 각 도시를 이동하는데 드는 비용은 행렬 W[i][j]로 주어진다. i에서 j로 가기위한 비용이다.
 * 비용은 대칭이 아니다. 경우에 따라서 i에서 j로 갈 수 없는 경우도 있으며 이때는 W[i][j] = 0이다.
 * 
 * N과 비용 행렬이 주어졌을 때, 가장 적은 비용으로 경로를 구하는 프로그램을 작성하자.
 */
public class Main {
	
	static int N, result = Integer.MAX_VALUE;
	static int[][] city;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); // 도시의 수 N
		city = new int [N][N];
		visited = new boolean[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				city[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 비용 정보 입력 완료
		
		combination(0, new int[N]);
		
		System.out.println(result);
	}
	
	static void combination(int depth, int[] arr) {
		if(depth == N) {
			int sum = 0; // 여기에 도시 이동 비용의 합 저장
			
			for(int i = 1; i < N; i++) {
				// 다음 도시로 못넘어가는 경우의 수는 필요없다.
				if(city[arr[i-1]][arr[i]] == 0) return;
				sum += city[arr[i-1]][arr[i]];
			}
			if(city[arr[N-1]][arr[0]] == 0) return; // 원점으로 돌아갈 수 없는 경우도 리턴
			sum +=  city[arr[N-1]][arr[0]]; // 마지막에 다시 출발지로 돌아오는 비용
			
			result = Math.min(result, sum);
			
			return;
		}
		
		for(int i = 0; i < N; i++) { // 조합 만들기
			if(!visited[i]) {
				visited[i] = true;
				arr[depth] = i;
				combination(depth + 1, arr);
				visited[i] = false;
			}
		}
	}
}