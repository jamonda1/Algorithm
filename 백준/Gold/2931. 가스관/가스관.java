import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
/*
 * 블록이 딱 1개 부족하다. 그 블록이 무엇일까?
 * 단 하나의 블록만 추가해서 가스를 M에서 Z에 도달할 수 있도록 하자. 불필요한 블록은 존재하지 않는다.
 */
public class Main {

    static int R, C, blockCnt = 1, changeX, changeY; // change 부분을 출력.
    static char block;
    static boolean findZ = false, crossCheck = false;
    static char[][] map;
    static Set<Character>[] hs = new HashSet[4];
    static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
    
    static void hsSet() {
    	char[][] p = {{'|', '+', '1', '4'}, {'-', '+', '3', '4'}, {'|', '+', '2', '3'}, {'-', '+', '1', '2'}};
        for(int i = 0; i < 4; i++) {
            hs[i] = new HashSet<>();
            for(int j = 0; j < 4; j++) hs[i].add(p[i][j]);
        } // 각 방향별 진입 가능한 파이프 삽입
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken()); // 맵의 세로 R
        C = Integer.parseInt(st.nextToken()); // 맵의 가로 C

        map = new char[R][C];

        int x = 0, y = 0;
        for(int i = 0; i < R; i++) {
            String input = br.readLine();
            for(int j = 0; j < C; j++) {
                map[i][j] = input.charAt(j);
                if(map[i][j] == 'M') {
                    x = i; y = j;
                } else if(map[i][j] != '.' && map[i][j] != 'Z') blockCnt++;
            }
        } // 맵 입력 및 출발 위치 확인

        hsSet(); // 방향별로 진입할 수 있는 파이프 정리하기
        
        for(int i = 0; i < 4; i++) {
        	int tx = x + dr[i];
        	int ty = y + dc[i];
        	if(tx < 0 || R <= tx || ty < 0 || C <= ty || map[tx][ty] == '.') continue;
        	
        	int dir = changeDir(map[tx][ty], i); // 다음 블럭과 지금 보고 있는 방향을 넘겨주기
        	if(!findZ) goGas(tx, ty, dir, false, 1);
        }
    }
    
    private static void goGas(int x, int y, int d, boolean b, int cnt) {
    	int tx = x + dr[d];
        int ty = y + dc[d];
        if(tx < 0 || R <= tx || ty < 0 || C <= ty) return;
        
        char pivot = map[tx][ty]; // 내 위치에서 d 방향의 1칸 앞을 확인
        
        if(!findZ && hs[d].contains(pivot)) { // Z를 찾지 못했고, 앞이 갈 수 있는 파이프라면?
        	int dir = changeDir(map[tx][ty], d);
        	if(block == '+' && tx == changeX && ty == changeY) crossCheck = true;
        	goGas(tx, ty, dir, b, cnt + 1);
        	crossCheck = false;
        	
        } else if(!b && pivot == '.') { // 다음 칸에 블럭이 없으면 깔자
        	for(char c : hs[d]) {
        		if(findZ) continue;

        		int dir = changeDir(c, d);
        		changeX = tx; changeY = ty; // 블럭을 설치한 위치
        		block = c; // 설치한 블럭의 종류
        		map[tx][ty] = c;
        		crossCheck = false;
        		goGas(tx, ty, dir, true, cnt + 1);
        	}
        } else if(pivot == 'Z' && cnt >= blockCnt) { // Z를 찾았으면 출력 후 리턴!!
        	// 그런데 +를 설치했으면 한 번 더 지나야 한다. +가 아니라 다른 걸 설치했으면 통과
        	if((block == '+' && crossCheck) || block != '+') {
        		System.out.println((changeX + 1) + " " + (changeY + 1) + " " + block);
                findZ = true;
                return;
        	}
        }
	}

	private static int changeDir(char pipe, int d) {
    	// 1 ~ 4 외의 블럭은 그대로 직진하면 된다.
    	switch(pipe) {
        case '1' : // 0 또는 3으로 진입할 수 있는데, 0으로 가면 1로 나오고, 3으로 가면 2로 나온다.
            d = (d == 0) ? 1 : 2;
            break;
        case '2' : // 2로 가면 1, 3으로 가면 0
            d = (d == 2) ? 1 : 0;
            break;
        case '3' : // 2로 가면 3, 1로 가면 0
            d = (d == 2) ? 3 : 0;
            break;
        case '4' : // 1로 가면 2, 0으로 가면 3
            d = (d == 1) ? 2 : 3;
            break;
        }
    	return d;
    }
}