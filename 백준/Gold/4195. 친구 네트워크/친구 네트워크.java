import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
/*
 * 민혁이는 SNS에서 친구를 모으는 게 취미다.
 * 어떤 사이트의 친구 관계가 생긴 순서대로 주어졌을 때, 두 사람의 친구 네트워크에 몇 명이 있는지 구해라
 * 친구 네트워크란, 친구 관계만으로 타고타고 이동할 수 있는 사이를 말한다.
 */
public class Main {
	
	static HashMap<String, Integer> sns;
	static List<String[]> list;
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			sns = new HashMap<>(); // 케이스 시작 전 초기화
			list = new ArrayList<>();
			
			int F = Integer.parseInt(br.readLine()); // 친구 관계의 수
			
			int idx = 0; // 최종 idx는 입력된 사람 수가 남게 된다.
			for(int i = 0; i < F; i++) {
				st = new StringTokenizer(br.readLine());
				String A = st.nextToken();
				String B = st.nextToken();
				
				list.add(new String[] {A, B});
				
				if(!sns.containsKey(A)) sns.put(A, idx++);
				if(!sns.containsKey(B)) sns.put(B, idx++);
			} // 리스트 등록 및 최상위 초기화 완료
			
			parent = new int[idx]; // 몇 명인지 알았으니 이제 초기화
			for(int i = 0; i < idx; i++) parent[i] = i;
			
			int[] dp = new int[idx];
			Arrays.fill(dp, 1);
			
			// 출력을 위한 union-find 시작
			for(int i = 0; i < F; i++) {
				// 키값을 통해 저장된 번호 가져오기
				int a = sns.get(list.get(i)[0]);
				int b = sns.get(list.get(i)[1]);
				
				int aParent = find(a);
				int bParent = find(b);
				
				if(aParent == bParent) { // 두 부모가 같다면 한쪽만 출력
					bw.write(dp[aParent] + "\n");
					continue;
				}
				
				// 두 부모가 다르다면?
				int sum = dp[aParent] + dp[bParent];
				bw.write(sum + "\n");
				
				union(a, b);
				dp[aParent] += dp[bParent]; // 합치고 값 갱신해주기
			}
			
			
		} // 전체 테스트 케이스 종료
		bw.flush();
		bw.close();
	}

	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a != b) parent[b] = a;
	}

	private static int find(int x) {
		if(parent[x] == x) return x;
		
		return parent[x] = find(parent[x]);
	}
}