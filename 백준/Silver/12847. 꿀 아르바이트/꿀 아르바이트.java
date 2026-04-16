import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] nm = br.readLine().split(" ");
		int n = Integer.parseInt(nm[0]); // n -> 전체 날짜별 임금
		int m = Integer.parseInt(nm[1]); // m -> 연속으로 일 할 수 있는 날
		
		String[] tokens = br.readLine().split(" "); // 임금 입력
		int[] salary = new int[n]; // 임금을 저장할 배열
		
		long sum = 0; // m 만큼의 합계를 저장
		long max = 0; // 합계 중 최대값을 저장
		
		for(int i = 0; i < n; i++) {
			int temp = Integer.parseInt(tokens[i]);
			salary[i] = temp;
			if(i < m) sum += temp; // 일단 처음에 0 ~ m 인덱스 모두 더하기
		}
		
		max = sum; // max에 sum을 넣어서 초기값으로 설정
		
		for(int i = m; i < n; i++) { // 슬라이딩 윈도우 알고리즘으로 탐색
			sum += (salary[i] - salary[i - m]); // sum에 다음 값을 더한 후 처음 값 빼기
			if(max < sum) max = sum;
		}
		
		bw.write(max + "\n");
		bw.flush();
		bw.close();
	}
}
