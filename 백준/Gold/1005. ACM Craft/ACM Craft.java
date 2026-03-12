import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 건물마다 건설 가능 순서가 있고, 소요 시간이 있다.
 * 특정 건물의 번호가 주어질 때 해당 건물을 완성하는데 드는 최소 시간을 출력하라
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 건물의 개수 N
			int K = Integer.parseInt(st.nextToken()); // 건설 순서의 개수 K
			
			List<Integer>[] list = new ArrayList[N + 1];
			int[] buildTime = new int[N + 1]; // 건물 건설 소요 시간 입력
			int[] degree = new int[N + 1]; // 진입 차수 입력
			int[] result = new int[N + 1]; // 해당 건물 완성 소요 시간 입력
			
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= N; i++) {
				buildTime[i] = Integer.parseInt(st.nextToken());
				list[i] = new ArrayList<>();
			} // 건물 건설 소요 시간 입력 완료
			
			
			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				
				list[A].add(B);
				degree[B]++;
			} // 리스트 단방향 추가 및 진입차수 증가 완료
			
			
			Queue<Integer> queue = new LinkedList<>();
			for(int i = 1; i <= N; i++) {
				if(degree[i] == 0) {
					queue.add(i);
					result[i] = buildTime[i];
				}
			} // 차수가 0인 것만 넣기
			
			while(!queue.isEmpty()) {
				int curr = queue.poll();
				
				for(int next : list[curr]) {
					degree[next]--;
					result[next] = Math.max(result[next], result[curr] + buildTime[next]);
					if(degree[next] == 0) queue.add(next);
				}
			}
			
			
			int W = Integer.parseInt(br.readLine()); // 찾고자 하는 건물의 번호 W

			bw.write(result[W] + "\n");
			
		} // 전체 테스트 케이스 종료
		bw.flush();
		bw.close();
	}
}