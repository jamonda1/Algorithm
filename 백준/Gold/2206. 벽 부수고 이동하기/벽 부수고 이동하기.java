import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * (0, 0)에서 (N-1, M-1)로 이동해야 한다. 벽은 딱 한 번 부술 수 있다.
 * 최단 거리를 출력해라. 불가능하다면 -1을 출력해라
 */
public class Main {
    
    static int N, M, time = Integer.MAX_VALUE;
    static char[][] map;
    static boolean[][][] visited;
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken()); // 맵의 크기 N
        M = Integer.parseInt(st.nextToken()); // 주어진 좌표의 수 M
        
        map = new char[N][M];
        visited = new boolean[2][N][M];
        
        for(int i = 0; i < N; i++) {
            String input = br.readLine();
            for(int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j);
            }
        } // 맵 입력 완료
        
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {0, 0, 1, 0}); // x, y, 시간, 찬스
        visited[0][0][0] = true;
        
        while(!queue.isEmpty()) {
        	int[] curr = queue.poll();
        	int x = curr[0];
        	int y = curr[1];
        	
        	if(x == N - 1 && y == M - 1) {
        		time = Math.min(time, curr[2]);
        		continue;
        	}
        	
        	for(int i = 0; i < 4; i++) {
        		int tx = x + dr[i];
        		int ty = y + dc[i];
        		
        		if(tx < 0 || N <= tx || ty < 0 || M <= ty) continue;
        		if((curr[3] == 0 && visited[0][tx][ty]) || (curr[3] == 1 && visited[1][tx][ty])) continue;
        		
        		if(curr[3] == 0) { // 찬스를 안 썼다.
        			if(map[tx][ty] == '1') { // 벽이 나오면 부수자
        				visited[1][tx][ty] = true; // 찬스를 썼을 경우의 방문으로 넘기기
        				queue.add(new int[] {tx, ty, curr[2] + 1, 1});
        			} else {
        				visited[0][tx][ty] = true; // 찬스를 안 썼을 경우의 방문
        				queue.add(new int[] {tx, ty, curr[2] + 1, 0});
        			}
        		}
        		if(curr[3] == 1) { // 찬스를 썼다.
        			if(map[tx][ty] == '1') continue;
        			else {
        				visited[1][tx][ty] = true; // 이미 찬스를 썼으니깐 이쪽으로
        				queue.add(new int[] {tx, ty, curr[2] + 1, 1});
        			}
        		}
        	}
        }
        
        int result = (time == Integer.MAX_VALUE) ? -1 : time;
        System.out.println(result);
    }
}