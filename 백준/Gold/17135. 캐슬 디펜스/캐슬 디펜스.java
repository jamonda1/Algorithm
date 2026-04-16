import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
/*
 * 게임이 진행되는 곳은 크기가 N * M인 격자판. 각 칸에 포함된 적의 수는 최대 하나다.
 * 격자판의 N번 행의 바로 아래(N+1번 행)의 모든 칸에는 성이 있다.
 * 성을 적에게서 지키기 위해 궁수 3명을 배치하고자 한다. 궁수는 성이 있는 칸에 배치 가능, 하나의 칸에는 최대 1명의 궁수
 *
 * 각 턴마다 궁수는 적 하나를 공격할 수 있고, 모든 궁수는 동시에 공격한다.
 * 공격 대상은 거리 D 이하의 가장 가까운 적이다. 여럿일 경우에는 가장 왼쪽의 적을 공격한다. 같은 적 공격 가능
 * 공격 받은 적은 게임에서 제외.
 *
 * 궁수 공격이 끝나면, 적은 아래로 한 칸 이동한다. 성이 있는 칸으로 이동한 경우에는 게임에서 제외. 모두 제외되면 게임 끝
 *
 * 격자판의 상태가 주어졌을 때 궁수의 공격으로 제거할 수 있는 적의 수는?
 */
public class Main {
	
	static int N, M, D, result = 0;
	static char[][] map, copyMap;
	static int[] forCombi;
	static boolean[] visited;
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 격자판의 세로 N
        M = Integer.parseInt(st.nextToken()); // 격자판의 가로 M
        D = Integer.parseInt(st.nextToken()); // 궁수의 사정거리 D
        
        map = new char[N][M];
        forCombi = new int[M]; // 조합을 만들기 위해 선언한 배열
        visited = new boolean[M]; // 조합을 만들기 위한 배열

        for(int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
            	map[i][j] = st.nextToken().charAt(0);
            	forCombi[j] = j;
            }
        } // 맵 입력 종료
        combination(0, 0, new int[3]);
        
        System.out.println(result);
    }
    
    static void combination(int depth, int start, int[] combi) { // 궁수 위치의 조합을 만들고, 해당 조합에서 원소를 하나씩 꺼내서 적들을 잡아보자
    	if(depth == 3) { // 조합 완성. 이제 적들을 죽이는 최댓값을 찾아보자
    		kill(combi);
    		return;
    	}
    	for(int i = start; i < M; i++) {
    		if(!visited[i]) {
    			visited[i] = true;
    			combi[depth] = forCombi[i];
    			combination(depth + 1, i + 1, combi);
    			visited[i] = false;
    		}
    	}
    }
    
    static void kill(int[] archer) { // 한 턴마다 각각 궁수가 적들을 노려야 한다.
    	List<int[]> target = new ArrayList<>();
    	
    	copyMap = new char[N][M]; // 원본 배열 보존
    	for(int i = 0; i < N; i++) {
    		copyMap[i] = map[i].clone();
    	}
    	
    	int n = N; // 원본 N 보존
    	int killCnt = 0;
    	while(n > 0) { // n이 0이 되면 종료
    		int[][] archers = {{n, archer[0]}, {n, archer[1]}, {n, archer[2]}};
    		
        	for(int i = 0; i < 3; i++) {
        		int[] temp = find(archers[i], n);
        		if(temp != null) target.add(temp); // 리스트에 최적의 타겟 저장
        	}
        	
    		for(int[] curr : target) {
        		if(copyMap[curr[0]][curr[1]] == '1') {
        			copyMap[curr[0]][curr[1]] = '0';
        			killCnt++;
        		}
        	}
        	target.clear();
        	n--; // 반복문이 하나 종료되면 적들이 내려오는 것이 아닌 궁수들이 다가간다.
    	}
    	result = Math.max(result, killCnt);
    }
    
    static int[] find(int[] archers, int n) { // 여기서 가장 최적의 적을 찾아서 리스트로 전달하자
    	int[] target = new int[2];
    	boolean f = false; // 적을 찾았는가?
    	
    	int length = (n - D >= 0 ? n - D : 0); // n-D가 음수면 0으로 보정
    	int min = 11; // 입력 가능한 최대 사정거리는 10이므로 초기값은 그것보다 크면 된다.
    	for(int i = length; i < n; i++) { // 0부터 끝까지 할 필요 없다
    		for(int j = 0; j < M; j++) {
    			if(copyMap[i][j] == '0') continue; // 적이 없는 부분은 바로 패스
    			
    			int howFar = Math.abs(i - archers[0]) + Math.abs(j - archers[1]); // 거리 측정
    			if(howFar <= D) { // 사정거리 안쪽이면 공격 가능하다.
    				f = true;
    				if(howFar < min) { // 가까운 곳에 있는 적을 노리자
    					min = howFar;
    					target[0] = i;
    					target[1] = j;
    				} else if (howFar == min && j < target[1]) {
    					// 만약 또 가까운 적이 탐지되었는데, 더 왼쪽에 있다면?
    					target[0] = i;
    					target[1] = j;
    				}
    			}
    		}
    	}
    	return (f ? target : null); // 만약 찾은 적이 없으면 null 반환
    }
}