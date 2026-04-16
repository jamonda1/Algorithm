import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
/*
 * 0은 빈 칸, 1은 벽, 2는 바이러스
 * 바이러스는 4방으로 퍼진다.
 * 벽을 무조건 3개를 사용해서 가장 넓은 안전영역을 구해보자.
 */
public class Main {

    static int N, M, safeCnt = 0, result = 0;
    static char[][] lab;
    static boolean[] visited;
    static List<int[]> list= new ArrayList<>(), virus = new ArrayList<>();
    static Queue<int[]> queue = new LinkedList<>();
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 연구소의 세로
        M = Integer.parseInt(st.nextToken()); // 연구소의 가로

        lab = new char[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                lab[i][j] = st.nextToken().charAt(0);
                if(lab[i][j] == '0') {
                    safeCnt++;
                    list.add(new int[] {i, j});
                }
                if(lab[i][j] == '2') {
                    virus.add(new int[] {i, j});
                }
            }
        } // 연구소 상태 입력 완료
        safeCnt -= 3; // 벽을 3개 칠 예정이니깐 안전영역은 3개가 기본적으로 감소해야 한다.

        visited = new boolean[list.size()]; // 리스트의 길이 만큼만 방문 배열 생성

        combination(0, new int[3][2], 0);

        System.out.println(result);
    }

    static void combination(int depth, int[][] address, int start) {
        if(depth == 3) { // 조합이 완성됐으면, 벽 세우고 확인해보기
            char[][] labCopy = new char[N][M];
            for(int i = 0; i < N; i++) {
                labCopy[i] = lab[i].clone();
            } // 배열 복사

            for(int i = 0; i < 3; i++) {
                labCopy[address[i][0]][address[i][1]] = '1';
            } // 벽 세우기

            infection(labCopy); // 벽을 세운 연구실 값만 전달
            return;
        }

        for(int i = start; i < list.size(); i++) {
            if(!visited[i]) {
                visited[i] = true;
                address[depth][0] = list.get(i)[0];
                address[depth][1] = list.get(i)[1];
                combination(depth + 1, address, i + 1);
                visited[i] = false;
            }
        }
    }

    static void infection(char[][] labCopy) {
        boolean[][] check = new boolean[N][M];
        queue.clear(); // 잔여물 혹시 모르니 제거

        for(int[] i : virus) {
            queue.add(i);
            check[i[0]][i[1]] = true;
        } // 바이러스 초기 위치 추가

        int count = 0;
        while(!queue.isEmpty()) {
            int[] curr = queue.poll();

            for(int i = 0; i < 4; i++) {
                int tx = curr[0] + dr[i];
                int ty = curr[1] + dc[i];

                if(tx < 0 || N <= tx || ty < 0 || M <= ty) continue;
                if(check[tx][ty] || labCopy[tx][ty] == '1') continue;

                check[tx][ty] = true;
                count++; // 몇 번 확산되었는가
                queue.add(new int[] {tx, ty});
            }
        }
        // 전체 안전영역 수에서 확산된 횟수 빼주기
        result = Math.max(result, safeCnt - count);
    }
}