import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 생쥐는 S에서 출발한다. 각 구역에는 치즈 공장 또는 장애물, 빈 땅이 있다.
 * 생쥐는 모든 치즈 공장을 돌면서 치즈를 하나씩 먹는다. 마을에는 N개의 치즈 공장이 있다. 각 공장은 1~N까지의 치즈를 생산한다.
 * 생쥐는 체력 1에서 시작하여 치즈 하나를 먹을 때마다 체력이 1씩 증가한다. 여기서, 생쥐는 자신의 체력보다 낮은 숫자는 먹을 수 없다.
 * 4방으로 이동하면서 맵에 있는 모든 치즈를 먹는 최소한의 시간을 구해라. 한 번 이동할 때 1분이 소요된다.
 * 그리고 생쥐는 반드시 모든 치즈를 먹을 수 있다는 것이 보장되어 있다.
 */
public class Main {
	
	static class Cheese {
		int x, y, t;
		char c;
		public Cheese(int x, int y, int t, char c) {
			this.x = x;
			this.y = y;
			this.t = t;
			this.c = c;
		}
	}
	static int H, W, N, nezumi_x, nezumi_y, result = 0;
	static char[][] map;
	static boolean[][][] visited;
	static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        H = Integer.parseInt(st.nextToken()); // 맵의 세로 H
        W = Integer.parseInt(st.nextToken()); // 맵의 가로 W
        N = Integer.parseInt(st.nextToken()); // 치즈 종류의 수 N
        
        map = new char[H][W];
        visited = new boolean[N + 1][H][W];
        
        for(int i = 0; i < H; i++) {
        	String input = br.readLine();
        	for(int j = 0; j < W; j++) {
        		map[i][j] = input.charAt(j);
        		if(map[i][j] == 'S') {
        			nezumi_x = i; nezumi_y = j;
        		}
        	}
        } // 맵 정보 입력 완료
        
        bfs();
        
        System.out.println(result);
    }
	
	static void bfs() {
		Queue<Cheese> queue = new LinkedList<>();
		queue.add(new Cheese(nezumi_x, nezumi_y, 0, '0'));
		visited[0][nezumi_x][nezumi_y] = true;
		
		while(!queue.isEmpty()) {
			Cheese curr = queue.poll();
			int x = curr.x;
			int y = curr.y;
			int t = curr.t; // 소요 시간
			int c = curr.c - '0'; // 어떤 종류까지 먹었나?
			
			if(c == N) { // N번까지 다 먹었으면 리턴!
				result = t; return;
			}
			
			for(int i = 0; i < 4; i++) {
				int tx = x + dr[i];
				int ty = y + dc[i];
				
				if(tx < 0 || H <= tx || ty < 0 || W <= ty) continue;
				if(visited[c][tx][ty] || map[tx][ty] == 'X') continue;
				
				if(map[tx][ty] == '.' || (map[tx][ty] - '0') != (c + 1)) { // 길이거나, 지금 먹으면 안 되는 치즈일 때
					visited[c][tx][ty] = true;
					queue.add(new Cheese(tx, ty, t + 1, (char) (c + '0')));
				} else if((map[tx][ty] - '0') == (c + 1)) { // 내가 먹은 치즈보다 1 높은 치즈일 때
					visited[c + 1][tx][ty] = true;
					queue.add(new Cheese(tx, ty, t + 1, map[tx][ty]));
				}
			}
		}
	}
}
