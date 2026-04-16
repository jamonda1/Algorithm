import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int[] move = {-1, 1};
		
		Queue<int[]> queue = new LinkedList<>();
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 수빈이의 초기 위치
		int K = Integer.parseInt(st.nextToken()); // 동생의 위치
		
		boolean[] visited = new boolean[100001];
		
		if(N == K) { // 수빈이와 동생의 위치가 같다면?
			System.out.println(0);
			System.exit(0);
		}
		if(K < N) { // 동생이 수빈이보다 뒤에 있다면?
			System.out.println(N - K);
			System.exit(0);
		}
		
		// -1, +1, *2
		int time = 0; // 소요 시간 저장
		queue.add(new int[] {N, 0}); // 수빈이의 초기 위치
		visited[N] = true;
		
		while(!queue.isEmpty()) {
			int[] current = queue.poll();
			
			if(current[0] == K) { // 동생의 위치를 찾았다면?
				time = current[1];
				break;
			}
			for(int i = 0; i < 3; i++) {
				int X = current[0];
				if(i != 2) { // 수빈이가 걸을 경우
					X += move[i];
				} else { // 수빈이가 순간이동 할 경우
					X *= 2;
				}
				
				// 전체 범위를 벗어나면 안 된다.
				if(X < 0 || 100000 < X) continue;
				// 이미 방문했으면 안 된다.
				if(visited[X]) continue;
				
				visited[X] = true;
				queue.add(new int[] {X, current[1] + 1});
			}
		}
		System.out.println(time);
	}
}