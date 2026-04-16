import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, result = Integer.MAX_VALUE, safe = 0;
    static char[][] lab;
    static boolean[] pick;
    static boolean[][] visited;
    static List<int[]> virus = new ArrayList<>(); // 바이러스 놓을 수 있는 곳
    static Queue<int[]> queue = new LinkedList<>();
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 연구실의 크기 N
        M = Integer.parseInt(st.nextToken()); // 놓을 수 있는 바이러스의 개수 M

        lab = new char[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                lab[i][j] = st.nextToken().charAt(0);
                if(lab[i][j] == '0') safe++; // 안전 영역의 수 카운팅
                if(lab[i][j] == '2') virus.add(new int[] {i, j});
            }
        } // 연구실 정보 입력 완료

        safe += virus.size() - M; // 전체 2 중에 M개 말고는 안전구역으로 카운트해야 한다.
        pick = new boolean[virus.size()];

        combination(0, 0, new int[M][2]);

        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }

    static void combination(int depth, int start, int[][] address) {
        if(depth == M) { // 조합 생성 완료
            visited = new boolean[N][N];
            infection(visited, address); // 메서드 호출
            return;
        }

        for(int i = start; i < virus.size(); i++) {
            if(!pick[i]) {
                pick[i] = true;
                address[depth][0] = virus.get(i)[0];
                address[depth][1] = virus.get(i)[1];
                combination(depth + 1, i + 1, address);
                pick[i] = false;
            }
        } // 조합 생성
    }

    static void infection(boolean[][] visited, int[][] address) {
        for(int i = 0; i < M; i++) {
            queue.add(new int[] {address[i][0], address[i][1], 0});
            visited[address[i][0]][address[i][1]] = true;
        } // 초기 위치 저장 및 방문처리 완료

        int time = -1;
        int count = 0;
        while(!queue.isEmpty()) {
            int[] curr = queue.poll();
            time = curr[2];

            for(int j = 0; j < 4; j++) {
                int tx = curr[0] + dr[j];
                int ty = curr[1] + dc[j];

                if(tx < 0 || N <= tx || ty < 0 || N <= ty) continue;
                if(visited[tx][ty] || lab[tx][ty] == '1') continue;

                visited[tx][ty] = true;
                count++;
                queue.add(new int[] {tx, ty, curr[2] + 1});
            }
        }
        // 확산이 안전구역 이상이고, 퍼진 값이 있으면 수행
        if(safe <= count && time != -1) {
            result = Math.min(result, time);
        }
    }
}