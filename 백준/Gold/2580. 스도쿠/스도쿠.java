import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
/*
 * 스도쿠 규칙에 맞게 0을 채워라
 */
public class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static boolean f = false;
	static int[][] map;
	static List<int[]> list = new ArrayList<>();
	static int[][] area = {{0, 0, 2, 2}, {0, 3, 2, 5}, {0, 6, 2, 8},
						   {3, 0, 5, 2}, {3, 3, 5, 5}, {3, 6, 5, 8},
						   {6, 0, 8, 2}, {6, 3, 8, 5}, {6, 6, 8, 8}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		map = new int[9][9];
		
		for(int i = 0; i < 9; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 9; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) list.add(new int[] {i, j});
			}
		} // 맵 정보 입력 완료
		
		setNum(0); // 메서드 호출
		
		bw.flush();
		bw.close();
	}
	
	static void setNum(int index) throws IOException {
		if(index == list.size()) { // 여기까지 왔으면 모두 완성
			for(int i = 0; i < 9; i++) {
				for(int j = 0; j < 9; j++) {
					bw.write(map[i][j] + " ");
				}
				bw.write("\n");
			}
			f = true;
			return;
		}
		
		int[] curr = list.get(index);
		int area = findArea(curr[0], curr[1]); // 어느 구역인지 확인
		
		for(int i = 1; i <= 9; i++) { // 가로와 세로 체크, 구역 체크 후 다 ok면 놓기
			boolean f1 = checkCross(curr[0], -1, i);
			boolean f2 = checkCross(-1, curr[1], i);
			boolean f3 = checkArea(i, area);
			
			if(f1 && f2 && f3) {
				map[curr[0]][curr[1]] = i;
				if(!f) setNum(index + 1);
				map[curr[0]][curr[1]] = 0;
			}
		}
	}

	private static boolean checkArea(int num, int areaNum) {
		int[] currArea = area[areaNum];
		int sx = currArea[0], sy = currArea[1];
		int ex = currArea[2], ey = currArea[3];
		
		for(int i = sx; i <= ex; i++) {
			for(int j = sy; j <= ey; j++) {
				if(map[i][j] == num) return false;
			}
		}
		
		return true;
	}

	private static boolean checkCross(int x, int y, int num) {
		// x가 0이면 세로 체크, y가 0이면 가로 체크
		for(int i = 0; i < 9; i++) {
			int pivot = map[(x == -1) ? i : x][(y == -1) ? i : y];
			if(pivot == num) return false;
		}
		
		return true;
	}

	private static int findArea(int x, int y) { // 구역 확인 메서드
		if(0 <= x && x <= 2) {
			if(0 <= y && y <= 2) return 0;
			if(3 <= y && y <= 5) return 1;
			if(6 <= y && y <= 8) return 2;
		}
		if(3 <= x && x <= 5) {
			if(0 <= y && y <= 2) return 3;
			if(3 <= y && y <= 5) return 4;
			if(6 <= y && y <= 8) return 5;
		}
		if(6 <= x && x <= 8) {
			if(0 <= y && y <= 2) return 6;
			if(3 <= y && y <= 5) return 7;
			if(6 <= y && y <= 8) return 8;
		}
		return 0;
	}
}