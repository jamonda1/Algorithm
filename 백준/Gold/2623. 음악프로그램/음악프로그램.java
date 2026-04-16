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

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 가수의 수 N
		int M = Integer.parseInt(st.nextToken()); // PD의 수 M
		
		int[] indegree = new int[N + 1]; // 진입차수 1번부터 사용
		
		List<Integer>[] graph = new ArrayList[N + 1]; // 1번부터 사용
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		} // 그래프 초기화 완료
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken()); // 담당 가수들
			int[] tempArr = new int[size];
			for(int j = 0; j < size; j++) {
				tempArr[j] = Integer.parseInt(st.nextToken());
			} // 입력된 가수들 순서를 배열에 저장
			
			for(int j = 1; j < size; j++) {
				graph[tempArr[j-1]].add(tempArr[j]);
				indegree[tempArr[j]]++;
			} // 순서에 맞게 그래프 입력과 진입차수 증가
		}
		
		Queue<Integer> queue = new LinkedList<>();
		for(int i = 1; i <= N; i++) { // 진입차수가 0인 것부터 추가
			if(indegree[i] == 0) queue.add(i);
		}
		
		int count = 0;
		while(!queue.isEmpty()) {
			count++;
			int cur = queue.poll();
			bw.write(cur + "\n"); // 큐에 담긴 건 진입차수가 0인 것이므로 바로 출력
			
			for(int temp : graph[cur]) {
				indegree[temp]--;
				if(indegree[temp] == 0) queue.add(temp);
			}
		}
		
		if(count != N) { // 위상정렬된 수와 N이 다르면 정렬이 완벽하지 않다는 뜻.
			System.out.println(0);
			System.exit(0);
		}
		
		bw.flush();
		bw.close();
	}
}