import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 상근이는 1층 빌딩에 침입해 매우 중요한 문서를 훔쳐오려고 한다. 상근이는 4방 이동이 가능하고, 문서 위치가 있는 평면도가 있다.
 * 빌딩의 문은 모두 잠겨있어서, 열쇠가 필요하다. 일부는 가지고 있고, 일부는 바닥에 있다. 훔칠 수 있는 최대 문서 개수를 구해라
 */
public class Main {
	
	static class Node {
		int x, y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int H, W, result;
	static char[][] map;
	static boolean[] keys;
	static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++) {
        	result = 0;
        	
        	st = new StringTokenizer(br.readLine());
        	H = Integer.parseInt(st.nextToken()); // 맵의 세로 H
        	W = Integer.parseInt(st.nextToken()); // 맵의 가로 W
        	
        	map = new char[H+2][W+2];
        	keys = new boolean[26];

        	for(int i = 0; i < H+2; i++) {
        		Arrays.fill(map[i], '.');
        	} // 테두리를 *로 채우기
        	
        	for(int i = 1; i < H+1; i++) {
        		String input = br.readLine();
        		for(int j = 0; j < W; j++) {
        			map[i][j + 1] = input.charAt(j);
        		}
        	}
        	
        	String input = br.readLine(); // 상근이가 이미 가지고 있는 열쇠
        	if(!input.equals("0")) {
        		for(int i = 0; i < input.length(); i++) {
        			keys[input.charAt(i) - 'a'] = true;
        		} // 이미 가지고 있는 열쇠 저장
        	}
        	
        	bfs();
        	
        	bw.write(result + "\n");
        } // 전체 테스트 케이스 종료
        bw.close();
    }

	private static void bfs() {
		Queue<Node> queue = new LinkedList<>();
		boolean[][] visited = new boolean[H+2][W+2];
		queue.add(new Node(0, 0));
		visited[0][0] = true;
		
		int count = 0;
		while(!queue.isEmpty()) {
			Node curr = queue.poll();
			int x = curr.x;
			int y = curr.y;
			
			for(int i = 0; i < 4; i++) {
				int tx = x + dr[i];
				int ty = y + dc[i];
				
				if(tx < 0 || H+2 <= tx || ty < 0 || W+2 <= ty || map[tx][ty] == '*' || visited[tx][ty]) continue;
				boolean door = ('A' <= map[tx][ty] && map[tx][ty] <= 'Z') ? true : false; // 진행 방향이 문이면 true
				boolean key = ('a' <= map[tx][ty] && map[tx][ty] <= 'z') ? true : false; // 진행 방향이 열쇠면 true
				boolean doc = (map[tx][ty] == '$') ? true : false; // 진행 방향이 문서인가?
				if(door && !keys[map[tx][ty] - 'A']) continue; // 문인데 열쇠가 없으면 패스
				if(key && !keys[map[tx][ty] - 'a']) {
					keys[map[tx][ty] - 'a'] = true;
					visited = new boolean[H+2][W+2];
					count = 0;
				} // 새로운 열쇠 찾으면 방문배열 초기화
				if(doc) count += 1;
				visited[tx][ty] = true;
				queue.add(new Node(tx, ty));
			}
		}
		
		result = Math.max(result, count);
	}
}