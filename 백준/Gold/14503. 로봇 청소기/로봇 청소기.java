import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 로봇 청소기와 방의 상태가 주어진다. 청소하는 영역의 개수를 구해라
 * 방의 크기는 N * M 각각의 칸은 벽 또는 빈 칸, 청소기에는 방향이 있고 4방 중 하나다.
 * 
 * 처음 빈 칸은 전부 청소되지 않았다.
 * 1. 칸이 청소되지 않았으면 청소한다.
 * 2. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없다면?
 *         1. 바라보는 방향 유지해서 한 칸 후진 가능하면 후진하고 1번으로 간다.
 *         2. 후진할 수 없다면 작동 중지
 * 3. 지금 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있다면?
 *         1. 반시계로 90도 회전
 *         2. 바라보는 방향 기준 앞이 청소되지 않았으면 한 칸 전진한다.
 *         3. 1번으로 돌아간다.
 */
public class Main {
    
    static char[][] room;
    static int N, M, r, c, d, cleanCnt = 0;
    static boolean[][] clean;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken()); // 방의 세로 N
        M = Integer.parseInt(st.nextToken()); // 방의 가로 M
        room = new char[N][M];
        clean = new boolean[N][M];
        
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken()); // 청소기의 초기 좌표
        c = Integer.parseInt(st.nextToken()); // 청소기의 초기 좌표
        d = Integer.parseInt(st.nextToken()); // 초기 방향 0상, 1우, 2하, 3좌
        
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                room[i][j] = st.nextToken().charAt(0);
                if(room[i][j] == '1') clean[i][j] = true;
            }
        } // 방 상태 입력 종료
        
        for(;;) {
        	cleanRoom();        	
        }
    }
    
    static void cleanRoom() {
    	 if(!clean[r][c]) { // 지금 로봇 위치가 청소되어 있지 않다면?
    		 clean[r][c] = true; // 청소 완료
    		 cleanCnt++;
    	 }
    	 
    	 boolean f = true;
         for(int i = 0; i < 4; i++) {
             int tempX = r + dr[i];
             int tempY = c + dc[i];
             
             if(tempX < 0 || N <= tempX || tempY < 0 || M <= tempY) continue;
             if(room[tempX][tempY] == '1') continue;
             
             if(!clean[r + dr[i]][c + dc[i]]) f = false;
         } // 청소가 안 된 칸이 하나라도 있다면 false가 된다.
         
         if(f) { // 주변이 다 깨끗하면?
	     	 int temp = (d < 2 ? d + 2 : d - 2); // 후진을 위해
	     	 int tempX = r + dr[temp];
	         int tempY = c + dc[temp];
             
             // 후진할 곳이 맵 밖이거나, 벽이라면 종료
             if(tempX < 0 || N <= tempX || tempY < 0 || M <= tempY) {
             	System.out.println(cleanCnt);
             	System.exit(0);
             }
             if(room[tempX][tempY] == '1') {
             	System.out.println(cleanCnt);
             	System.exit(0);
             }
         	
             r = tempX; // 후진이 가능하면 후진하기
             c = tempY;
         } else { // 주변에 청소할 곳이 있다면?
         	 int temp = d;
         	 d = (temp > 0 ? temp - 1 : 3); // 반시계 90도 회전
         	 int tempX = r + dr[d]; // 청소기가 바라보는 곳
             int tempY = c + dc[d];
         	
             if(room[tempX][tempY] != '1' && !clean[tempX][tempY]) {
             	r = tempX;
             	c = tempY;
             }
         }
    }
}