import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Node implements Comparable<Node> {
		int x; double w;
		public Node(int x, double w) {
			this.x = x;
			this.w = w;
		}
		@Override
		public int compareTo(Node n) {
			return Double.compare(this.w, n.w);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 우주신들의 개수 N
		int M = Integer.parseInt(st.nextToken()); // 이미 연결된 신들과의 통로 개수 M
		
		long[][] list = new long[N + 1][2];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			long X = Long.parseLong(st.nextToken()); // 좌표값
			long Y = Long.parseLong(st.nextToken());
			
			list[i][0] = X;
			list[i][1] = Y;
		} // 전체 좌표 저장 완료
		
		boolean[] visited = new boolean[N + 1];// 연결 확인
		double[][] map = new double[N+1][N+1]; // 가중치 행렬
		
		for(int i = 1; i < N; i++) {
			for(int j = i + 1; j <= N; j++) {
				map[i][j] = Math.hypot(list[i][0] - list[j][0], list[i][1] - list[j][1]);
				map[j][i] = Math.hypot(list[i][0] - list[j][0], list[i][1] - list[j][1]);
			} // 대칭으로 등록
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken()); // 이것과
			int Y = Integer.parseInt(st.nextToken()); // 이것은 이미 연결되었다.
			
			map[X][Y] = 0;
			map[Y][X] = 0;
		} // 이미 연결된 것은 가중치를 0으로 바꿔준다.
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(1, 0)); // 초기는 1부터 출발하자
		
		double result = 0;
		int count = 0;
		while(!pq.isEmpty()) { // 프림 알고리즘 사용
			Node curr = pq.poll();
			int x = curr.x;
			double w = curr.w;
			
			if(visited[x]) continue;
			
			visited[x] = true;
			result += w;
			count++;
			
			if(count == N) break;
			
			for(int i = 1; i <= N; i++) {
				if(!visited[i]) {
					pq.add(new Node(i, map[x][i]));
				}
			}
		}
		
		System.out.printf("%.2f", Math.round(result * 100) / 100.0);
	}
}