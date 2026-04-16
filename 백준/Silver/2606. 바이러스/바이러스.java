import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static boolean[] result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine()); // 컴퓨터의 수
		int M = Integer.parseInt(br.readLine()); // 간선의 수
		
		List<Integer>[] computer = new ArrayList[N + 1]; // 리스트가 담겨있는 배열 생성. 1번 인덱스부터 사용 예정
		for(int i = 0; i <= N; i++) {
			computer[i] = new ArrayList<>();
		}
		
		result = new boolean[N + 1]; // 0번 인덱스는 사용 안 할 예정
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			computer[a].add(b); // 무향 리스트이므로
			computer[b].add(a); // 양방향으로 연결
		}
		
		virus(computer, 1, 1);
		
		int count = 0;
		for(boolean f : result) {
			if(f) count++;
		}
		bw.write(count - 1 + "");
		bw.flush();
		bw.close();
	}
	
	static void virus(List<Integer>[] computer, int idx, int depth) {
		if(depth == computer.length) {
			return;
		}
		// 처음 idx에 들어온 숫자를 기준으로 computer[idx]에 연결된 요소들을 가져온다.
		// 해당 요소들을 idx로 다시 전달해서 그것을 기준으로 연결된 요소를 다시 가져온다.
		// 이미 가져왔던 것을 또 가져오면 안 되니깐, 이미 방문한 곳을 boolean true로 변경해준다.
		// 이렇게 하면 idx=1일 때 요소 2와 5를 가져온 후 idx=2로 설정해서 다음 재귀에서 요소 1을 가져와도
		// 이미 1은 true가 되어 있기 때문에 무한루프에 빠지지 않는다.
		if(!result[idx]) {
			result[idx] = true; // 이건 해제해주면 안 된다.
			List<Integer> temp = computer[idx];
			for(int next : temp) {
				virus(computer, next, depth + 1);
			}
		}
	}
}