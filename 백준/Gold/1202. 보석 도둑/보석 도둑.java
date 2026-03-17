import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
 * 상덕이가 털 보석점에는 보석이 총 N개 있다. 각 보석은 무게M과 가격V를 가지고 있다. 상덕이는 가방을 K개 가지고 있다.
 * 각 가방에 담을 수 있는 최대 무게는 C이다. 가방에는 한 개의 보석만 넣을 수 있다.
 * 상덕이가 훔칠 수 있는 보석의 최대 가격은?
 */
public class Main {
    
    static class Jewel implements Comparable<Jewel> {
    	int w, p;
        public Jewel(int w, int p) {
            this.w = w;
            this.p = p;
        }
        @Override
        public int compareTo(Jewel j) {
            return Integer.compare(this.w, j.w);
        } // 일단 보석 무게 순으로 오름차순
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken()); // 보석의 개수 N
        int K = Integer.parseInt(st.nextToken()); // 가방의 개수 K
        
        Jewel[] jew = new Jewel[N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken()); // 보석의 무게 저장
            int p = Integer.parseInt(st.nextToken()); // 보석의 값 저장
            
            jew[i] = new Jewel(w, p);
        }
        Arrays.sort(jew); // 보석 무게 순으로 오름차순
        
        int[] bag = new int[K];
        for(int i = 0; i < K; i++) {
            bag[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(bag); // 가방 무게 오름차순 정렬
        
        // 가격만 담아서 비싼 순으로 저장
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        long result = 0;
        int idx = 0;
        for(int i = 0; i < K; i++) {
        	for(int j = idx; j < N; j++) {
        		if(jew[j].w <= bag[i]) { // 가방에 넣을 수 있는 거라면?
        			pq.add(jew[j].p);
        			idx++;
        		} else break;
        	}
        	
        	if(!pq.isEmpty()) { // 큐에 있는 것 중에서 가장 비싼 보석만 뽑기
        		result += pq.poll();
        	}
        }
        
        System.out.println(result);
    }
}