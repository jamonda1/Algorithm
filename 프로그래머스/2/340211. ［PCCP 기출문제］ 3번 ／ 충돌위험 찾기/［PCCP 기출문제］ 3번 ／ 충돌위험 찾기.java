class Solution {
    
    final int INF = Integer.MAX_VALUE;
    int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
    
    public int solution(int[][] points, int[][] routes) {
        int mx = 0, my = 0;
        for(int i = 0; i < points.length; i++) {
            mx = Math.max(points[i][0], mx);
            my = Math.max(points[i][1], my);
        }
        
        int x = routes.length;
        int[] rx = new int[x];
        int[] ry = new int[x];
        int[] np = new int[x];
        boolean[] out = new boolean[x];
        
        for(int i = 0; i < x; i++) {
            int p = routes[i][0] - 1;
            np[i] = 1;
            rx[i] = points[p][0];
            ry[i] = points[p][1];
        }
        
        int result = 0, cnt = 0;
        while(cnt < x) {
            int[][] check = new int[mx + 10][my + 10];
            for(int i = 0; i < x; i++) {
                if(out[i]) continue;
                if(check[rx[i]][ry[i]]++ == 1) result++;
            }
            
            for(int i = 0; i < x; i++) {
                if(out[i]) continue;
                int p = routes[i][np[i]] - 1;
                if(points[p][0] == rx[i] && points[p][1] == ry[i]) np[i]++;
                if(np[i] >= routes[i].length) {
                    out[i] = true; cnt++;
                }
            }
            
            for(int i = 0; i < x; i++) {
                if(out[i]) continue;
                int p = routes[i][np[i]] - 1;
                int min = INF, nx = 0, ny = 0;
                for(int j = 0; j < 4; j++) {
                    int tx = rx[i] + dr[j];
                    int ty = ry[i] + dc[j];
                    
                    int len = Math.abs(tx - points[p][0]) + Math.abs(ty - points[p][1]);
                    if(min > len) {
                        min = len;
                        nx = tx; ny = ty;
                    }
                }
                rx[i] = nx; ry[i] = ny;
            }
        }
        
        return result;
    }
}