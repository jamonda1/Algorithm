import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * 장난감을 만드는데는 [기본 부품]과 그걸로 만드는 [중간 부품]이 있다. 중간 부품으로도 중간 부품을 만들 수 있다.
 * 중간 부품이나 완제품 X를 만들기 위해 부품 Y가 K개 필요하다.
 * 서로가 서로를 필요로 하는 경우가 없다.
 */
public class Main {
	
	static class Toy {
		int x, c;
		public Toy(int x, int c) {
			this.x = x;
			this.c = c;
		}
	}
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine()); // 1 ~ N-1까지 기본 또는 중간 부품, N은 완제품
        int M = Integer.parseInt(br.readLine()); // 부품들 관의 관계 수 M
        
        List<Toy>[] graph = new ArrayList[N + 1];
        int[] degree = new int[N + 1];
        int[] basic = new int[N + 1];
        for(int i = 1; i <= N; i++) {
        	graph[i] = new ArrayList<>();
        } // 그래프 초기화
        
        for(int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken()); // a를 만들기 위해
        	int b = Integer.parseInt(st.nextToken()); // b가
        	int c = Integer.parseInt(st.nextToken()); // c개 필요하다
        	
        	graph[a].add(new Toy(b, c));
        	degree[b]++;// 진입차수
        	basic[a]++; // 기본 부품 체크
        }
        
        boolean[] org = new boolean[N + 1]; // 기본 부품인 것만 체크
        int[] used = new int[N + 1]; // 사용한 부품 수 저장
        
        for(int i = 1; i <= N; i++) {
        	if(basic[i] == 0) org[i] = true;
        } // 차수가 0인 것이 기본 부품이다.
        
        Queue<Integer> queue = new LinkedList<>();
        queue.add(N); // N에서 기본 부품까지 top-down하자
        used[N] = 1;
        
        while(!queue.isEmpty()) {
        	int x = queue.poll();
        	
        	for(Toy t : graph[x]) {
        		degree[t.x]--;
        		int need = used[x] * t.c;
        		used[t.x] += need;
        		
        		if(degree[t.x] == 0) {
        			queue.add(t.x);
        		}
        	}
        }
        
        for(int i = 1; i < N; i++) {
        	if(org[i]) bw.write(i + " " + used[i] + "\n");
        }
        bw.flush();
        bw.close();
    }
}