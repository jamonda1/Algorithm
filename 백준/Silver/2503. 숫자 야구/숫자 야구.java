import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		List<String> numList = new ArrayList<>();
		
		for(int i = 1; i <= 9; i++) {
			for(int j = 1; j <= 9; j++) {
				if(i == j) continue;
				for(int k = 1; k <= 9; k++) {
					if(i == k || j == k) continue;
					numList.add("" + i + j + k);
				}
			}
		}
		
		int N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			String num = st.nextToken();					// 민혁이가 말한 번호
			int strike = Integer.parseInt(st.nextToken());  // 스트라이크
			int ball = Integer.parseInt(st.nextToken());	// 볼
			
			for(int l = 0; l < numList.size(); l++) { // 리스트에서 값 하나 가져와서 비교
				int s = 0, b = 0;
				
				for(int j = 0; j < 3; j++) {
					for(int k = 0; k < 3; k++) { // 각 숫자가 같다면?
						if(num.charAt(j) == numList.get(l).charAt(k)) {
							if(j == k) s++; // 자리도 같으면 s++
							else b++;		// 자리는 다르면 b++
						}
					}
				}
				// 계산했을 때 처음에 입력된 값이랑 둘 중 하나라도 다르면 해당되지 않는 것이므로 요소 삭제
				if(s != strike || b != ball) {
					numList.remove(numList.get(l));
					l--;
				}
			}
		}
		bw.write(numList.size() + "\n");
		bw.flush();
		bw.close();
	}
}
