import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
/*
 * 종수는 아두이노 한 대를 조정하며, 미친 아두이노를 피해다녀야 한다. 미친 아두이노는 종수의 아두이노를 향해 점점 다가온다.
 * 게임은 아래와 같은 5가지 과정이 반복된다.
 * 
 * 	1. 종수가 아두이노를 8방으로 이동 시키거나, 그대로 둔다.
 *  2. 종수의 아두이노가 미친 아두이노 칸으로 이동한 경우에는 게임이 끝나게 되며, 종수는 게임에서 진다.
 *  3. 미친 아두이노는 8방 중 종수의 아두이노와 가장 가까워지는 방향으로 한 칸 이동한다.
 *  4. 미친 아두이노가 종수의 칸으로 가면 종수는 게임에서 진다.
 *  5. 복수의 미친 아두이노가 같은 칸에 있는 경우에는 큰 폭발이 일어나고, 그 칸의 모든 아두이노는 파괴된다.
 *  
 * 주어진 방향대로 아두이노를 모두 움직인 후의 보드판을 출력하시오, 중간에 게임에서 지게 되면, 몇 번째 움직임에서 죽는지를 구한다.
 */
public class Main {
	
	static char[][] map;
	static int[] js = new int[2]; // 종수의 아두이노 위치
	static List<int[]> crazy = new ArrayList<>(); // 미친 아두이노 위치
	static int[] dr = {0, 1, 1, 1, 0, 0, 0, -1, -1, -1}, dc = {0, -1, 0, 1, -1, 0, 1, -1, 0, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken()); // 보드의 세로 R
		int C = Integer.parseInt(st.nextToken()); // 보드의 가로 C
		
		map = new char[R][C];
		
		for(int i = 0; i < R; i++) {
			String input = br.readLine();
			for(int j = 0; j < C; j++) {
				map[i][j] = input.charAt(j);
				if(map[i][j] == 'I') { js[0] = i; js[1] = j; }
				if(map[i][j] == 'R') crazy.add(new int[] {i, j});
			}
		} // 맵 정보 입력 완료
		
		String order = br.readLine();
		int time = 0;
		for(int i = 0; i < order.length(); i++) {
			char o = order.charAt(i);
			time++;
			
			if(!moveJongju(o) || !moveCrazy()) { // 미친을 만나면 false가 온다
				System.out.println("kraj " + time);
				return;
			}
			// 미친끼리 곂치는지 체크
			checkCrazy();
		}
		
		for(int i = 0; i < R; i++) { // 여기까지 오면 게임이 끝나지 않은 것이므로
			for(char m : map[i]) { // 맵을 출력한다.
				bw.write(m + "");
			}
			bw.write("\n");
		}
		bw.flush();
		bw.close();
	}

	private static boolean moveJongju(char o) { // 종수 움직이기
		int order = o - '0';
		// 가려는 곳에 미친이 있으면 false 반환
		if(map[js[0] + dr[order]][js[1] + dc[order]] == 'R') return false;
		
		map[js[0]][js[1]] = '.'; // 미친이 없으면 이동
		map[js[0] + dr[order]][js[1] + dc[order]] = 'I';
		
		js[0] += dr[order]; // 그리고 좌표 갱신
		js[1] += dc[order];
		return true;
	}
	
	private static boolean moveCrazy() { // 미친 아두이노 움직이기
		for(int i = 0; i < crazy.size(); i++) { // 미친 아두이노 좌표값 하나씩 꺼내서 비교
			int[] ci = crazy.get(i);
			int dir = 0;
			int min = 10000;
			for(int j = 1; j <= 9; j++) {
				int tx = ci[0] + dr[j];
				int ty = ci[1] + dc[j];
				
				int len = Math.abs(js[0] - tx) + Math.abs(js[1] - ty);
				if(min > len) { // 종수와 미친이 가장 가까워질 수 있는 이동 방향 확인하기
					dir = j; min = len;
				}
			}
			// 가장 가까운 방향으로 이동했을 때 종수가 있다면 false 반환
			if(map[ci[0] + dr[dir]][ci[1] + dc[dir]] == 'I') return false;
			
			map[ci[0]][ci[1]] = '.'; // 원래 미친의 자리 다 지우기
			crazy.get(i)[0] = ci[0] + dr[dir]; // 미친의 좌표 갱신
			crazy.get(i)[1] = ci[1] + dc[dir];
		}
		
		return true;
	}
	
	private static void checkCrazy() { // 미친끼리 곂치는 거 없애고 나머지 한 번에 이동
		int X = -1;
		int Y = -1;
		for(int[] c : crazy) { // 배열 생성을 위해 가장 큰 값만 가져오기
			X = Math.max(X, c[0]);
			Y = Math.max(Y, c[1]);
		}
		
		int[][] check = new int[X + 1][Y + 1];
		
		for(int[] c : crazy) {
			int x = c[0];
			int y = c[1];
			
			check[x][y] += 1; // 1을 초과하면 미친이 곂치는 것
		}
		crazy.clear(); // 리스트 비워주고
		for(int i = 0; i <= X; i++) {
			for(int j = 0; j <= Y; j++) {
				if(check[i][j] == 1) { // 안 곂치는 부분만 다시 리스트에 넣기
					crazy.add(new int[] {i, j});
					map[i][j] = 'R';
				}
			}
		}
	}
}