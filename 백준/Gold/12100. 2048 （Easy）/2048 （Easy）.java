import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * N * N의 맵이 있다. 최대 5번 이동해서 만들 수 있는 가장 큰 수를 출력해라!
 */
public class Main {
	
	static class Obj {
		int[][] map; int cnt;
		public Obj(int[][] moveMap, int cnt) {
			this.map = moveMap;
			this.cnt = cnt;
		}
	}
	static int N, result = 0;
	static Queue<Obj> queue = new LinkedList<>();
// ---------- main start ---------- //
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine()); // 맵의 크기 N
        
        int[][] map = new int[N][N];
        
        for(int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j = 0; j < N; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        	}
        } // 맵 정보 입력 완료
        
        queue.add(new Obj(map, 0)); // 초기값 넣고 bfs 호출
        bfs();
        
        System.out.println(result);
    }
 // ---------- main end ---------- //
	private static void bfs() {
		while(!queue.isEmpty()) {
			Obj curr = queue.poll();
			if(curr.cnt == 5) {
				for(int i = 0; i < N; i++) {
					for(int j = 0; j < N; j++) {
						result = Math.max(result, curr.map[i][j]);
					}
				} continue;
			} // 5번 다 돌렸으면 숫자의 최댓값 구하기
			
			for(int i = 0; i < 4; i++) { // 더하기 -> 큐에 넣기 -> curr맵 돌리기
				int[][] movedMap = move(curr.map);
				queue.add(new Obj(movedMap, curr.cnt + 1));
				curr.map = rotate(curr.map);
			}
		}
	}
	
	private static int[][] move(int[][] map) { // 움직여서 더하기
		int[][] movedMap = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			int idx = 0;
			for(int j = 0; j < N; j++) {
				if(map[i][j] == 0) continue;
				// moveMap이 0이면 숫자 넣어주기, map이랑 같으면 *2 해주고 다음칸, 다르면 다음칸에 숫자 넣기
				if(movedMap[i][idx] == 0) movedMap[i][idx] = map[i][j];
				else if(movedMap[i][idx] == map[i][j]) movedMap[i][idx++] *= 2;
				else movedMap[i][++idx] = map[i][j];
			}
		}
		
		return movedMap;
	}
	
	private static int[][] rotate(int[][] map) { // 맵 돌리기
		int[][] rotated = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				// N이 3이라면, 02 12 22 -> 01 11 12 순으로 map[i][j]를 저장
				rotated[j][N-1-i] = map[i][j];
			}
		}
		
		return rotated;
	}
}