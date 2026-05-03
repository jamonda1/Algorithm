import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class Solution {
     
    static int N, tc, cnt;
    static char[][] map = new char[300][300];
    static int[][] visited;
    static int[] qx = new int[300*300]; // x좌표 저장할 큐
    static int[] qy = new int[300*300]; // y좌표 저장할 큐
    static int[] dr = {-1, 1, 0, 0, -1, -1, 1, 1}, dc = {0, 0, -1, 1, -1, 1, -1, 1};
     
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
         
        int TC = Integer.parseInt(br.readLine());
        visited = new int[300][300];
         
        for(tc = 1; tc <= TC; tc++) {
             
            N = Integer.parseInt(br.readLine()); // 맵의 크기 N
             
            cnt = 0;
            for(int i = 0; i < N; i++) {
                char[] input = br.readLine().toCharArray();
                for(int j = 0; j < N; j++) {
                    map[i][j] = input[j];
                    if(map[i][j] == '.') cnt++;
                }
            } // 맵 입력 완료
             
            int result = 0;
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(map[i][j] == '*' || visited[i][j] == tc || !check(i, j)) continue;
                    bfs(i, j); // 지뢰가 아니고, 방문도 안 했으면 bfs 시작
                    result++;
                    cnt--;
                }
            }
             
            sb.append("#").append(tc).append(" ").append(result+cnt).append("\n");
             
        } // 전체 테스트 케이스 종료
        System.out.println(sb);
    }
     
    private static void bfs(int sx, int sy) { // 연쇄 클릭 메서드
        int h = 0, t = 1; // head가 삭제, tail이 삽입
         
        qx[h] = sx; qy[h] = sy; // 초기값 삽입
        visited[sx][sy] = tc;
         
        while(h != t) { // head와 tail이 같아지면 큐가 비어있는 것
            int x = qx[h];
            int y = qy[h++];
             
            for(int i = 0; i < 8; i++) {
                int tx = x + dr[i];
                int ty = y + dc[i];
                // 범위 밖, 방문, 지뢰일 경우 패스
                if(tx < 0 || N <= tx || ty < 0 || N <= ty || visited[tx][ty] == tc || map[tx][ty] == '*') continue;
                if(check(tx, ty)) { // 다음칸의 주변 8방에 지뢰가 없을 때만 큐에 넣기
                    qx[t] = tx;
                    qy[t++] = ty;
                }
                visited[tx][ty] = tc; // 방문 처리는 무조건
                cnt--;
            }
        }
    }
     
    private static boolean check(int sx, int sy) { //주변에 지뢰가 없으면 true 리턴
        for(int i = 0; i < 8; i++) {
            int tx = sx + dr[i];
            int ty = sy + dc[i];
             
            if(tx < 0 || N <= tx || ty < 0 || N <= ty) continue;
            if(map[tx][ty] == '*') return false;
        }
         
        return true;
    }
}