import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 감독은 각 이닝마다 타자가 어떤 결과를 얻을지 알고 있다.
 * 그렇기 때문에 타자의 순서를 정해서 가장 많이 득점할 수 있도록 해보자
 * 
 * 1번 선수는 감독이 좋아하는 선수라서 4번 타자 고정이다.
 * 아웃이 3번 누적되면 이닝이 종료된다.
 */
public class Main {
    
    static int N, result = 0;
    static int[] player = new int[9];
    static int[][] baseball;
    static boolean[] visited; // 각각 베이스에 사람이 있는지 확인
    static Queue<Integer> queue = new LinkedList<>();
     
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken()); // 이닝의 수 N
        visited = new boolean[9];
        
        baseball = new int[N][9];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 9; j++) {
                baseball[i][j] = Integer.parseInt(st.nextToken());
            }
        } // 각 이닝에서 얻는 결과 입력 완료

        visited[0] = true;
        permutation(0);
        
        System.out.println(result);
    }
    
    private static void permutation(int depth) { // 순열 생성 후 게임 시작
    	if(depth == 9) {
    		letsPlay();
    		return;
    	}
    	
    	if(depth == 3) { // 3번 인덱스는 0번 타자 고정이므로 패스
    		permutation(depth + 1);
    		return;
    	}
        
        for(int i = 1; i < 9; i++) { // 순열 생성
            if(!visited[i]) {
                visited[i] = true;
                player[depth] = i;
                permutation(depth + 1);
                visited[i] = false;
            }
        }
    }
    
    public static void letsPlay() {
    	int totalScore = 0;
    	
    	int idx = 0;
    	for(int i = 0; i < N; i++) { // 이닝 수만큼 게임 시작
//    		queue.clear();
//    		for(int j = 0; j < 3; j++) queue.add(0); // 큐를 활용해서 진루를 해보자
    		boolean[] base = new boolean[4];
    		int score = 0, outCnt = 0;

    		for(;;) {
    			int temp = baseball[i][player[idx]]; // 각 선수의 이닝별 결과
    			
    			switch(temp) {
    			case 0 : // 아웃
    				outCnt++;
    				break;
    			case 1 : // 1루타
    				if(base[3]) score++;
    				base[3] = base[2];
    				base[2] = base[1];
    				base[1] = true;
    				break;
    			case 2 : // 2루타
    				if(base[3]) score++;
    				if(base[2]) score++;
    				base[3] = base[1];
    				base[2] = true;
    				base[1] = false;
    				break;
    			case 3 : // 3루타
    				if(base[3]) score++;
    				if(base[2]) score++;
    				if(base[1]) score++;
    				base[3] = true;
    				base[2] = false;
    				base[1] = false;
    				break;
    			case 4 : // 4루타
    				if(base[3]) score++;
    				if(base[2]) score++;
    				if(base[1]) score++;
    				score++;
    				base[3] = false;
    				base[2] = false;
    				base[1] = false;
    				break;
    			}
    			
//    			if(temp != 0) {
//    				for(int j = 0; j < temp; j++) {
//    					if(j == 0) queue.add(1); // 제일 처음에 1 넣기
//    					if(queue.poll() == 1) score++; // 제거해서 1인가
//    					if(j != 0) queue.add(0); // 첫 번째 이후로는 0 넣기
//    				}
//    			} else outCnt++; // 0이면 아웃이니깐 cnt 증가
    			
    			idx = (idx + 1 == 9 ? 0 : idx + 1); // 다음 타자
    			if(outCnt == 3) {
    				break;
    			}
    		}
    		totalScore += score;
    	}
    	
    	result = Math.max(result, totalScore);
    }
}