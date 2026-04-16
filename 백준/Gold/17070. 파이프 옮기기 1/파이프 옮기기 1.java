import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // 새 집의 크기는 N * N
    // 행과 열의 번호는 1부터 시작한다.
    // 파이프가 벽을 넘어가면 안 된다.
    // 파이프는 밀어서 이동할 수 있다. 바라보는 방향으로만 갈 수 있다.
    // 회전은 45도로만 가능하다. 우측을 볼 때 한 번에 하단을 볼 수는 없다.

    // 가장 처음에 파이프는 (1, 1) (2, 2)의 위치에 있다. 이걸 (N, N)으로 밀 수 있는 방법의 개수를 찾아보자
    // 전진할 경우에는 꼬리와 머리에 모두 같은 값을 더해주고, 회전의 경우에는 머리만 값을 더해준다.
    // 어떻게든 도착해야 하는 것이 아닌, 도착할 수 있는 방법이 몇 개가 있는지 물어보는 것이다.
    // 그렇기 때문에 방문 체크는 불필요하다.

    static int N, count = 0;
    static int[][] map;
    static int[][] move = {{0, 1}, {1, 1}, {1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine()); // 맵의 크기 입력
        map = new int[N+1][N+1]; // 파이프는 (1, 1)(1, 2)부터 시작하니깐 계산하기 편함

        for(int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        } // ---------- 변수 선언 및 입력 끝 ----------

        if(map[1][3] != 1) { // 바로 앞에 벽이 있으니깐 도달할 수 있는 가능성은 0
            movePipe(1, 1, 1, 2, 0); // 파이프는 (1, 1)(1, 2)부터 시작
        }
        System.out.println(count);
    }

    static void movePipe(int tail_x, int tail_y, int head_x, int head_y, int dir) { // direction이 0이면 가로, 1면 대각, 2이면 세로를 보고 있는 것
        if(head_x == N && head_y == N) {
            count++; // 파이프의 머리가 (N, N)에 도달했으면
            return;
        }
        
        if(dir != 1) { // 지금 방향이 가로 또는 세로인가
        	// 직진
        	int X = head_x + move[dir][0]; // 직진할 곳의 X 좌표
        	int Y = head_y + move[dir][1]; // 직진할 곳의 Y 좌표
        	if(X < N+1 && Y < N+1 && map[X][Y] == 0) { // 범위 안에 있고 벽이 없으면
        		movePipe(head_x, head_y, X, Y, dir);   // 직진
        	}
        	// 회전
        	boolean f = true; // 이게 계속 트루면 벽이 없다는 뜻
        	for(int i = 0; i < 3; i++) { // 3방 탐색으로 벽 탐색
        		X = head_x + move[i][0];
        		Y = head_y + move[i][1];
        		if(X < N+1 && Y < N+1 && map[X][Y] == 0) {
        			continue;
        		} else f = false;
        	}
        	if(f) movePipe(head_x, head_y, head_x + 1, head_y + 1, 1); // 회전
        	
        } else { // 지금 방향이 대각인가
        	// 직진
        	boolean f = true; // 이게 계속 트루면 벽이 없다는 뜻
        	for(int i = 0; i < 3; i++) { // 3방 탐색으로 벽 탐색
        		int X = head_x + move[i][0];
        		int Y = head_y + move[i][1];
        		if(X < N+1 && Y < N+1 && map[X][Y] == 0) {
        			continue;
        		} else f = false;
        	} // 대각선일 때는 주변에 벽이 없어야 직진 가능
        	if(f) movePipe(head_x, head_y, head_x + 1, head_y + 1, dir); // 직진
        	
        	// 회전
        	if(head_y + 1 < N+1 && map[head_x][head_y + 1] == 0) {
        		movePipe(tail_x, tail_y, head_x, head_y + 1, 0);
        	}
        	if(head_x + 1 < N+1 && map[head_x + 1][head_y] == 0) {
        		movePipe(tail_x, tail_y, head_x + 1, head_y, 2);
        	}
        }
    }
}