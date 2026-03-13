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
	
	static class Node {
		int a;
		double w;
		public Node(int a, double w) {
			this.a = a;
			this.w = w;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        PriorityQueue<double[]> pq = new PriorityQueue<>(new Comparator<double[]>() {
        	@Override
        	public int compare(double[] a, double[] b) {
        		return Double.compare(a[1], b[1]);
        	}
        });
        
        int N = Integer.parseInt(st.nextToken()); // 나무 그루터기의 개수 N
        int M = Integer.parseInt(st.nextToken()); // 오솔길의 개수 M
        
        double[] fox = new double[N + 1]; // 여우
        double[][] wolf = new double[2][N + 1];// 늑대
        Arrays.fill(fox, Double.MAX_VALUE);
        Arrays.fill(wolf[0], Double.MAX_VALUE); // 가중치 배열 초기화 완료
        Arrays.fill(wolf[1], Double.MAX_VALUE); // 가중치 배열 초기화 완료
        
        List<Node>[] list = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
        	list[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int A = Integer.parseInt(st.nextToken());
        	int B = Integer.parseInt(st.nextToken());
        	double W = Double.parseDouble(st.nextToken());
        	
        	list[A].add(new Node(B, W));
        	list[B].add(new Node(A, W));
        }
        
        pq.add(new double[] {1, 0}); // 여우 다익스트라 시작
        while(!pq.isEmpty()) {
        	double[] curr = pq.poll();
        	int x = (int) (curr[0]);// 지금 노드
        	double t = curr[1]; 	// 걸린 시간
        	
        	if(t > fox[x]) continue;
        	
        	for(Node n : list[x]) {
        		int nx = n.a;
        		double nt = t + n.w;
        		
        		if(nt >= fox[nx]) continue;
        		fox[nx] = nt;
        		pq.add(new double[] {nx, nt});
        	}
        } // 여우 다익스트라 끝
        
        pq.add(new double[] {1, 0, -1}); // 늑대 다익스트라 시작
        while(!pq.isEmpty()) {
        	double[] curr = pq.poll();
        	int x = (int) (curr[0]);// 지금 노드
        	double t = curr[1];		// 걸린 시간
        	double speed = curr[2]; // -1이면 /2, 1이면 *2
        	
        	if(speed == 1) {
        		if(t > wolf[0][x]) continue;
        	} else {
        		if(t > wolf[1][x]) continue;
        	}
        	
        	for(Node n : list[x]) {
        		int nx = n.a;
        		double nt = t;
        		
        		if(speed == -1.0) {
        			nt += (n.w / 2);
        			if(nt >= wolf[0][nx]) continue;
        			wolf[0][nx] = nt; // 여우보다 2배 빠르다.
        		}
        		else {
        			nt += (n.w * 2);
        			if(nt >= wolf[1][nx]) continue;
        			wolf[1][nx] = nt; // 여우보다 2배 느리다.
        		}
        		
        		pq.add(new double[] {nx, nt, speed * -1});
        	}
        } // 늑대 다익스트라 끝
        
        int result = 0;
        for(int i = 2; i <= N; i++) {
        	double wolfD = (wolf[0][i] < wolf[1][i]) ? wolf[0][i] : wolf[1][i];
        	if(fox[i] < wolfD) result++;
        }
        
        System.out.println(result);
	}
}
