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

		// A B가 입력되면 A가 B보다 작다는 뜻(즉 앞에 와야 한다)
		// 답이 여러가지인 경우에는 아무거나 출력
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 학생의 수 N
		int M = Integer.parseInt(st.nextToken()); // 키를 비교한 횟수 M
		
		int[] indegree = new int[N + 1]; // 이곳에 저장된 숫자가 높을 수록 큰 값이다.
		
		List<Integer>[] graph = new ArrayList[N + 1]; // 이곳에 키 정보 저장할 예정
		for(int i = 1; i <= N; i++) { // 0번 인덱스는 사용 x
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) { // 키 비교한 결과를 graph에 단방향으로 저장
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			graph[A].add(B); // 순서가 있는 거라서 단방향으로 저장
			indegree[B]++;   // 큰 곳 값++
		}
		
		Queue<Integer> queue = new LinkedList<>();
		for(int i = 1; i <= N; i++) { // indegree가 0이라는 것은 키가 제일 작다는 뜻.
			if(indegree[i] == 0) queue.add(i);
		}
		
		while(!queue.isEmpty()) { // 큐에 값이 있다면 반복
			int current = queue.poll();
			bw.write(current + " "); // 작은 순서대로 출력된다.
			
			for(int temp : graph[current]) { // current에 있는 것과 연결된 곳을 찾아보자
				indegree[temp]--; // 일단 줄이고
				
				// 줄인 값이 0이 되면 큐에 담는다.
				if(indegree[temp] == 0) queue.add(temp);
			}
		}
		// 예제 입력 1의 경우 1 3, 2 3을 입력하면서 각각의 indegree는 0 0 2가 된다.
		// 또한 큐에 1과 2가 담기게 되고, 처음에 1을 출력. 1과 연결된 3을 확인
		// 3의 indegree를 -1했지만, 여전히 1로 0이 아님. 이후 2를 출력
		// 2와 연결된 3을 보고 indegree를 -1하여 0이 되고 3을 출력. 큐는 Empty가 되면서 종료
		bw.flush();
		bw.close();
	}
}