import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
/*
 * 홀수번째 수를 입력했을 때, 지금까지 입력된 숫자들의 중앙값을 출력해라
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		List<Integer> inputList = new ArrayList<>();
		List<Integer> outputList = new ArrayList<>();
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 입력
		for(int t = 1; t <= T; t++) {
			
			int M = Integer.parseInt(br.readLine()); // 수열의 크기 M
			int[] arr = new int[M];
			
			int inputLoop = (M / 10) + 1;
			int idx = 0;
			for(int i = 0; i < inputLoop; i++) { // 입력이 10개씩 주어진다.
				st = new StringTokenizer(br.readLine());
				int size = st.countTokens();
				
				for(int j = 0; j < size; j++) { // 그래서 일단 배열에 다 저장하고 탐색하자
					arr[idx++] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i = 1; i <= M; i++) {
				inputList.add(arr[i - 1]); // 리스트에 숫자 추가
				
				if(i % 2 == 1) { // 홀수번째의 입력이 주어지면?
					Collections.sort(inputList);
					outputList.add(inputList.get(i / 2));
				}
			}
			
			int outSize = outputList.size();
			bw.write(outSize + "\n");
			
			for(int i = 1; i <= outSize; i++) { // 10개마다 출력
				bw.write(outputList.get(i - 1) + " ");
				if(i % 10 == 0 || i == outSize) bw.write("\n");
			}
			inputList.clear();
			outputList.clear();
		} // 전체 테스트 케이스 종료
		bw.flush();
		bw.close();
	}
}