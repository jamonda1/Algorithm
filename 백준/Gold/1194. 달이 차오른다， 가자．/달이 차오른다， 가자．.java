import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Node implements Comparable<Node> {
		int x; int y; int cnt; int keyCnt;
		boolean[] key = new boolean[6];

		public Node(int x, int y, int cnt, int keyCnt, boolean[] key) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.keyCnt = keyCnt;
			this.key = key;
		}

		@Override
		public int compareTo(Node n) {
			return this.cnt - n.cnt;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		Map<Character, Integer> parse = new HashMap<>();
		int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
		int sx = 0, sy = 0;
		
		int N = Integer.parseInt(st.nextToken()); // 맵의 세로 크기 N
		int M = Integer.parseInt(st.nextToken()); // 맵의 가로 크기 M
		
		char[][] map = new char[N][M];
		int[][][] dist = new int[64][N][M]; // 모든 열쇠의 조합에 대응하기 위한 64
		
		for(int i = 0; i < 64; i++) {
			for(int j = 0; j < N; j++) {
				Arrays.fill(dist[i][j], Integer.MAX_VALUE);
			}
		} // 가중치 전체 초기화
		
		for(int i = 0; i < N; i++) {
			String input = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = input.charAt(j);
				if(map[i][j] == '0') { // 민식이 위치 확인
					sx = i; sy = j;
				}
			}
		} // 맵 정보 입력 완료
		
		parse.put('a', 1);
		parse.put('b', 2);
		parse.put('c', 4);
		parse.put('d', 8);
		parse.put('e', 16);
		parse.put('f', 32); // 비트 마스킹 대신 사용
		
		pq.add(new Node(sx, sy, 0, 0, new boolean[6])); // 첫 0은 시간이고, 다음 0은 열쇠 개수
		dist[0][sx][sy] = 0;
		
		while(!pq.isEmpty()) {
			Node curr = pq.poll();
			int x = curr.x;
			int y = curr.y;
			int cnt = curr.cnt;
			int kc = curr.keyCnt;
			if(cnt > dist[kc][x][y]) continue;
			if(map[x][y] == '1') {
				System.out.println(cnt);
				return;
			}
			
			for(int i = 0; i < 4; i++) {
				int tx = x + dr[i];
				int ty = y + dc[i];
				int tc = cnt + 1;
				int tkc = kc;
				boolean[] getKey = curr.key.clone();
				
				if(tx < 0 || N <= tx || ty < 0 || M <= ty || map[tx][ty] == '#') continue;
				if('a' <= map[tx][ty] && map[tx][ty] <= 'f') { // 다음을 봤는데 열쇠다?
					if(!getKey[map[tx][ty] - 'a']) tkc += parse.get(map[tx][ty]);
					getKey[map[tx][ty] - 'a'] = true;
				}
				if('A' <= map[tx][ty] && map[tx][ty] <= 'F') { // 다음을 봤는데 문이다?
					if(!getKey[map[tx][ty] - 'A']) continue; // 열쇠가 없으면 패스
				}
				
				if(tc >= dist[tkc][tx][ty]) continue;
				dist[tkc][tx][ty] = tc;
				pq.add(new Node(tx, ty, tc, tkc, getKey));
			}
		} // 탐색 종료
		
		System.out.println(-1); // 여기까지 오면 민식이 탈출 실패
	}
}
