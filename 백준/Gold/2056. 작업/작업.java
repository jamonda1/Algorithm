import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine()); // 수행해야 할 작업의 수 N
		
		List<Integer>[] list = new ArrayList[N + 1];
		
		int[] time = new int[N + 1]; // 해당 작업의 수행 시간 저장
		int[] degree = new int[N + 1];
		int[] result = new int[N + 1];
		
		for(int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
			
			st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken()); // 작업 소요 시간
			
			int size = Integer.parseInt(st.nextToken());// 해당 작업 전에 해야 할 작업의 수
			
			for(int j = 0; j < size; j++) {
				int A = Integer.parseInt(st.nextToken());
				list[A].add(i);
				degree[i]++;
			}
		}
		
		Queue<Integer> queue = new LinkedList<>();
		int max = 0;
		for(int i = 1; i <= N; i++) {
			if(degree[i] == 0) {
				queue.add(i);
				result[i] = time[i];
				max = Math.max(max, result[i]);
			}
		}
		
		while(!queue.isEmpty()) {
			int curr = queue.poll();
			
			for(int next : list[curr]) {
				result[next] = Math.max(result[next], result[curr] + time[next]);
				max = Math.max(max, result[next]);
				degree[next]--;
				if(degree[next] == 0) queue.add(next);
			}
		}
		
		System.out.println(max);
	}
}