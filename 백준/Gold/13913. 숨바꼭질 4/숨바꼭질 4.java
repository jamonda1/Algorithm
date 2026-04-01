import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	
	static class Obj {
		int x, w;
		public Obj(int x, int w) {
			this.x = x;
			this.w = w;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 수빈이의 위치 N
		int K = Integer.parseInt(st.nextToken()); // 동생의 위치 K
		
		int[] parent = new int[100001];
		Arrays.fill(parent, -1);
		
		Queue<Obj> queue = new LinkedList<>();
		int[] d = {-1, 1};
		
		queue.add(new Obj(N, 0));
		
		while(!queue.isEmpty()) { // 동생 찾기
			Obj curr = queue.poll();
			int x = curr.x;
			int w = curr.w;
			
			if(x == K) {
				bw.write(w + "\n");
				break;
			}
			
			for(int i = 0; i < 3; i++) {
				int tx = (i != 2) ? (x + d[i]) : (x * 2);
				
				if(tx < 0 || 100000 < tx || parent[tx] != -1) continue;
				parent[tx] = x;
				queue.add(new Obj(tx, w + 1));
			}
		}
		
		// 역추적 시작
		Stack<Integer> stack = new Stack<>();
		int pivot = K;
		while(pivot != N) {
			stack.push(parent[pivot]);
			pivot = parent[pivot];
		}
		
		while(!stack.isEmpty()) {
			bw.write(stack.pop() + " ");
		}
		bw.write(K + "");
		bw.close();
	}
}