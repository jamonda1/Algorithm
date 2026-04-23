import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	static long[] arr, tree, lazy;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 수의 개수 N
		int M = Integer.parseInt(st.nextToken()); // 수의 변경이 일어나는 횟수 M
		int K = Integer.parseInt(st.nextToken()); // 구간합을 구하는 횟수 K
		
		
		arr = new long[N]; // 원본
		tree = new long[N * 4]; // 세그먼트 트리
		lazy = new long[N * 4]; // 메모장
		
		for(int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(br.readLine());
		} // 수열 입력 완료
		
		init(1, 0, N-1); // 트리 구간합 초기화
		
		for(int i = 0; i < M+K; i++) {
			st = new StringTokenizer(br.readLine());
			int o = Integer.parseInt(st.nextToken()); // 1 또는 2
			int b = Integer.parseInt(st.nextToken())-1; // left 인덱스
			int c = Integer.parseInt(st.nextToken())-1; // right 인덱스
			
			if(o == 1) { // 1인 경우에는 b부터 c까지 d를 더한다.
				long d = Long.parseLong(st.nextToken());
				update_range(1, 0, N-1, b, c, d);
				
			}
			if(o == 2) { // 2인 경우에는 b부터 c까지의 합을 구한다.
				long result = sum(1, 0, N-1, b, c);
				bw.write(result + "\n");
			}
		}
		bw.close();
	}

	private static long init(int node, int start, int end) {
		if(start == end) return tree[node] = arr[start];
		
		int mid = (start + end) / 2;
		long l = init(node*2, start, mid);
		long r = init(node*2+1, mid+1, end);
		
		return tree[node] = l + r;
	}
	
	private static void propagate(int node, int start, int end) {
		if(lazy[node] != 0) { // 해당 노드 부분에 변경해야 할 값이 있다면?
			tree[node] += lazy[node] * (end - start + 1);
			
			if(start != end) { // 리프 노드가 아니라면 자식에게 떠넘기기
				lazy[node * 2] += lazy[node];
				lazy[node * 2 + 1] += lazy[node];
			}
			
			lazy[node] = 0; // 해결했으니 [node]는 다시 0으로
		}
	}
	
	private static void update_range(int node, int start, int end, int left, int right, long value) {
		// 업데이트 명령의 재귀마다 lazy를 체크하는 메서드를 호출해야 한다.
		propagate(node, start, end);
		
		if(left > end || right < start) return; // 말도 안 되는 범위는 패스
		
		if(left <= start && end <= right) { // 찾고자 하는 구간에 start와 end가 쏙 들어오면?
			lazy[node] += value; // 해당 위치에 값 적기
			propagate(node, start, end);
			return;
		}
		
		int mid = (start + end) / 2;
		update_range(node*2, start, mid, left, right, value);
		update_range(node*2+1, mid+1, end, left, right, value);
		
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}
	
	private static long sum(int node, int start, int end, int left, int right) {
		propagate(node, start, end);
		
		if(left > end || right < start) return 0; // 말도 안 되는 범위는 패스
		
		if (left <= start && end <= right) {
            return tree[node];
        }
		
		int mid = (start + end) / 2;
		long l = sum(node*2, start, mid, left, right);
		long r = sum(node*2+1, mid+1, end, left, right);
		return l + r;
	}
}