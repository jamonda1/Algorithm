import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static long[] arr;
	static long[] tree;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 배열의 길이 N
		int M = Integer.parseInt(st.nextToken()); // 업데이트 횟수 M
		int K = Integer.parseInt(st.nextToken()); // 구간합 조회 횟수 K
		
		arr = new long[N];
		tree = new long[N * 4]; // 안전하게 4배
		
		for(int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}
		
		init(1, 0, N-1); // 세그먼트 트리 초기화
		
		for(int i = 0; i < M+K; i++) {
			st = new StringTokenizer(br.readLine());
			int pivot = Integer.parseInt(st.nextToken()); // 1이면 값 변경, 2면 구간합
			int a = Integer.parseInt(st.nextToken())-1;
			long b = Long.parseLong(st.nextToken()); // b는 int로 안 된다.
			
			if(pivot == 1) {
				long c = b - arr[a];
				arr[a] = b; // 값 갱신
				
				update(1, 0, N-1, a, c);
			} else bw.write(sum(1, 0, N-1, a, b-1) + "\n");
		} bw.close();
	}

	private static long init(int node, int start, int end) {
		if(start == end) return tree[node] = arr[start];
		
		int mid = (start + end) / 2;
		
		return tree[node] = init(node*2, start, mid) + init(node*2+1, mid+1, end);
	}
	
	private static long sum(int node, int start, int end, long a, long b) {
		if(end < a || b < start) return 0; // 범위를 벗어나는 값은 0 리턴
		
		if(a <= start && end <= b) return tree[node];
		
		int mid = (start + end) / 2;
		return sum(node*2, start, mid, a, b) + sum(node*2+1, mid+1, end, a, b);
	}
	
	private static void update(int node, int start, int end, int idx, long value) {
		if(idx < start || end < idx) return;
		
		tree[node] += value;
		
		if(start != end) {
			int mid = (start + end) / 2;
			update(node*2, start, mid, idx, value);
			update(node*2+1, mid+1, end, idx, value);
		}
	}
}