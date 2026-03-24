import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 배열 돌리기를 한 후 각 행을 더한 숫자들 중 최솟값을 출력하라
 * 배열은 시계방향으로 회전하며, 왼쪽 위는 (r-s, c-s), 오른쪽 아래는 (r+s, c+s)이다.
 */
public class Main {
    
	static class RCS {
		int r, c, s;
		public RCS(int r, int c, int s) {
			this.r = r;
			this.c = c;
			this.s = s;
		}
	}
	static int N, M, K, result = 987654321;
    static int[][] map, copy, orgMap;
    static RCS[] rcs;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken()); // 배열의 세로 N
        M = Integer.parseInt(st.nextToken()); // 배열의 가로 M
        K = Integer.parseInt(st.nextToken()); // 회전 연산의 수 K
        
        map = new int[N+1][M+1];
        copy = new int[N+1][M+1];
        orgMap = new int[N+1][M+1];
        
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                orgMap[i][j] = map[i][j];
            }
        } // 맵 입력 종료
        
        rcs = new RCS[K+1];
        for(int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            
            rcs[i] = new RCS(r, c, s);
        }
        
        permutation(0, new RCS[K], 0);
        
        System.out.println(result);
    }

	private static void permutation(int idx, RCS[] perArr, int visited) {
		if(idx == K) { // 회전 및 계산 수행
			for(int i = 0; i < K; i++) {
				RCS curr = perArr[i];
				int[] address = {curr.r - curr.s, curr.c - curr.s, curr.r + curr.s, curr.c + curr.s};
				
				for(int j = 1; j <= N; j++) copy[j] = map[j].clone(); // 카피 맵을 돌릴 예정
				move(address);
				for(int j = 1; j <= N; j++) map[j] = copy[j].clone(); // 돌아간 카피 맵을 다시 복사
			}
			
			for(int i = 1; i <= N; i++) { // 회전 종료 후 계산
	        	int sum = 0;
	        	for(int t : map[i]) {
	        		sum+= t;
	        	}
	        	result = Math.min(result, sum);
	        }
			for(int i = 1; i <= N; i++) map[i] = orgMap[i].clone(); // 카피 맵을 돌릴 예정
			return;
		}
		
		for(int i = 1; i <= K; i++) {
			if((visited & (1 << i)) == 0) {
				perArr[idx] = rcs[i]; // 비트마스킹을 활용한 방문 체크
				permutation(idx + 1, perArr, (visited | (1 << i)));
			}
		}
	}
	
	private static void move(int[] address) {
        int x1 = address[0]; // i 시작점
        int y1 = address[1]; // i 종료점
        int x2 = address[2]; // j 시작점
        int y2 = address[3]; // j 종료점

        if(x1 >= x2 || y1 >= y2) return;
        
        for(int i = y1; i < y2; i++) { // 윗면
        	copy[x1][i+1] = map[x1][i];
        }
		for(int i = x1; i < x2; i++) { // 오른쪽
		    copy[i+1][y2] = map[i][y2];
		}
		for(int i = y2; i > y1; i--) { // 아래쪽
			copy[x2][i-1] = map[x2][i];
		}
		for(int i = x2; i > x1; i--) { // 왼쪽
			copy[i-1][y1] = map[i][y1];
		}
        
        int[] next = new int[] {++x1, ++y1, --x2, --y2};
        move(next); // 한 칸 더 안쪽 돌리기
    }
}