import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 지도는 N * M의 크기다.
 * 각 섬의 영역 알아내기 -> 해당 섬의 테두리 찾기 -> 
 * -> 조회해서 거리 계산한 다음 가장 가까운 곳 저장하기,
 */
public class Main {
    
    static class Node implements Comparable<Node> {
        int a;
        int b;
        int w;
        public Node(int a, int b, int w) {
            this.a = a;
            this.b = b;
            this.w = w;
        }
        @Override
        public int compareTo(Node n) {
            return this.w - n.w;
        } // 가중치 기준 오름차순
    }
    static int N, M, idx;
    static int[] parent;
    static char[][] map;
    static boolean[][] visited;
    static List<Node> nodeList;
    static List<List<int[]>> list = new ArrayList<>();
    static Map<String, Integer> adrsList = new HashMap<>(); // [좌표(Key), 구역(Value)]들이 리스트 형태로 저장
    static Queue<int[]> queue = new LinkedList<>();
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken()); // 맵의 세로 크기 N
        M = Integer.parseInt(st.nextToken()); // 맵의 가로 크기 M
        
        map = new char[N][M];
        visited = new boolean[N][M];
        
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = st.nextToken().charAt(0);
            }
        } // 맵 정보 입력 완료
        
        list.add(new ArrayList<>());
        
        idx = 1; // 구역 확인을 위한 idx
        for(int i = 0; i < N; i++) { // 구역 나누기를 위한 방문 배열 필요
            for(int j = 0; j < M; j++) {
                if(!visited[i][j] && map[i][j] == '1') {
                    list.add(new ArrayList<>());
                    divideArea(i, j);
                    idx++; // 구역 하나 나눴으니 ++
                }
            }
        }
        
        nodeList = new ArrayList<>();
        
        for(int i = 1; i < idx; i++) {
            for(int[] curr : list.get(i)) { // 해당 좌표에서 쭉 직진해보자.
                checkConnect(i, curr[0], curr[1], curr[2]);
            }
        } // 이 반복문이 끝나면, 모든 가중치가 입력된다.
        
        Collections.sort(nodeList);
        parent = new int[idx];
        for(int i = 1; i < idx; i++) parent[i] = i;
        
        int count = 0;
        int cost = 0;
        for(Node n : nodeList) {
        	int a = n.a;
        	int b = n.b;
        	int w = n.w;
        	// 이미 연결되어 있으면 패스
        	if(find(a) == find(b)) continue;
        	
        	union(a, b);
        	count++;
        	cost += w;
        	// 마지막으로 idx++로 인해서 -2를 해줘야 필요한 간선의 수와 맞아진다
        	if(count == idx - 2) break;
        }
        
        System.out.println(count == idx - 2 ? cost : -1);
    }

    private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if(a != b) parent[b] = a;
	}

	private static int find(int a) {
		if(parent[a] == a) return a;
		return parent[a] = find(parent[a]);
	}


	private static void divideArea(int r, int c) { // 구역 나누는 메서드
        visited[r][c] = true;
        queue.add(new int[] {r, c}); // 초기값 넣기
        
        while(!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0];
            int y = curr[1];
            
            for(int i = 0; i < 4; i++) {
                int tx = x + dr[i];
                int ty = y + dc[i];
                
                if(tx < 0 || N <= tx || ty < 0 || M <= ty || visited[tx][ty]) continue;
                if(map[tx][ty] == '0') { // 주변에 물이 있으면 테두리
                    String str = x + "," + y;// 이 값이 Key가 된다.
                    adrsList.put(str, idx); // 그러니 curr의 좌표를 HashMap에 구역과 함께 추가
                    list.get(idx).add(new int[] {x, y, i}); // i에는 방향이 담긴다.
                    continue;
                }
                
                visited[tx][ty] = true;
                queue.add(new int[] {tx, ty});
            }
        }
    }

    private static void checkConnect(int area, int row, int col, int dir) {
        int tx = row;
        int ty = col;
        
        int count = 0;
        for(;;) {
            tx += dr[dir];
            ty += dc[dir];
            count++;
            
            if(tx < 0 || N <= tx || ty < 0 || M <= ty) break; // 맵을 벗어나면 안 된다.
            if(map[tx][ty] == '0') continue;
            
            String str = tx + "," + ty;
            
            if(adrsList.get(str) == area) {
            	break; // 같은 영역이면 안 된다.
            } else {
            	if(count - 1 >= 2) { // 같은 영역도 아니고, 다리 길이 2를 충족한다면?
            		nodeList.add(new Node(area, adrsList.get(str), count-1));
            	}
            	break;
            }
        }
    }
}