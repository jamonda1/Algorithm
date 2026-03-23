import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); // 빌딩의 수 N
		
		int[] tower = new int[N+1];
		for(int i = 1; i <= N; i++) {
			tower[i] = Integer.parseInt(br.readLine());
		} // 빌딩의 층 수 저장
		
		Stack<Integer> stack = new Stack<>();
		
		long count = 0;
		for(int i = 1; i <= N; i++) {
			while(!stack.isEmpty()) {
				if(tower[stack.peek()] <= tower[i]) { // 다음 옥상을 볼 수 없으면?
					stack.pop(); // 맨 위를 제거하고 다시 시도
					continue;
				}
				count += stack.size(); // 볼 수 있으면 stack의 크기를 더하고 while 탈출
				break;
			}
			
			stack.push(i);
		}
		
		System.out.println(count);
	}
}