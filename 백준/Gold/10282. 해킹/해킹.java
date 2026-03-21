import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Com implements Comparable<Com> {
		int x, w;
		public Com(int x, int w) {
			this.x = x;
			this.w = w;
		}
		@Override
		public int compareTo(Com o) {
			return Integer.compare(this.w, o.w);
		}
	}
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++) {
        	
        	st = new StringTokenizer(br.readLine());
        	int N = Integer.parseInt(st.nextToken()); // 컴퓨터의 개수 N
        	int D = Integer.parseInt(st.nextToken()); // 의존성의 개수 D
        	int C = Integer.parseInt(st.nextToken()); // 해킹한 컴퓨터 C
        	
        	List<Com>[] graph = new ArrayList[N + 1];
        	for(int i = 1; i <= N; i++) {
        		graph[i] = new ArrayList<>();
        	}
        	
        	for(int i = 0; i < D; i++) {
        		st = new StringTokenizer(br.readLine());
        		int a = Integer.parseInt(st.nextToken());
        		int b = Integer.parseInt(st.nextToken());
        		int w = Integer.parseInt(st.nextToken());
        		graph[b].add(new Com(a, w));
        	}
        	
        	PriorityQueue<Com> pq = new PriorityQueue<>();
        	int[] dist = new int[N + 1];
        	Arrays.fill(dist, Integer.MAX_VALUE);
        	
        	pq.add(new Com(C, 0));
        	dist[C] = 0;
        	
        	int count = 0;
        	int time = 0;
        	while(!pq.isEmpty()) {
        		Com curr = pq.poll();
        		int x = curr.x;
        		int w = curr.w;
        		
        		if(w > dist[x]) continue;
        		time = w;
        		count++;
        		
        		for(Com next : graph[x]) {
        			int tx = next.x;
        			int tc = w + next.w;
        			
        			if(tc >= dist[tx]) continue;
        			dist[tx] = tc;
        			pq.add(new Com(tx, tc));
        		}
        	}
        	
        	bw.write(count + " " + time + "\n");
        }
        bw.close();
    }
}