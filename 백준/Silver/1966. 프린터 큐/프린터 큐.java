import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 문서의 중요도를 확인한다.
 * 나머지 문서들 중 현재 문서보다 중요도가 높은 문서가 있다면, 그 문서를 먼저 출력해야 한다.
 * 
 * Queue에 있는 문서의 수와 중요도가 주어질 때 문서가 몇 번째로 인쇄되는지 알아내보자
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 수 T
		
		for(int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 문서의 개수 N
			int M = Integer.parseInt(st.nextToken()); // 몇 번째로 인쇄되었는지 궁금한 문서는 Queue의 M 번째에 있다.
			
			int[] print = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				print[i] = Integer.parseInt(st.nextToken());
			} // 각 문서의 중요도 입력 완료
			
			int result = goPrint(N, M, print);
			
			bw.write(result + "\n");
		}
		bw.flush();
		bw.close();
	}

	private static int goPrint(int n, int m, int[] print) {
		Queue<int[]> queue = new LinkedList<>();
		for(int i = 0; i < n; i++) {
			queue.add(new int[] {i, print[i]});
		}
		
 		Arrays.sort(print); // 제일 중요도가 큰 것부터
 		
 		int idx = n-1;
 		int result = 0;
 		while(!queue.isEmpty()) {
 			int[] curr = queue.poll();
 			
 			if(curr[1] == print[idx]) { // 중요도 확인
 				idx--;
 				result++;
 				
 				if(curr[0] == m) break; // m을 찾았으면 return
 			}
 			queue.add(curr); // 중요도가 다르면 다시 뒤에 집어넣기
 		}
		
		return result;
	}
}