import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] firstLine = br.readLine().split(" ");	 // 첫 째 줄 입력
		int N = Integer.parseInt(firstLine[0]);
		int M = Integer.parseInt(firstLine[1]);
		
		String[] secondeLine = br.readLine().split(" "); // 둘 째 줄 입력
		
		int result = 0; // 결과값 저장
		for(int i = 0; i < N; i++) {
			int sum = 0; // 목표 숫자를 위한 sum
			for(int j = i; j < N; j++) {
				sum += Integer.parseInt(secondeLine[j]); // sum 에 숫자들을 더하고
				if(sum == M) { // 그것을 비교해서 맞으면 카운팅
					result++;
					break;
				}
				if(sum > M) { // 목표 숫자보다 커지면 바로 break
					break;
				}
			}
		}
		bw.write(result + "\n");
		bw.flush();
		bw.close();
	}
}
