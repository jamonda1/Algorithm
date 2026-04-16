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
 * 어떤 과목들은 선수과목이 있어서 그것을 들어야 수강할 수 있다.
 * 각각의 과목 번호가 주어지면, 해당 과목들을 몇 번째 학기에 이수하게 되는지 구하라
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 과목의 수 N
		int M = Integer.parseInt(st.nextToken()); // 선수 조건의 수 M
		
		int[] when = new int[N + 1]; // 어느 학기에 해당 과목을 듣게 되는가?
		int[] degree = new int[N + 1]; // 1~N까지
		
		List<Integer>[] graph = new ArrayList[N + 1]; // 1~N까지
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			graph[A].add(B);
			degree[B]++;
		} // 그래프 초기화 및 입력, 차수 증가 완료
		
		
		Queue<Integer> queue = new LinkedList<>();
		for(int i = 1; i <= N; i++) {
			if(degree[i] == 0) queue.add(i);
		} // 1번이 A
		
		int semester = 1; // 1학기부터 시작
		while(!queue.isEmpty()) {
			int size = queue.size(); 
			
			for(int i = 0; i < size; i++) { // 처음 큐에 들어있는 횟수만큼 탐색 시작
				int curr = queue.poll();    // curr에 담기는 것은 수강한 과목
				
				when[curr] = semester; // 수강한 학기 저장
				
				for(int temp : graph[curr]) {
					degree[temp]--;
					if(degree[temp] == 0) {
						queue.add(temp);
					}
				}
			}
			semester++; // 학기 증가
		}
		
		for(int i = 1; i <= N; i++) {
			bw.write(when[i] + " ");
		}
		bw.flush();
		bw.close();
	}
}