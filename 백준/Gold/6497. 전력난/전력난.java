import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
/*
 * 성진이는 한 도시의 시장인데, 거지라서 전력난에 시달리고 있다.
 * 그래서 모든 길마다 켜져 있던 가로등 중 일부를 소등하고자 한다.
 * 가로등을 키면 하루에 길의 미터 수만큼 돈이 들어가는데, 일부를 소등하면 그만큼의 돈을 절약할 수 있다.
 * 
 * 그런데 어떤 두 집을 왕래할 때 불이 꺼져 있는 곳을 지나면 위험하다.
 * 그래서 모든 두 집 쌍에 대해 불이 켜진 길만으로 서로 왕래할 수 있다.
 */
public class Main {
	
	static class Node implements Comparable<Node> {
		int x;
		int y;
		int z;
		
		public Node(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
		
		@Override
		public int compareTo(Node n) {
			return this.z - n.z;
		} // 가중치 오름차순
	}
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		for(;;) {
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken()); // 집의 수 m
			int n = Integer.parseInt(st.nextToken()); // 길의 수 n
			
			if(m == 0 && n == 0) break;
			
			parent = new int[m]; // 초기화
			for(int i = 0; i < m; i++) parent[i] = i;
			
			List<Node> list = new ArrayList<>();
			
			int allZ = 0;
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int z = Integer.parseInt(st.nextToken());
				allZ += z;
				
				list.add(new Node(x, y, z));
			} // 리스트에 간선 정보 입력 완료
			
			Collections.sort(list);
			
			int count = 0, edge = 0;
			for(int i = 0; i < n; i++) {
				int x = list.get(i).x;
				int y = list.get(i).y;
				int z = list.get(i).z;
				// 이미 부모가 같으면 패스
				if(find(x) == find(y)) continue;
				
				union(x, y);
				count += z;
				edge++;
				
				if(edge == m - 1) break;
			}
			// 전체 비용에서 연결된 수 빼주기
			bw.write(allZ - count + "\n");
			
		} // 전체 테스트 케이스 종료
		bw.flush();
		bw.close();
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