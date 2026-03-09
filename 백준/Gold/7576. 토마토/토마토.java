import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 토마토는 잘 익은 것도 있지만, 안 익은 것도 있다.
 * 보관 후 하루가 지나면, [익은 토마토]의 [인접]한 곳에 있는 [안 익은 토마토]는 [익게 된다.]
 * (이때 인접하다는 것은 상하좌우의 4방위)
 * 토마토는 혼자서 저절로 익지 않는다.
 * 
 * 이때 모든 토마토가 익게 되는지 최소 일수를 구하라
 * (토마토가 없는 칸이 있을 수 있음에 주의)
 * 1 = 익토, 0 = 노익토, -1 = 빈칸
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// 익은 토마토를 기점으로 출발해서 -1은 방문하지 않고, 모든 곳을 돌아야 한다.
		// 만약 막혀서 익지 못하는 토마토가 있다면 -1을 출력한다.
		// 입력할 때 전체 노익토의 수를 구하자!
		
		int[] r = {-1, 1, 0, 0};
		int[] c = {0, 0, -1, 1};
		int tomato = 0;
		
		Queue<int[]> queue = new LinkedList<>();
		
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken()); // 상자의 가로 M
		int N = Integer.parseInt(st.nextToken()); // 상자의 세로 N
		
		int[][] box = new int[N][M];
		boolean[][] visited = new boolean[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				box[i][j] = Integer.parseInt(st.nextToken());
				if(box[i][j] == 0) tomato++; // 안 익은 토마토 개수 카운트
				if(box[i][j] == 1) {
					queue.add(new int[] {i, j, 0}); // 익은 토마토 위치 담기
					visited[i][j] = true; // 익은 토마토가 있는 곳은 방문하지 않아도 된다.
				}
			}
		} // 박스에 토마토 입력 종료
		
		int time = 0; // 소요 시간 저장
		first : while(!queue.isEmpty()) {
			int[] current = queue.poll();
			
			for(int i = 0; i < 4; i++) {
				int X = current[0] + r[i];
				int Y = current[1] + c[i];
				
				// 범위 밖으로 나가면 다시
				if(X < 0 || N <= X || Y < 0 || M <= Y) continue;
				// 빈 칸이면 다시
				if(box[X][Y] == -1 || visited[X][Y]) continue;
				
				visited[X][Y] = true; // 방문 처리
				tomato--; // 방문했으니 토마토 차감
				if(tomato == 0) { // 차감했는데 0이면?
					time = current[2] + 1; // 자신의 위치 다음을 확인한 것이라서 +1
					break first;
				}
				queue.add(new int[] {X, Y, current[2] + 1});
			}
		}
		// 반복문 종료 이후에도 익어야 하는 토마토가 남아있다면 모두 익지 못하는 상황이다.
		System.out.println(tomato == 0 ? time : -1);
	}
}