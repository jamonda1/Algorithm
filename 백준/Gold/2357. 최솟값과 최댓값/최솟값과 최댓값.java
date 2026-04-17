import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	static final int INF = Integer.MAX_VALUE;
	static int N;
	static int[] arr;
	static int[][] tree;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 수열의 개수 N
		int M = Integer.parseInt(st.nextToken()); // 명령 횟수 M
		
		arr = new int[N];
		tree = new int[2][N * 4]; // [0] = 최솟값, [1] = 최댓값
		
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		// 세그먼트 트리 초기화
		init(0, 1, 0, N-1); // 맨 앞의 0은 최솟값이 저장되는 트리라는 뜻
		init(1, 1, 0, N-1); // 맨 앞의 1은 최댓값이 저장되는 트리라는 뜻
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			
			int min = query(0, 1, 0, N-1, a, b); // 최솟값
			int max = query(1, 1, 0, N-1, a, b); // 최댓값
			
			bw.write(min + " " + max + "\n");
		} bw.close();
	}

	private static int init(int pivot, int node, int start, int end) {
		if(start == end) return tree[pivot][node] = arr[start];
		
		int mid = (start + end) / 2;
		
		int l = init(pivot, node*2, start, mid);
		int r = init(pivot, node*2+1, mid+1, end);
		// 0일때와 1일때를 구분
		return tree[pivot][node] = (pivot == 0) ? Math.min(l, r) : Math.max(l, r);
	}
	
	private static int query(int pivot, int node, int start, int end, int left, int right) {
		// 범위를 벗어나는 곳에는 그에 맞는 값을 리턴
		if(end < left || right < start) return (pivot == 0) ? INF : 0;
		
		if(left <= start && end <= right) { // 구하고자 하는 범위 안에 start와 end가 들어온다면?
			return tree[pivot][node];
		}
		
		int mid = (start + end) / 2;
		
		int l = query(pivot, node*2, start, mid, left, right);
		int r = query(pivot, node*2+1, mid+1, end, left, right);
		
		return (pivot == 0) ? Math.min(l, r) : Math.max(l, r);
	}
}