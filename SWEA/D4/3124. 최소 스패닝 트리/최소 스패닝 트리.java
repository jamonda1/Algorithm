import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	
	static class Node implements Comparable<Node> {
		int a;
		int b;
		int c;
		
		public Node(int a, int b, int c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}
		
		@Override
		public int compareTo(Node n) {
			return this.c - n.c;
		} // 가중치 기준 오름차순
	}
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			
			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken()); // 정점의 개수 V
			int E = Integer.parseInt(st.nextToken()); // 간선의 개수 E
			
			parent = new int[V + 1]; // 최상위 부모 초기화
			for(int i = 1; i <= V; i++) parent[i] = i;
			
			List<Node> list = new ArrayList<>();
			
			for(int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				
				list.add(new Node(a, b, c));
			} // 리스트에 간선 정보 입력 완료
			
			Collections.sort(list);
			
			long result = 0, count = 0;
			for(int i = 0; i < E; i++) {
				int a = list.get(i).a;
				int b = list.get(i).b;
				int c = list.get(i).c;
				
				if(find(a) == find(b)) continue;
				
				union(a, b);
				result += c;
				
				if(++count == V - 1) break;
			}
			
			bw.write("#" + t + " " + result + "\n");
			
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