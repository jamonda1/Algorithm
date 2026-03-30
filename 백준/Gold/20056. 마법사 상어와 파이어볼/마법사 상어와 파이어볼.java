import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
/*
 * 크기가 N * N인 격자에 파이어볼 M개를 발사했다. 가장 처음에 파이어볼은 각자 위치에서 이동을 대기하고 있다.
 * 맵의 끝과 끝은 연결되어 있으므로, 만약 파이어볼이 맵을 벗어나게 되면, 반대쪽으로 이동하게 된다.
 * 각각의 파이어볼에는 위치와 질량, 방향, 속력이 있다. 위치는 1부터 N까지의 행열이다. 파이어볼의 방향은 상단 0부터 시계방향으로 7까지다.
 * 이제 마법사는 모든 파이어볼에게 명령을 한다.
 * 
 *   1. 모든 파이어볼은 자신의 방향 d로 속력 s만큼 이동한다. (이동하는 중에는 같은 칸에 여러 개의 파이어볼이 있을 수도 있다.)
 *   2. 이동이 모두 끝난 뒤, 2개 이상의 파이어볼이 있는 칸에서는 다음과 같은 일이 일어난다.
 *   	1) 같은 칸의 파이어볼은 하나가 된다.
 *   	2) 파이어볼은 4개로 나누어진다.
 *   	3) 나뉜 파이어볼의 질량, 속력, 방향은 다음과 같다.
 *   		- 질량 : 합쳐진 파이어볼 질량의 합 / 5
 *   		- 속력 : 합쳐진 파이어볼 속력의 합 / 합쳐진 파이어볼의 개수
 *   		- 방향 : 합쳐진 파이어볼의 방향이 짝수 또는 모두 홀수면 0, 2, 4, 6이고, 그렇지 않으면 1, 3, 5, 7이다.
 *   	4) 질량이 0이되면 소멸된다.
 *   
 * K번 이동한 후에 남아있는 파이어볼 질량의 합을 구해라!
 */
public class Main {
	
	static class Fire {
		int x, y, w, d, s; // x 좌표, y 좌표, 질량, 방향, 속력
		public Fire(int x, int y, int w, int d, int s) {
			this.x = x; this.y = y;
			this.w = w; this.d = d; this.s = s;
		}
	}
	static int N;
	static List<Fire> fire = new ArrayList<>();
	static List<Fire> moveDone = new ArrayList<>();// 이동이 끝난 불 정보가 담기는 리스트
	static List<Fire> allDone = new ArrayList<>(); // 불 합치기와 나누기가 끝난 것이 담기는 리스트
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1}, dc = {0, 1, 1, 1, 0, -1, -1, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 맵의 크기 N
		int M = Integer.parseInt(st.nextToken()); // 파이어볼의 개수 M
		int K = Integer.parseInt(st.nextToken()); // 명령 횟수
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			int w = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			fire.add(new Fire(x, y, w, d, s));
		} // 초기 불의 위치 입력 완료
		
		// 불 이동 -> 불 합치기 -> 불 나누기 -> 불 나누기까지 끝난 리스트를 fire에 복사하기 -> 반복
		for(int i = 0; i < K; i++) {
			moveFire(); // K번 만큼 반복문 수행
		}
		
		int result = 0;
		for(Fire f : fire) result += f.w;
		
		System.out.println(result);
	}

	private static void moveFire() {
		moveDone.clear(); // 메서드 실행 전 초기화
		int size = fire.size();
		
		// 불의 속도만큼 더한 값을 새로운 리스트에 저장해줘야 한다.
		for(int i = 0; i < size; i++) {
			Fire curr = fire.get(i);
			// 음수가 나오면 N을 더해주고, N을 넘으면 N을 빼준다.
			int speed = curr.s % N;
			
			int tx = curr.x + dr[curr.d] * speed;
			int ty = curr.y + dc[curr.d] * speed;

			if(tx < 0) tx += N;
			if(ty < 0) ty += N;
			if(N <= tx) tx -= N;
			if(N <= ty) ty -= N;
			
			moveDone.add(new Fire(tx, ty, curr.w, curr.d, curr.s));
		}
		
		sumAndDivide(); // 이동한 파이어볼 합치고 나누는 메서드
	}

	private static void sumAndDivide() { // 이동한 파이어볼을 합치는 메서드
		allDone.clear(); // 메서드 실행 전 초기화
		int size = moveDone.size();
		boolean[] picked = new boolean[size];
		
		for(int i = 0; i < size; i++) {
			if(picked[i]) continue; // 이미 확인한 불은 패스
			picked[i] = true;
			
			int cnt = 1; // 이게 2 이상이면 같은 위치에 불이 여럿 존재
			Fire curr = moveDone.get(i);
			int pivot = curr.d % 2; // curr이 짝수면 0, 홀수면 1이 저장
			boolean f = true;
			int sumW = curr.w;
			int sumS = curr.s;
			
			for(int j = i+1; j < size; j++) { // i번 인덱스와 같은 위치에 있는 불 탐색
				if(picked[j]) continue;
				Fire next = moveDone.get(j);
				
				if(curr.x == next.x && curr.y == next.y) {
					picked[j] = true;
					cnt++;
					sumW += next.w;
					sumS += next.s;
					if(next.d % 2 != pivot) f = false;
				}
			}
			if(cnt == 1) { // 좌표에 단 하나의 불만 있을 경우
				allDone.add(curr);
			} else if(cnt > 1 && (sumW / 5) > 0) { // 좌표에 복수의 불이 있을 경우
				for(int j = 0; j < 7; j+=2) {
					allDone.add(new Fire(curr.x, curr.y, sumW / 5, f ? j : j+1, sumS / cnt));
				}
			}
		}
		
		fire.clear(); // 원본 리스트 초기화 후에
		fire.addAll(allDone); // 모든 작업이 끝난 리스트 넣어주기
	}
}