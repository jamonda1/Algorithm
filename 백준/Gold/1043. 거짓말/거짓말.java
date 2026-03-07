import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
/*
 * 지민이는 파티에서 진실을 말하거나 과장해서 말한다.
 * 몇몇 사람은 과장된 이야기의 진실을 안다. 그래서 그런 사람이 왔을 때는 진실만 이야기한다.
 * 어떤 사람이 파티에서 진실을 듣고, 다음 파티에서 과장을 들어도 거짓말쟁이가 된다.
 * 
 * 사람의 수 N이 주어지고, 진실을 아는 사람이 주어진다. 지민이는 모든 파티에 참석한다.
 * 거짓말쟁이로 알려지지 않으면서 진실을 말할 수 있는 파티의 수는 몇 개인가?
 */
public class Main {
	
	static int[] parent;
	static List<Integer>[] partyList;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 사람의 수
		int M = Integer.parseInt(st.nextToken()); // 파티의 수
		
		parent = new int[N + 1];
		partyList = new ArrayList[M + 1]; // 파티 참석자들
		
		for(int i = 1; i <= N; i++) {
			parent[i] = i; // 최상위는 자기 자신으로 초기화
		}
		
		for(int i = 1; i <= M; i++) {
			partyList[i] = new ArrayList<>();
		} // 파티 참석자 리스트를 만들기 위해 초기화
		
		st = new StringTokenizer(br.readLine());
		int size = Integer.parseInt(st.nextToken()); // 진실을 아는 사람의 수
		
		for(int i = 0; i < size; i++) {
			int temp = Integer.parseInt(st.nextToken());
			union(0, temp);
		} // 진실을 아는 사람들은 0으로 묶어놓기
		
		
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			size = Integer.parseInt(st.nextToken()); // 파티에 참석하는 사람 수
			
			if(size == 0) continue; // 파티에 오는 사람이 없다면 패스
			
			int firstPerson = Integer.parseInt(st.nextToken());
			partyList[i].add(firstPerson);
			
			for(int j = 0; j < size - 1; j++) {
				int next = Integer.parseInt(st.nextToken());
				partyList[i].add(next);
				
				union(firstPerson, next); // 첫 번째 사람에 모두 연결
			}
		}
		
		int count = lastCheck(M);
		
		System.out.println(M - count);
	}

	private static void union(int a, int b) {
		a = find(a); // a에는 입력된 값의 최상위 부모가 위치한다.
		b = find(b);
		
		if(a != b) {
			if(a < b) parent[b] = a;
			else parent[a] = b;
		}
	}

	private static int find(int a) {
		if(parent[a] == a) return a;
		
		return parent[a] = find(parent[a]);
	}
	
	private static int lastCheck(int m) {
		int count = 0;
		
		for(int i = 1; i <= m; i++) {
			boolean f = false;
			
			for(int t : partyList[i]) {
				if(find(t) == 0) {
					f = true;
					break;
				} // 해당 파티에 참석하는 인원의 최상위 부모 확인
			}	  // 한 명이라도 0이 보이면 안 된다.
			if(f) count++;
		}
		// 최상위 부모가 0인 애들이 참석하는 파티가 몇 개인지 리턴
		return count;
	}
}