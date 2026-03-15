import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
 * 도현이는 아무렇게나 널브러져 있는 n개의 별들을 이어서 별자리 하나를 만들 것이다. 조건은 다음과 같다.
 * 		1. 별자리를 이루는 선은 서로 다른 두 별을 일직선으로 이은 형태이다.
 * 		2. 모든 별들은 별자리 위의 선을 통해 서로 직/간접적으로 이어져 있어야 한다.
 * 선을 하나 이을 때마다 두 별 사이의 거리만큼의 비용이 든다. 별자리를 만드는 최소 비용을 구해라!
 */
public class Main {
	
	static class Star {
		double x, y;
		public Star(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}
	static class Sign implements Comparable<Sign> {
		int x; double w;
		public Sign(int x, double w) {
			this.x = x;
			this.w = w;
		}
		@Override
		public int compareTo(Sign s) {
			return Double.compare(this.w, s.w);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine()); // 별의 개수 N
		
		Star[] stars = new Star[N + 1];
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			
			stars[i] = new Star(x, y);
		} // 별들의 좌표 입력 및 저장 완료
		
		double[][] graph = new double[N + 1][N + 1]; // 가중치 저장
		boolean[] visited = new boolean[N + 1]; // 연결 확인
		
		for(int i = 1; i < N; i++) {
			for(int j = i + 1; j <= N; j++) {
				double w = Math.sqrt(Math.pow(stars[i].x - stars[j].x, 2) + Math.pow(stars[i].y - stars[j].y, 2));
				
				graph[i][j] = w; // 대칭으로 연결해줘야 한다.
				graph[j][i] = w;
			}
		} // 각 별들간의 가중치 계산 및 저장 완료
		
		PriorityQueue<Sign> pq = new PriorityQueue<>();
		pq.add(new Sign(1, 0)); // 초기값 추가 및 연결 처리 완료
		
		double count = 0;
		int conn = 0;
		while(!pq.isEmpty()) {
			Sign curr = pq.poll();
			int x = curr.x;
			double w = curr.w;
			
			if(visited[x]) continue;
			
			visited[x] = true;
			count += w;
			conn++;
			
			if(conn == N) break;
			
			for(int i = 2; i <= N; i++) {
				if(!visited[i]) {
					pq.add(new Sign(i, graph[x][i]));
				}
			}
		}
		
		System.out.println(count);
	}
}