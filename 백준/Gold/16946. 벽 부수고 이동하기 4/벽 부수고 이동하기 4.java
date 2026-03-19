import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * N * M 맵이 있다. 1은 이동할 수 없는 벽이다. 벽을 부수고 이동할 수 있는 곳으로 변경한다.
 * 그 위치에서 이동할 수 있는 칸의 개수를 세어본다. 이동은 4방으로만 가능하다.
 */
public class Main {
	
	static class Node {
		int x, y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int N, M, area;
	static int[][] orgMap, cntMap, areaMap;
	static boolean[][] visited;
	static Queue<Node> queue = new LinkedList<>();
	static List<Node> list = new ArrayList<>();
	static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken()); // 맵의 세로 크기 N
        M = Integer.parseInt(st.nextToken()); // 맵의 가로 크기 M
        
        orgMap = new int[N][M]; // 정답 표시할 원본 맵
        cntMap = new int[N][M]; // 주변의 0을 카운트한 맵
        areaMap = new int[N][M]; // 구역을 나누는 맵
        visited = new boolean[N][M];
        
        List<Node> one = new ArrayList<>();
        for(int i = 0; i < N; i++) {
        	String input = br.readLine();
        	for(int j = 0; j < M; j++) {
        		orgMap[i][j] = input.charAt(j) - '0';
        		if(orgMap[i][j] == 1) one.add(new Node(i, j));
        	}
        } // 맵 정보 입력 완료
        
        area = 0;
        for(int i = 0; i < N; i++) {
        	for(int j = 0; j < M; j++) {
        		if(!visited[i][j] && orgMap[i][j] == 0) {
        			area++;
        			dfs(i, j);
        		}
        	}
        } // 탐색 종료
        
        for(int i = 0; i < one.size(); i++) {
        	int x = one.get(i).x;
			int y = one.get(i).y;
			
			int count = 1;
			boolean[] check = new boolean[area + 1];
			for(int j = 0; j < 4; j++) {
				int tx = x + dr[j];
				int ty = y + dc[j];
				if(tx < 0 || N <= tx || ty < 0 || M <= ty) continue;
				
				int target = areaMap[tx][ty];
				if(!check[target]) {
					check[target] = true;
					count += cntMap[tx][ty];
				}
			}
			
			orgMap[x][y] = count;
        }
        
        for(int i = 0; i < N; i++) {
        	for(int o : orgMap[i]) {
        		int result = o % 10;
        		bw.write(result + "");
        	}
        	bw.write("\n");
        }
        bw.flush();
        bw.close();
    }

	private static void dfs(int r, int c) {
		list.clear();
		queue.add(new Node(r, c));
		list.add(new Node(r, c));
		visited[r][c] = true;
		
		int count = 0;
		while(!queue.isEmpty()) {
			Node curr = queue.poll();
			int x = curr.x;
			int y = curr.y;
			count++;

			for(int i = 0; i < 4; i++) {
				int tx = x + dr[i];
				int ty = y + dc[i];
				if(tx < 0 || N <= tx || ty < 0 || M <= ty) continue;
				if(visited[tx][ty] || orgMap[tx][ty] == 1) continue;
				
				visited[tx][ty] = true;
				queue.add(new Node(tx, ty));
				list.add(new Node(tx, ty));
			}
		}
		setNum(count);
	}

	private static void setNum(int count) {
		for(int i = 0; i < list.size(); i++) {
			int x = list.get(i).x;
			int y = list.get(i).y;
			
			cntMap[x][y] = count;
			areaMap[x][y] = area;
		}
	}
}