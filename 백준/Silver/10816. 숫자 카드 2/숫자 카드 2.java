import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 상근이가 들고 있는 숫자 카드
		int N = Integer.parseInt(br.readLine());
		int[] card1 = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) { // 상근이 카드 입력
			card1[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(card1); // 이분탐색을 위한 정렬
		
		// ------------상근이 카드 관련 종료------------
		
		int M = Integer.parseInt(br.readLine()); // 여기 담긴 카드를 몇 장 들고 있는가?
		int[] card2 = new int[M];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) { // 찾을 카드 입력
			card2[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < M; i++) {
			// 상한선 인덱스 이분탐색으로 찾기
			int low = findLower(card1, card2[i]); // card1에서 card2[i]의 
			// 하한선 인덱스를 이분탐색으로 찾기
			int up = findUpper(card1, card2[i]);
			
			bw.write((up - low) + " "); // 상한값에서 하한값을 빼준다.
		}
		
		bw.flush();
		bw.close();
	}

	private static int findLower(int[] card1, int target) {
		int left = 0;
		int right = card1.length;
		
		while(left < right) {
			// 오버플로우 방지를 위한 안전한 middle 값
			int middle = left + (right - left) / 2;
			
			if(card1[middle] >= target) {
				// 만약 타겟이 중간값보다 작거나 같다면?
				right = middle; // 오른쪽값을 중간값으로 지정하고 다시 시작
			} else {
				// 만약 타겟이 중간값보다 크다면?
				// 왼쪽 값이 middle보다 하나 커야 한다.
				left = middle + 1;
			}
		}
		return left;
	}
	
	private static int findUpper(int[] card1, int target) {
		int left = 0;
		int right = card1.length;
		
		while(left < right) {
			// 오버플로우 방지를 위한 안전한 middle 값
			int middle = left + (right - left) / 2;
			
			if(card1[middle] > target) {
				// 타겟보다 중간값이 크면? 오른쪽 값을 중간값으로 해줘야 한다.
				// 크거나 같다로 해버리면 같을 때도 중간값을 당기기 때문에...
				right = middle;
			} else {
				// 타겟이 중간값보다 크거나 같으면? 더 오른쪽을 보자
				left = middle + 1;
			}
		}
		return right;
	}
}
