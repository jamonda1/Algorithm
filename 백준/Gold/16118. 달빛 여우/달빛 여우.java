import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Node implements Comparable<Node> {
		int a;
		int w;
		int speed;
		public Node(int a, int w, int speed) {
			this.a = a;
			this.w = w;
			this.speed = speed;
		}
		@Override
		public int compareTo(Node n) {
			return this.w - n.w;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        int N = Integer.parseInt(st.nextToken()); // 나무 그루터기의 개수 N
        int M = Integer.parseInt(st.nextToken()); // 오솔길의 개수 M
        
        int[] fox = new int[N + 1]; // 여우
        int[][] wolf = new int[2][N + 1];// 늑대
        Arrays.fill(fox, Integer.MAX_VALUE);
        Arrays.fill(wolf[0], Integer.MAX_VALUE); // 가중치 배열 초기화 완료
        Arrays.fill(wolf[1], Integer.MAX_VALUE); // 가중치 배열 초기화 완료
        
        List<int[]>[] list = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
        	list[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int A = Integer.parseInt(st.nextToken()); // A에서
        	int B = Integer.parseInt(st.nextToken()); // B까지
        	int W = Integer.parseInt(st.nextToken()); // W만큼 걸린다
        	
        	list[A].add(new int[] {B, W * 2}); // 처음부터 2를 곱해서 넣으면
        	list[B].add(new int[] {A, W * 2}); // 나누기를 할 때 double을 쓰지 않아도 된다.
        }
        
        pq.add(new Node(1, 0, 0)); // 여우 다익스트라 시작
        while(!pq.isEmpty()) {
        	Node curr = pq.poll();
        	int x = curr.a; // 지금 노드
        	int t = curr.w; // 걸린 시간
        	
        	if(t > fox[x]) continue;
        	
        	for(int[] n : list[x]) {
        		int nx = n[0];
        		int nt = t + n[1];
        		
        		if(nt >= fox[nx]) continue;
        		fox[nx] = nt;
        		pq.add(new Node(nx, nt, 0));
        	}
        } // 여우 다익스트라 끝
        
        pq.add(new Node(1, 0, 0)); // 늑대 다익스트라 시작
        while(!pq.isEmpty()) {
        	Node curr = pq.poll();
        	int x = curr.a; // 지금 노드
        	int t = curr.w; // 걸린 시간
        	
        	int speed = curr.speed; // 늑대 속도 조절
        	int ns = (speed == 0) ? 1 : 0;
        	
        	if(t > wolf[ns][x]) continue;
        	
        	for(int[] n : list[x]) {
        		int nx = n[0];
        		int nt = (speed == 0) ? t + (n[1] / 2) : t + (n[1] * 2);
        		
        		if(nt >= wolf[speed][nx]) continue;
        		wolf[speed][nx] = nt; // 여우보다 2배 빠르다.
        		
        		pq.add(new Node(nx, nt, ns)); // 속도 토글해서 넣어야 한다.
        	}
        } // 늑대 다익스트라 끝
        
        int result = 0;
        for(int i = 2; i <= N; i++) {
        	// 늑대의 두 배열 중에서 더 작은 쪽을 가져와서 비교해야 한다.
        	int wolfD = (wolf[0][i] < wolf[1][i]) ? wolf[0][i] : wolf[1][i];
        	if(fox[i] < wolfD) result++;
        }
        
        System.out.println(result);
	}
}
