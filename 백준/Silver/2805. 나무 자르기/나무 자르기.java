import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 후보 범위의 최소값인 L과 최댓값 H를 넉넉하게 잡아서 이를 점점 줄여나간다.
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 나무의 수 N
		int M = Integer.parseInt(st.nextToken()); // 가져가려는 나무의 총 길이
		
		// 적어도 M미터의 나무를 가져가기 위해 설정할 절단기의 높이 H를 구해보자
		
		int[] tree = new int[N]; // 최대 높이는 10억이 주어진다.
		int maxTree = 0;	     // 입력된 나무 중 최대 높이.
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			tree[i] = Integer.parseInt(st.nextToken());
			maxTree = Math.max(maxTree, tree[i]);
		}
		
		long left = 0; // 바닥부터 잘라야 할 수 있으니 0부터 시작
		long right = maxTree;
		long middle = 0;
		while(left < right) {
			middle = (left + right + 1) / 2;
			
			long sum = 0;
			for(int i = 0; i < N; i++) {
				if(tree[i] > middle) {
					sum += (tree[i] - middle);					
				}
			}
			
			if(sum >= M) {
				// 내가 자른 나무의 길이가 목표보다 크거나 같다면?
				// 더 큰 단위로 잘라서 값을 줄여본다.
				left = middle;
			} else {
				right = middle - 1;
			}
		}
		
		bw.write(left + "");
		bw.flush();
		bw.close();
	}
}
