import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
 * N개의 행성이 주어진다. 각 행성의 x y z 좌표가 주어질 때, 최소 비용으로 모든 행성을 연결하라
 */
public class Main {
	
	static class XYZ implements Comparable<XYZ>  {
		long n; int idx;
		public XYZ(long n, int idx) {
			this.n = n;
			this.idx = idx;
		}
		@Override
		public int compareTo(XYZ n) {
			return Long.compare(this.n, n.n);
		}
	}
	static class Node implements Comparable<Node> {
		int s, e; long w;
		public Node(int s, int e, long w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}
		@Override
		public int compareTo(Node n) {
			return Long.compare(this.w, n.w);
		}
	}
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine()); // 행성의 개수 N
        parent = new int[N];
        
        XYZ[][] xyz = new XYZ[3][N];

        for(int i = 0; i < N; i++) {
        	parent[i] = i;
        	st = new StringTokenizer(br.readLine());
        	for(int j = 0; j < 3; j++) {
        		xyz[j][i] = new XYZ(Long.parseLong(st.nextToken()), i);
        	}
        } // x y z 좌표 각각 저장 완료
        
        for(int i = 0; i < 3; i++) {
        	Arrays.sort(xyz[i]);
        } // 오름차순으로 정렬
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        for(int i = 0; i < N-1; i++) {
        	for(int j = 0; j < 3; j++) {
        		XYZ temp1 = xyz[j][i];
        		XYZ temp2 = xyz[j][i + 1];
        		
        		pq.add(new Node(temp1.idx, temp2.idx, Math.abs(temp1.n - temp2.n)));
        	}
        } // 간선의 가중치 입력
        
        long result = 0;
        int count = 0;
        while(!pq.isEmpty()) {
        	Node curr = pq.poll();
        	int a = curr.s;
        	int b = curr.e;
        	long w = curr.w;
        	
        	if(find(a) == find(b)) continue;
        	union(a, b);
        	result += w;
        	
        	if(++count == N - 1) break;
        }
        
        System.out.println(result);
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
