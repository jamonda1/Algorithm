import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
 * 수빈이는 동생과 숨바꼭질을 하고 있다. 수빈이는 1초마다 X-1 또는 X+1로 움직인다. 그리고 X*2로 순간이동도 가능하다.
 * 동생은 항상 걷기만 한다. 그런데 가속을 한다.
 * 처음에는 K, K+1, K+1+2, K+1+2+3....
 * 수빈이가 동생을 찾는 가장 빠른 시간은?
 */
public class Main {
    
	static class Node implements Comparable<Node> {
        int x, t;
        public Node(int x, int t) {
            this.x = x;
            this.t = t;
        }
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.t, o.t);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        if(N == K) { // 둘의 위치가 같으면 바로 0 출력
            System.out.println(0); return;
        }
        
        int size = 500001; // 최대 배열 길이
        int[] sister = new int[size];
        Arrays.fill(sister, -1);
        
        int sum = 0;
        int time = 0;
        for(int i = K; i < size; i = K + sum) { // 동생이 갈 수 있는 모든 곳에 도착 시간 저장
            sister[i] = time;
            sum += ++time;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        int[] d = {-1, 1};
        int[][] dist = new int[2][size];
        for(int i = 0; i < 2; i++) {
        	Arrays.fill(dist[i], Integer.MAX_VALUE);        	
        }
        
        pq.add(new Node(N, 0)); // 초기값 설정
        dist[0][N] = 0;
        
        while(!pq.isEmpty()) {
            Node curr = pq.poll();
            int x = curr.x;
            int t = curr.t;
            
            if(t > dist[t % 2 == 0 ? 0 : 1][x]) continue;
            
            for(int i = 0; i < 3; i++) {
                int tx = (i < 2) ? x + d[i] : x * 2;
                int tt = t + 1;
                int tf = tt % 2 == 0 ? 0 : 1;
                
                if(tx < 0 || size <= tx) continue;
                if(tt >= dist[tf][tx]) continue;
                
                dist[tf][tx] = tt;
                pq.add(new Node(tx, tt));
            }
        } // 각각의 위치로 최단거리 이동 완료
        
        int result = size * 2;
        for(int i = K; i < size; i++) {
        	for(int j = 0; j < 2; j++) {
        		if(sister[i] != -1 && sister[i] >= dist[j][i] && checkTime(sister[i] - dist[j][i])) {
                    result = Math.min(result, sister[i]);
                }
        	}
        }
        
        System.out.println(result == (size * 2) ? -1 : result);
    }
    private static boolean checkTime(int pivot) {
        if(pivot % 2 == 0) return true;
        return false;
    }
}