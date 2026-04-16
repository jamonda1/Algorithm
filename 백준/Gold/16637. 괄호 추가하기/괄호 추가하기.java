import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int result = Integer.MIN_VALUE;
	static int[] nums;
	static char[] oper;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); // 연산자의 길이 N
		
		nums = new int[N / 2 + 1];
		oper = new char[N / 2];
		
		String input = br.readLine();
		int nIdx = 0;
		int oIdx = 0;
		for(int i = 0; i < N; i++) {
			char temp = input.charAt(i);
			if('0' <= temp && temp <= '9') {
				nums[nIdx++] = temp - '0';
			} else {
				oper[oIdx++] = temp;
			}
		} // 숫자와 연산자 나눠서 배열에 저장하기
		
		dfs(0, nums[0]);
		
		System.out.println(result);
	}

	private static void dfs(int idx, int sum) {
		if(idx == oper.length) {
			result = Math.max(result, sum);
			return;
		}
		
		// 그냥 계산
		int temp1 = cal(sum, nums[idx + 1], oper[idx]);
		dfs(idx + 1, temp1);
		
		// 괄호 계산
		if(idx < oper.length - 1) {
			// 다음 숫자와 다다음 숫자를 연산
			int temp2 = cal(nums[idx + 1], nums[idx + 2], oper[idx + 1]);
			// 그 결과를 지금까지의 결과와 연산 수행
			int temp3 = cal(sum, temp2, oper[idx]);
			dfs(idx + 2, temp3);
		}
		
	}
	
	private static int cal(int A, int B, char oper) {
		switch(oper) {
		case '+' :
			return A + B;
		case '-' :
			return A - B;
		case '*' :
			return A * B;
		}
		
		return 0;
	}
}