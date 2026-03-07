import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 두 명의 플레이어가 번갈아가면서 게임을 진행한다. 선이 홀수, 후가 짝수 차례다.
 * 게임 시작 시 0~n-1까지 번호가 있는 평면상의 점 n 개가 주어지고 이중 어느 세 점도 일직선 상에 있지 않다.
 * 매 차례마다 두 플레이어는 두 점을 선택해서 이를 연결하는 선붓을 긋는다.
 * 게임은 한 점에서 출발해서 모든 선분을 지나 다시 되돌아오면 종료된다.
 * 
 * 그런데 사이클 완성 여부를 판단하기 어려워서 게임 진행 상황이 주어지면,
 * 몇 번째에 사이클이 완성되었는지, 아니면 아직인지 판단하는 프로그램을 작성하고자 한다.
 * 사이클이 완성되었으면 차례를, 아직이면 0을 출력하자
 */
public class Main {
	
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken()); // 점의 개수 n
		int m = Integer.parseInt(st.nextToken()); // 진행 횟수 m
		
		parent = new int[n]; // 최상위 초기값은 자기 자신
		for(int i = 0; i < n; i++) parent[i] = i;
		
		int time = 0;
		for(int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(find(a) == find(b)) {
				time = i;
				break;
			} // 연결할 두 점의 최상위 부모가 이미 같다면 순환할 수 있다.
			union(a, b); // 최상위 부모가 다르면 연결하자
		}
		
		System.out.println(time);
	}

	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a != b) parent[b] = a;
	}

	private static int find(int a) {
		if(parent[a] == a) return a;
		
		return parent[a] = find(parent[a]);
	}
}