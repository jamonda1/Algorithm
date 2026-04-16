import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/*
 * 수빈이는 걷거나 순간이동을 한다.
 * 걸으면 +1 또는 -1로 이동하고 1초 소비한다.
 * 순간이동은 지금 위치에서 *2를 하고 0초를 소비한다.
 * 
 * 동생을 만나는 최소 시간을 출력해라!!
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt(); // 수빈이의 위치 N
        int K = sc.nextInt(); // 동생의 위치 K
        
        int[] dist = new int[100001];
        Arrays.fill(dist, Integer.MAX_VALUE); // 초기 거리 최대로 초기화
        int[] dir = {1, -1};
        
        if(N > K) { // 동생이 수빈이보다 뒤에 있으면 바로 종료
            System.out.println(N - K);
            System.exit(0);
        }
        
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {N, 0});
        
        while(!queue.isEmpty()) {
        	int[] curr = queue.poll();

        	// 유통기한 지난 것
        	if(curr[1] > dist[curr[0]]) continue;
        	
        	if(curr[0] == K) { // 지금 위치가 동생의 위치인가?
        		System.out.println(curr[1]);
                System.exit(0);
        	}
        	
        	for(int i = 0; i < 3; i++) { // 0, 1은 걷기  2는 순간이동
        		int x = (i < 2) ? (curr[0] + dir[i]) : (curr[0] * 2);
        		int time = (i < 2) ? (curr[1] + 1) : curr[1];
        		
        		if(x < 0 || 100000 < x) continue; // 전체 범위를 벗어나면 패스
        		if(time >= dist[x]) continue;
        		
        		dist[x] = time;
        		queue.add(new int[] {x, time});
        	}
        } // 탐색 종료
        
        sc.close();
    }
}