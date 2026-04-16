import java.io.*;

public class Main {
	static int[] nums, result;
	static int R = 7;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 주어진 숫자들 중에서 모든 조합을 해서 100이 되면 각각의 숫자를 출력
		nums = new int[9];	 // 9명의 난쟁이 번호 저장
		result = new int[R]; // 정답인 난쟁이 번호 저장
		
		for(int i = 0; i < 9; i++) { // 난쟁이 번호들 입력
			nums[i] = Integer.parseInt(br.readLine());
		}
		
		permutation(0, 0, 0);	// 조합 메서드 호출
	}
	
	static void permutation(int cnt, int start, int sum) throws IOException {
		if(cnt == R) { // 7개의 수를 뽑았다?
			if(sum == 100) { // 그런데 합이 100이다?
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
				
				for(int i = 0; i < R; i++) { // 합이 100이면 난쟁이 번호 출력하고 시스템 종료
					bw.write(result[i] + "\n");
				}
				bw.flush();
				bw.close();
			}
			return; // 아니면 다른 조합을 위해 return
		}
		
		for(int i = start; i < 9; i++) {
			result[cnt] = nums[i]; // nCr 수행
			permutation(cnt+1, i+1, sum+nums[i]);
		}
	}
}
