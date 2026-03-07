import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * N개의 도시가 있고, 두 도시 사이에는 길이 있을 수도 없을 수도 있다.
 * 여행 일정이 주어졌을 때, 이 여행 경로가 가능한 것인지 알아보자
 * 
 * 도시의 수 N이 주어지고, 다음 줄에 여행 계획에 속한 도시들의 수 M이 주어진다.
 * 다음 N개의 줄에는 N개의 정보가 주어진다.
 * 0이면 연결이 안 되어 있고, 1이면 연결이 되어있다는 뜻이다.
 */
public class Main {
	
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine()); // 도시의 수 N
		int M = Integer.parseInt(br.readLine()); // 여행 계획에 속한 도시의 수 M
		
		parent = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			parent[i] = i;
		} // 자기 자신을 최상위로 초기화
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				int a = Integer.parseInt(st.nextToken());
				if(a == 1) union(i, j); // 1이면 연결하자
			}
		}
		
		boolean f = true;
		st = new StringTokenizer(br.readLine());
		
		int pivot = find(Integer.parseInt(st.nextToken())); // 여기 저장된 값과 나머지 도시들의 값이 같아야 한다.
		for(int i = 0; i < M - 1; i++) {
			int temp = find(Integer.parseInt(st.nextToken()));
			if(pivot != temp) f = false;
		}
		
		String result = (f) ? "YES" : "NO";
		System.out.println(result);
	}

	private static void union(int i, int j) {
		i = find(i); // 최상위 부모 찾기
		j = find(j);
		
		// 서로 최상위 부모가 다르면 한쪽에 등록하자
		if(i != j) parent[j] = i;
	}

	private static int find(int i) {
		if(parent[i] == i) return i;
		
		// 재귀를 통해 최상위 부모 탐색
		return parent[i] = find(parent[i]);
	}
}