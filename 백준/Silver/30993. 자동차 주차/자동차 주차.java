import java.io.*;

public class Main {
	static int TOTAL;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] tokens = br.readLine().split(" ");
		int N = Integer.parseInt(tokens[0]); // 전체 주차장 칸 수 입력
		
		int result = 1;
		for(int i = 1; i <= 3; i++) { // 각각 A B C를 temp에 담아서 메서드 호출
			int temp = Integer.parseInt(tokens[i]);
			TOTAL = 0;
			parking(N, 0, 0, temp);
			N -= temp;
			result *= TOTAL; // 가능한 경우의 수 누적 7 2 2 3의 경우 7C2 * 5C2 * 3C3으로 진행됨
		}
		
		bw.write(result + "\n");
		bw.flush();
		bw.close();
	}
	
	static void parking(int N, int cnt, int start, int car) {
		if(cnt == car) { // 모든 자동차가 선택되었으면 TOTAL++
			TOTAL++;
			return;
		}
		
		for(int i = start; i < N; i++) { // 조합 실행
			// 처음에 N은 7에 차량 하나 뽑았으니 cnt + 1해주고, start도 하나 증가해서 재귀 실행
			parking(N, cnt + 1, i + 1, car);
		}
	}
}
