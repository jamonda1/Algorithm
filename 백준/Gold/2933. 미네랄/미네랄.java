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
 * 동굴에 미네랄이 있다. 왼쪽과 오른쪽에서 번갈아가며 막대기를 던진다. 이때 가장 먼저 닿는 미네랄은 파괴된다.
 * 파괴되고 4방으로 이어진 것이 없어진 미네랄 덩어리는 그대로 모양을 유지하면서 아래로 떨어진다.
 */
public class Main {
    
    static class Adrs {
        int x, y;
        public Adrs(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int R, C, count;
    static char[][] map;
    static Queue<Adrs> queue = new LinkedList<>();
    static List<Adrs> list = new ArrayList<>();
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
// ---------- main start ---------- //
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        R = Integer.parseInt(st.nextToken()); // 맵의 세로 R
        C = Integer.parseInt(st.nextToken()); // 맵의 가로 C
        
        map = new char[R][C];
        count = 0;
        for(int i = 0; i < R; i++) {
            String input = br.readLine();
            for(int j = 0; j < C; j++) {
                map[i][j] = input.charAt(j);
                if(map[i][j] == 'x') count++;
            }
        } // 맵 정보 입력 완료
        
        int N = Integer.parseInt(br.readLine()); // 막대를 던진 횟수
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            int h = Integer.parseInt(st.nextToken()); // 막대를 던진 높이
            
            move(R - h, i % 2 == 1 ? true : false); // 홀수일 때는 왼쪽, 짝수일 때는 오른쪽에서 던진다.
        }
        
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                bw.write(map[i][j] + "");
            } bw.write("\n");
        } bw.close(); // 모든 명령 수행 후 맵 출력
    }
// ---------- main end ---------- //
    private static void move(int h, boolean f) {
        for(int i = 0; i < C; i++) {
            int n = f ? i : C - 1 - i;
            
            if(map[h][n] == 'x') { // 미네랄 파괴 후에 떨어트리기
                map[h][n] = '.'; count--;
                check(h, n); return;
            }
        }
    }

    private static void check(int x, int y) {
        for(int i = 0; i < 4; i++) { // 파괴한 미네랄 주변 탐색해서 같은 클러스터에 미네랄이 있는지 확인
            int tx = x + dr[i];
            int ty = y + dc[i];
            if(tx < 0 || R <= tx || ty < 0 || C <= ty) continue;
            if(map[tx][ty] == 'x' && bfs(tx, ty)) break;
        }
    }
    
    private static boolean bfs(int sx, int sy) { // 공중에 뜬 클러스터 찾기
        queue.clear();
        boolean[][] visited = new boolean[R][C];
        visited[sx][sy] = true;
        queue.add(new Adrs(sx, sy));
        
        while(!queue.isEmpty()) {
            Adrs curr = queue.poll();
            int x = curr.x;
            int y = curr.y;
            
            if(x == R-1) return false; // x좌표가 땅에 닿는 게 있으면 drop x
            
            for(int i = 0; i < 4; i++) {
                int tx = x + dr[i];
                int ty = y + dc[i];
                if(tx < 0 || R <= tx || ty < 0 || C <= ty) continue;
                if(visited[tx][ty] || map[tx][ty] == '.') continue;
                
                visited[tx][ty] = true;
                queue.add(new Adrs(tx, ty));
            }
        }
        
        drop(visited); // 여기까지 오면 공중에 뜬 클러스터이므로 drop 수행
        return true;
    }
    
    private static void drop(boolean[][] visited) { // 클러스터 내리기
        list.clear();
        for(int i = 0; i < R; i++) { // 리스트에 모든 주소 넣고, '.'으로 바꿔주기
            for(int j = 0; j < C; j++) {
                if(visited[i][j]) {
                    list.add(new Adrs(i, j));
                    map[i][j] = '.';
                }
            }
        }
        
        first : while(true) {
            for(Adrs c : list) { // 다음 위치에 미네랄이 있으면 탈출
                if(c.x + 1 == R || map[c.x + 1][c.y] == 'x') break first;
            }
            // 없으면 한 칸씩 내리기
            for(Adrs c : list) c.x += 1;
        }
        
        for(Adrs c : list) map[c.x][c.y] = 'x'; 
    }
}