import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 구슬은 N번만 쏠 수 있다. 구슬은 좌우로 움직일 수 있어서 항상 맨 위의 벽돌만 깨트린다.
 * 
 *  1. 벽돌은 숫자 1~9로 표현되고, 구슬이 명중한 벽돌은 상하좌우 숫자-1 만큼 같이 제거된다.
 *  2. 제거되는 범위 내에 있는 벽돌은 동시에 제거된다.
 *  3. 동시에 제거되는 벽돌도 똑같이 숫자-1 만큼 주변 벽돌에 영향을 준다.
 *  
 * 구슬을 N번만 썼을 때 최대한 많은 벽돌을 제거한 후, 남은 벽돌의 개수를 구해라!!!
 */
public class Solution {
	
	static class Obj {
		int x, y, c;
		public Obj(int x, int y, int c) {
			this.x = x;
			this.y = y;
			this.c = c;
		}
	}
	static int N, W, H, copy[][], result;
	static Queue<Obj> queue = new LinkedList<>();
	static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
// ---------- main start ---------- //
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        int TC = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= TC; tc++) {
        	result = Integer.MAX_VALUE;
        	
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 구슬 사용 횟수 N
            W = Integer.parseInt(st.nextToken()); // 맵의 가로 W
            H = Integer.parseInt(st.nextToken()); // 맵의 세로 H
            
            int[][] map = new int[H][W];
            
            for(int i = 0; i < H; i++) {
            	st = new StringTokenizer(br.readLine());
            	for(int j = 0; j < W; j++) {
            		map[i][j] = Integer.parseInt(st.nextToken());
            	}
            } // 맵 정보 입력 완료
            
            copy = new int[H][W];
            play(0, map); // 메서드 시작
            
            bw.write("#" + tc + " " + result + "\n");
        } bw.close(); // 전체 테스트 케이스 종료
    }
// ---------- main end ---------- //
    
    private static void play(int depth, int[][] move) {
    	if(depth == N) { // 기저에 도달하면 카운트 후에 리턴
    		int cnt = 0;
    		for(int i = 0; i < H; i++) {
    			for(int j = 0; j < W; j++) {
    				if(move[i][j] != 0) cnt++;
    			}
    		}
    		result = Math.min(result, cnt);
    		return;
    	}
    	
    	// 원복 저장 -> 구슬 떨구기 -> 블럭 터트리기 -> 블럭 떨구기 -> 재귀 -> 원복
    	for(int i = 0; i < W; i++) { // 0부터 W-1까지 구슬을 N번 떨어트려보자
    		int[][] next = breakBlock(i, move); // 블럭 터트리기, 이 안에서 떨구는 메서드도 호출
    		play(depth + 1, next);
    	}
    }

	private static int[][] breakBlock(int sy, int[][] move) { // 블럭 터트리는 메서드
		queue.clear();
		for(int i = 0; i < H; i++) copy[i] = move[i].clone();
		
		int sx = 0;
		for(int i = 0; i < H; i++) {
			if(copy[i][sy] != 0) {
				sx = i; break;
			}
		} // 초기 x 위치 찾기
		queue.add(new Obj(sx, sy, copy[sx][sy]));
		
		while(!queue.isEmpty()) {
			Obj curr = queue.poll();
			
			for(int i = 0; i < 4; i++) { // 4방향 탐색
				int tx = curr.x;
				int ty = curr.y;
				copy[tx][ty] = 0; // 자기 자신 깨지니깐 0으로 해주기
				
				for(int j = 0; j < curr.c-1; j++) { // 블럭에 적힌 수-1까지 뻗어야 함
					tx += dr[i];
					ty += dc[i];
					if(tx < 0 || H <= tx || ty < 0 || W <= ty || copy[tx][ty] == 0) continue;
					if(copy[tx][ty] > 1) queue.add(new Obj(tx, ty, copy[tx][ty]));
					copy[tx][ty] = 0;
				}
			}
		}
		
		return dropBlock(copy); // 블럭 다 터트렸으면 내려야 한다.
	}

	private static int[][] dropBlock(int[][] copy) { // 블럭 중력 메서드
		int[][] temp = new int[H][W];
		
		for(int i = 0; i < W; i++) {
			int idx = H-1; // temp를 위한 idx
			for(int j = H-1; j >= 0; j--) {
				if(copy[j][i] == 0) continue;
				temp[idx--][i] = copy[j][i];
			}
		}
		
		return temp;
	}
}