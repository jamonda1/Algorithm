import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
/*
 * 반은 5 * 5다. 얼마 지나지 않아, '이다솜파'와 '임도연파'가 생겼다.
 * '임도연파'의 세력이 확장되어 '이다솜파'가 위협을 느꼈다.
 * 
 * '이다솜파'는 소문난 칠공주를 결성하기로 했다. 칠공주는 다음과 같은 규칙을 만족해야 한다.
 * 
 * 1. 반드시 7명이어야 한다.
 * 2. 7명의 자리는 가로나 세로로 반드시 인접해야 한다.
 * 3. '이다솜파'가 아니어도 괜찮다.
 * 4. 그래도 '이다솜파'가 4명 이상은 있어야 한다.
 * 
 * 입력의 S는 이다솜파, Y는 임도연파
 */
public class Main {
	
	static int result = 0;
	static char[][] seven = new char[5][5]; // 교실 입력
	static int[][] address = new int[25][2]; // 전체 좌표 입력
	static boolean[] combiVisited = new boolean[25]; // 조합을 위한 배열
	static int[] r = {-1, 1, 0, 0};
	static int[] c = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int idx = 0;
		for(int i = 0; i < 5; i++) {
			String input = br.readLine();
			for(int j = 0; j < 5; j++) {
				seven[i][j] = input.charAt(j);
				address[idx++] = new int[] {i, j};
			}
		} // 자리 배치 입력 완료
		
		combination(0, 0, new int[7][2]);
		
		System.out.println(result);
	}
	
	static void combination(int depth, int start, int[][] combi) {
		if(depth == 7) { // 조합이 완성되었다면?
			int sCnt = 0;
			boolean[][] linkCheck = new boolean[5][5];
			for(int i = 0; i < 7; i++) {
				if(seven[combi[i][0]][combi[i][1]] == 'S') sCnt++;
				linkCheck[combi[i][0]][combi[i][1]] = true;
			}
			
			if(sCnt < 4) return; // S가 4개 이상 포함되어야 한다.
			// BFS를 통해서 전부 연결된 것이 확인되면 ++
			if(isLinked(combi[0][0], combi[0][1], linkCheck)) result++;
			return;
		}
		
		for(int i = start; i < 25; i++) {
			if(!combiVisited[i]) {
				combiVisited[i] = true; // 선택 완료
				
				combi[depth][0] = address[i][0]; // x 좌표
				combi[depth][1] = address[i][1]; // y 좌표
				
				combination(depth + 1, i + 1, combi);
				combiVisited[i] = false;// 선택 해제
			}
		} // 조합 생성
	}
	
	static boolean isLinked(int x, int y, boolean[][] check) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {x, y});
		check[x][y] = false;
		
		int count = 0;
		while(!queue.isEmpty()) {
			count++;
			int[] curr = queue.poll();
			
			for(int i = 0; i < 4; i++) {
				int tx = curr[0] + r[i];
				int ty = curr[1] + c[i];
				
				if(tx < 0 || 5 <= tx || ty < 0 || 5 <= ty) continue;
				if(!check[tx][ty]) continue;
				
				check[tx][ty] = false;
				queue.add(new int[] {tx, ty});
			}
		}
		if(count == 7) return true;
		return false;
	}
}