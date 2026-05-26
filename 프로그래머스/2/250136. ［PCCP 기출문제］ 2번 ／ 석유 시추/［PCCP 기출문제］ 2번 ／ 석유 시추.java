// 각 구역 나누기
// 배열에 구역별 석유량 저장하기
// 첫 번째 [j][i] 돌면서 쭉 내려가보기
class Solution {
    
    int N, M, area;
    int[][] map, land;
    int[] cost = new int[150_000]; // 넉넉하게
    int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
    int[] xq = new int[270_000];
    int[] yq = new int[270_000];
    boolean[][] visited;
    
    public int solution(int[][] land) {
        this.land = land;
        
        N = land.length;   // 맵의 세로 N
        M = land[0].length;// 맵의 가로 M
        
        map = new int[N][M]; // 구역
        visited = new boolean[N][M];
        
        area = 1;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(land[i][j] == 1 && !visited[i][j]) {
                    int oil = divideArea(i, j, 1); // 구역 나누기
                    cost[area] = oil;
                    area++;
                }
            }
        }
        
        return go();
    }
    
    int divideArea(int sx, int sy, int oil) { // 구역 나누는 메서드
        int h = 0, t = 0;
        
        xq[t] = sx; yq[t++] = sy; // 초기값 삽입
        map[sx][sy] = area;
        visited[sx][sy] = true;
        
        while(h != t) {
            int x = xq[h];
            int y = yq[h++];
            
            for(int i = 0; i < 4; i++) {
                int tx = x + dr[i];
                int ty = y + dc[i];
                // 범위밖, 방문 완료, 0은 패스
                if(tx < 0 || N <= tx || ty < 0 || M <= ty || visited[tx][ty] || land[tx][ty] == 0) continue;
                visited[tx][ty] = true;
                map[tx][ty] = area;
                oil++;
                xq[t] = tx; yq[t++] = ty;
            }
        }
        
        return oil;
    }
    
    int go() {
        int result = 0;
        
        for(int i = 0; i < M; i++) {
            boolean[] check = new boolean[area + 1]; // 각 구역 체크했는지 확인
            int value = 0;
            for(int j = 0; j < N; j++) {
                if(map[j][i] == 0 || check[map[j][i]]) continue;
                check[map[j][i]] = true;
                value += cost[map[j][i]];
            }
            result = Math.max(result, value);
        }
        
        return result;
    }
}