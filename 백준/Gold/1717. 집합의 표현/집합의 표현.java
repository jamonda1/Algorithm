import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
/*
 * n + 1개의 집합이 있다. 합집합 연선과 두 원소가 같은 집합에 포함되어 있는지 확인하자
 * n과 m이 주어진다. m은 연산의 횟수
 * 0 a b의 형태로 주어지면 a의 집합과 b의 집합 합치기
 * 1 a b의 형태로 주어지면 a와 b가 같은 집합에 있는지 확인하는 연산
 * 
 * 1로 시작하는 입력에서 같은 집합에 있으면 YES, 아니면 NO를 출력
 */
public class Main {
	
	static int n, m;
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken()); // 노드의 수 n
		m = Integer.parseInt(st.nextToken()); // 연선의 수 m
		
		parent = new int[n + 1];
		for(int i = 0; i <= n; i++) {
			parent[i] = i; // 최상위 부모를 자기 자신으로 초기화한다.
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int o = Integer.parseInt(st.nextToken()); // 명령어 o
			int a = Integer.parseInt(st.nextToken()); // 노드 a
			int b = Integer.parseInt(st.nextToken()); // 노드 b
			
			if(o == 0) { // a 집합과 b 집합 합치기
				union(a, b);
			}
			if(o == 1) { // a와 b가 같은 집합에 있는지
				String answer = (find(a) == find(b)) ? "YES" : "NO";
				bw.write(answer + "\n");
			}
		}
		
		bw.flush();
		bw.close();
	}

	private static void union(int a, int b) {
		a = find(a); // a의 최상위 부모 찾기
		b = find(b); // b의 최상위 부모 찾기
		
		if(a != b) parent[b] = a; // 서로 부모가 다르면 b에 a 등록
	}

	private static int find(int a) {
		if(parent[a] == a) return a; // 자기 자신이 최상위면 바로 리턴
		
		// 그게 아니라면 자신에게 등록된 부모의 부모를 찾으러 재귀
		return parent[a] = find(parent[a]);
	}
}