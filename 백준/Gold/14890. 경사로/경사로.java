import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
 * N * N인 지도에 각각 높이가 적혀있다.
 * 한 행이나 열의 높이가 모두 같으면 지나갈 수 있는 길이다.
 * 높이가 다르면 경사로를 놓을 수 있다. 경사로의 높이는 1이며, 길이는 L이다.
 */
public class Main {
	
	static int N, L;
	static int[][] map;
	static int[] dr = {0, 1}, dc = {1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 맵의 크기 N
		L = Integer.parseInt(st.nextToken()); // 경사로의 길이 L
		
		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 맵 정보 입력 완료
		
		int result = 0;
		for(int i = 0; i < N; i++) {
			if(check(i, 0, 0)) result++; // 가로 탐색
			if(check(0, i, 1)) result++; // 세로 탐색
		}
		
		System.out.println(result);
	}

	private static boolean check(int row, int col, int d) {
		int pivot = map[row][col];
		int count = 1; // 경사로를 만들기 위한 평평의 길이
		
		int tx = row, ty = col;
		for(int i = 0; i < N; i++) {
			tx += dr[d]; // pivot의 다음 칸 확인
			ty += dc[d];
			
			if(N <= tx || N <= ty) return true; // 만약 다음 칸이 맵 밖이면? 무사히 끝까지 탐색한 것이다.
			if(map[tx][ty] == pivot) {
				count++; // 다음 칸이 피벗과 같으면 평평의 길이 늘려주기
				continue;
			}
			
			if(map[tx][ty] == pivot + 1 && count >= L) { // 다음 칸이 피벗보다 한 칸 높다면?
				pivot = map[tx][ty];
				count = 1;
			} else if(map[tx][ty] == pivot - 1 ) { // 만약 다음 칸이 피벗보다 한 칸 낮다면?
				int next = checkNext(tx, ty, d);
				if(next >= L) {
					pivot = map[tx][ty];
					count = 0; // 아직 지금 칸에서 다음 칸으로 이동 안 했기 때문에 0으로 해줘야 한다.
					tx += dr[d] * (L - 1);
					ty += dc[d] * (L - 1);
				} else return false; // 아니면 탈락
			} else return false; // 아니면 탈락
		}
		
		return false;
	}

	private static int checkNext(int tx, int ty, int d) {
		int pivot = map[tx][ty];
		
		int count = 1;
		for(int i = 0; i < N; i++) {
			tx += dr[d]; // 다음 칸 확인
			ty += dc[d];
			// 맵 밖이거나 pivot과 다르면 바로 리턴
			if(N <= tx || N <= ty || map[tx][ty] != pivot) return count;
			if(map[tx][ty] == pivot) count++;
		}
		
		return count;
	}
}