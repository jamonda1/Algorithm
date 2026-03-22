import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * N개의 물건이 있고, 가방은 총 무게 K까지 버틸 수 있다.
 * 각 물건의 무게 W와 물건의 가치 V가 주어진다.
 * 이때 K를 넘지 않으면서 최대한 높은 가치를 담을 수 있도록 해보자.
 */
public class Main {
	
	static class Item{
		int w, v;
		public Item(int w, int v) {
			this.w = w;
			this.v = v;
		}
	}
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken()); // 물건의 개수 N
        int K = Integer.parseInt(st.nextToken()); // 가방 최대 무게 K
        
        Item[] item = new Item[N + 1];

        for(int i = 1; i <= N; i++) {
        	st = new StringTokenizer(br.readLine());
        	int w = Integer.parseInt(st.nextToken()); // 무게
        	int v = Integer.parseInt(st.nextToken()); // 가치
        	item[i] = new Item(w, v);
        } // 물건 정보 입력 완료
        
        int[][] bag = new int[N+1][K+1];
        
        for(int i = 1; i <= N; i++) {
        	for(int j = 1; j <= K; j++) {
        		if(item[i].w > j) { // 해당 무게에 넣을 수 없는 경우
        			bag[i][j] = bag[i-1][j]; // 한 칸 위의 값을 가져온다.
        			
        		} else { // 해당 무게에 물건을 넣을 수 있는 경우
        			// 물건을 넣지 않은 값과 넣은 값 중에 최대값을 저장
        			bag[i][j] = Math.max(bag[i-1][j], bag[i-1][j - item[i].w] + item[i].v);
        		}
        	}
        }
        // 1부터 K까지의 무게마다 항상 최대로 넣을 수 있는 값을 저장한다
        System.out.println(bag[N][K]);
    }
}