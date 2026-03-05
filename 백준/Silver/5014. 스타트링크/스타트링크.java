import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 건물의 높이는 1층~F층. 지금 있는 곳은 S층이다.
 * 엘리베이터를 타고 G층으로 가야 한다.
 * 
 * 버튼을 눌려야 하는 최소값을 구해보자.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int F = Integer.parseInt(st.nextToken()); // 건물의 최대 높이 F
        int S = Integer.parseInt(st.nextToken()); // 강호가 있는 층 S
        int G = Integer.parseInt(st.nextToken()); // 목표 G
        int U = Integer.parseInt(st.nextToken()); // 한 번에 올라가는 층 U
        int D = Integer.parseInt(st.nextToken()); // 한 번에 내려가는 층 D
        
        boolean[] visited = new boolean[F + 1];
        boolean f = false;
        
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {S, 0});
        visited[S] = true;
        
        int time = 0;
        while(!queue.isEmpty()) {
        	int[] curr = queue.poll();
        	
        	if(curr[0] == G) {
        		f = true;
        		time = curr[1];
        		break;
        	}
        	
        	for(int i = 0; i < 2; i++) { // 0업 1다운
        		int x = (i == 0) ? curr[0] + U : curr[0] - D; 
        		
        		if(x < 1 || F < x || visited[x]) continue;
        		
        		visited[x] = true;
        		queue.add(new int[] {x, curr[1] + 1});
        	}
        }
        
        System.out.println((f ? time : "use the stairs"));
    }
}