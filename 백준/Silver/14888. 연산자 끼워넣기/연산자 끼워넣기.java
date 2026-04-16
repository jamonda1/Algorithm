import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	// 최대값은 Integer의 가장 작은 값, 최소값은 Integer의 가장 큰 값을 초기값으로 할당
	static int MAX = Integer.MIN_VALUE, MIN = Integer.MAX_VALUE;
	
    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
       
       int N = Integer.parseInt(br.readLine()); // 수열의 개수 입력
       int[] nums = new int[N]; // 수열 저장
       int[] oper = new int[4]; // 연산자 개수 저장
       
       StringTokenizer st = new StringTokenizer(br.readLine());
       for(int i = 0; i < N; i++) { // 수열
    	   nums[i] = Integer.parseInt(st.nextToken());
       }
       
       st = new StringTokenizer(br.readLine());
       for(int i = 0; i < 4; i++) { // 연산자
    	   oper[i] = Integer.parseInt(st.nextToken());
       }
       
       operator(nums, oper, N, 1, nums[0]);
       
       bw.write(MAX + "\n" + MIN);
       bw.flush();
       bw.close();
    }

	private static void operator(int[] nums, int[] oper, int n, int depth, int result) {
		if(depth == n) {
			MAX = Math.max(MAX, result);
			MIN = Math.min(MIN, result);
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			if(oper[i] > 0) {
				int temp = 0; // result += nums를 해버리면 메서드(1)의 result값이 영구적으로 바뀌고 만다.
				oper[i]--;	  // 그래서 재귀의 리턴 리턴 후에 메서드(1)의 반복문이 돌아갈 때 결과값이 이상하게 나온다.
				switch(i) {
				case 0 : // 더하기
					temp = (result + nums[depth]); break;
				case 1 : // 빼기
					temp = (result - nums[depth]); break;
				case 2 : // 곱하기
					temp = (result * nums[depth]); break;
				case 3 : // 나누기
					temp = (result / nums[depth]); break;
				}
				// n = 2일 경우 연산자의 개수는 1개
				// 그러므로 처음에 초기값으로 숫자를 하나 쓰니깐 depth는 1부터 시작한다.
				// 메서드(1)에서 연산자를 하나 소비해서 남은 게 없다.
				// 그러므로 재귀(2)에서 결과를 비교하고 return한다.
				operator(nums, oper, n, depth + 1, temp);
				oper[i]++;
			}
		}
	}
}