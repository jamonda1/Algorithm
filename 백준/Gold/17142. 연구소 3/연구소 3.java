import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, safe = 0, result = Integer.MAX_VALUE;
    static char[][] lab;
    static boolean[] pick;
    static boolean[][] visited;
    static List<int[]> virus = new ArrayList<>();
    static Queue<int[]> queue = new LinkedList<>();
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 연구실의 크기 N
        M = Integer.parseInt(st.nextToken()); // 활성화 시킬 지뢰의 수 M

        lab = new char[N][N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                lab[i][j] = st.nextToken().charAt(0);
                if(lab[i][j] == '0') safe++; // 초기에 안전 영역이 몇 개인가?
                if(lab[i][j] == '2') virus.add(new int[] {i, j});
            }
        } // 연구실 정보 입력 완료

        if(safe == 0) { // 처음부터 안전영역이 없으면 바로 종료
            System.out.println(0);
            System.exit(0);
        }

        pick = new boolean[virus.size()]; // 조합을 위한 방문 배열

        combination(0, 0, new int[M][2]); // 조합 메서드 호출

        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }

    static void combination(int depth, int start, int[][] arr) {
        if(depth == M) { // 조합 생성 완료
            visited = new boolean[N][N];
            infection(visited, arr);
            return;
        }

        for(int i = start; i < virus.size(); i++) {
            if(!pick[i]) {
                pick[i] = true;
                arr[depth] = virus.get(i);
                combination(depth + 1, i + 1, arr);
                pick[i] = false;
            }
        } // 조합 생성
    }

    static void infection(boolean[][] visited, int[][] arr) {
    	queue.clear();
        for(int i = 0; i < M; i++) {
            queue.add(new int[] {arr[i][0], arr[i][1], 0});
            visited[arr[i][0]][arr[i][1]] = true;
        } // 초기 활성화 시킬 바이러스의 위치 추가

        int time = -1, count = 0;
        while(!queue.isEmpty()) {
            int[] curr = queue.poll();

            if(count >= safe) {
            	time = curr[2];
            	continue;
            }

            for(int i = 0; i < 4; i++) {
                int tx = curr[0] + dr[i];
                int ty = curr[1] + dc[i];

                if(tx < 0 || N <= tx || ty < 0 || N <= ty) continue;
                if(visited[tx][ty] || lab[tx][ty] == '1') continue;
                if(lab[tx][ty] == '0') count++; // 안전구역 없어진 만큼 카운팅

                visited[tx][ty] = true;
                queue.add(new int[] {tx, ty, curr[2] + 1});
            }
        }
        
        if(time != -1) {
            result = Math.min(result, time);
        }
    }
}