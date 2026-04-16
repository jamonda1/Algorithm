import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 하나의 건물을 짓기 전에 이전 건물을 완성해야 한다. N개의 각 건물이 완성되기까지 걸리는 최소 시간을 출력하라
 * 
 * 첫 줄에 건물 종류의 수 N이 주어진다. 다음 N개의 줄에는 건물 짓는데 걸리는 시간과 먼저 지어야 하는 건물 번호가 있다.
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine()); // 건물 종류의 수 N
		
		List<Integer>[] graph = new LinkedList[N + 1]; // 건물은 1번부터 시작
		int[] time = new int[N + 1];  // 건물 완성 시간
		int[] degree = new int[N + 1];// 진입 차수
		
		for(int i = 1; i <= N; i++) {
			graph[i] = new LinkedList<>();
		}
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken()); // 첫 번째 토큰은 건물 완성 시간
			
			for(;;) {
				int temp = Integer.parseInt(st.nextToken());
				if(temp == -1) break; // -1이 나오면 입력 종료
				
				graph[temp].add(i); // temp를 짓기 전에 i를 먼저 지어야 한다.
				degree[i]++;
			}
		}
		
		int[] result = new int[N + 1]; // 각 건물을 짓기 위한 최종 시간이 저장될 배열
		Queue<Integer> queue = new LinkedList<>();
		for(int i = 1; i <= N; i++) {
			if(degree[i] == 0) {
				result[i] = time[i];
				queue.add(i);
			}
		}

		while(!queue.isEmpty()) {
			int curr = queue.poll();
			
			for(int next : graph[curr]) {
				// next를 짓는 기존의 시간과 curr을 짓는 시간 + next를 짓는 시간을 비교
				result[next] = Math.max(result[next], result[curr] + time[next]);
				
				degree[next]--;
				if(degree[next] == 0) {
					queue.add(next);
				}
			}
		}
		
		for(int i = 1; i <= N; i++) {
			bw.write(result[i] + "\n");
		}
		bw.flush();
		bw.close();
	}
}