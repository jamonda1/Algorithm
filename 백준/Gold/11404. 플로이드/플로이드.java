import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	static int MAX = 987654321;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine()); // 도시의 개수 N
		int M = Integer.parseInt(br.readLine()); // 버스의 개수 M
		
		int[][] dist = new int[N+1][N+1];
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(i == j) dist[i][j] = 0;
				else dist[i][j] = MAX;
			}
		} // 가중치 초기화 완료
		
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); // a 도시에서
			int b = Integer.parseInt(st.nextToken()); // b 도시로 가는
			int c = Integer.parseInt(st.nextToken()); // 버스의 비용은 c
			
			dist[a][b] = Math.min(dist[a][b], c); // 중복 노선들이 있으니, 최소 비용만 저장
		}
		
		for(int k = 1; k <= N; k++) {
			for(int i = 1; i <= N; i++) {
				// i에서 k로 가는 길이 없으면 그냥 패스
				if(dist[i][k] == MAX) continue;
				for(int j = 1; j <= N; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			} // 가중치 초기화 완료
		}
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				int pivot = dist[i][j] == MAX ? 0 : dist[i][j];
				bw.write(pivot + " ");
			}
			bw.write("\n");
		}
		
		bw.close();
	}
}
