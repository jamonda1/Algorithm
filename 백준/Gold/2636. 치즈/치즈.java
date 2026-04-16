import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 공기와 맞닿은 치즈는 1시간 뒤에 썩어서 없어진다.
 * 중간에 구멍에는 공기가 없기 때문에 썩지 않는다.
 * 구멍이 뚫리면 공기가 들어간다.
 *
 * 모든 치즈가 없어지기까지 걸리는 시간과
 * 녹기 1시간 전에는 몇 개의 치즈가 남아있는지 구하여라!!
 */
public class Main {

    static int N, M, result = 0;
    static char[][] cheese;
    static int[] r = {-1, 1, 0, 0};
    static int[] c = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 치즈판의 세로 N
        M = Integer.parseInt(st.nextToken()); // 치즈판의 가로 M
        cheese = new char[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                cheese[i][j] = st.nextToken().charAt(0);
            }
        } // 치즈 정보 입력 완료

        int time = 0;
        for(;;) {
            boolean[][] visited = new boolean[N][M];

            int count = bfs(visited); // result[0] = 전체 bfs의 횟수, result[1] = 녹기 직전 치즈의 개수

            if(count == N * M) {
                System.out.println(time + "\n" + result);
                System.exit(0);
            }
            time++; // 치즈 탐색 후 시간 증가
        }
    }

    static int bfs(boolean[][] visited) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {0, 0}); // 배열이 매번 초기화되니깐 항상 0,0부터
        visited[0][0] = true;

        int count = 0, last = 0;
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            count++;

            for(int i = 0; i < 4; i++) {
                int tx = cur[0] + r[i];
                int ty = cur[1] + c[i];

                if(tx < 0 || N <= tx || ty < 0 || M <= ty) continue;
                if(visited[tx][ty]) continue; // 범위를 벗어나거나 방문했으면 패스

                if(cheese[tx][ty] == '1') { // 공기를 순회하다 1을 만나면?
                    cheese[tx][ty] = '0';
                    visited[tx][ty] = true;
                    last++; // 치즈를 녹였으니 카운팅
                    continue;
                }

                visited[tx][ty] = true;
                queue.add(new int[] {tx, ty});
            }
        }
        if(last != 0) result = last;
        return count;
    }
}