import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine()); // 기타의 개수 N 입력
		String[] lines = new String[N];
		
		for(int i = 0; i < N; i++) { // 기타줄 입력
			lines[i] = br.readLine();
		}
		
		for(int i = 0; i < N - 1; i++) {
			for(int j = i + 1; j < N; j++) {
				if(lines[i].length() > lines[j].length()) {
					// 만약 길이가 다르면 짧은 게 먼저
					swap(lines, i, j);
				} 
				if(lines[i].length() == lines[j].length()) {
					// 길이가 같으면 숫자 합이 작은 게 먼저
					sortByNum(lines, i, j);
				}
			}
		}
		// 출력 확인
		for(int i = 0; i < N; i++) {
			bw.write(lines[i] + "\n");
		}
		bw.flush();
		bw.close();
	}
	// 큰(x) 문자열과 작은(y) 문자열 위치 변경
	static String[] swap(String[] lines, int x, int y) {
		String temp = lines[x];
		lines[x] = lines[y];
		lines[y] = temp;
		
		return lines;
	}
	
	static String[] sortByNum(String[] lines, int x, int y) {
		// 숫자를 체크해서 모두 더한 값이 작은 것을 앞으로.
		int f = 0, s = 0;		// 각 문자열에 숫자를 더한다
		for(int i = 0; i < lines[x].length(); i++) { // 각 자리에 숫자가 있는가?
			// 숫자 체크
			char tempF = lines[x].charAt(i);
			char tempS = lines[y].charAt(i);
			
			if('0' <= tempF && tempF <= '9') {
				f += (tempF - 48);
			}
			if('0' <= tempS && tempS <= '9') {
				s += (tempS - 48);
			}
		}
		if(f > s) swap(lines, x, y); // 두 수의 합 중에 f가 더 크면 f를 뒤로
		if(f == s) sortByChar(lines, x, y, 0); // 같으면 문자 기준으로 다시 정렬
		
		return lines;
	}
	
	static String[] sortByChar(String[] lines, int x, int y, int idx) {
		
		if(idx == lines[x].length()) {
			return lines;
		}
		
		char tempF = lines[x].charAt(idx);
		char tempS = lines[y].charAt(idx);
		
		if(tempF == tempS) { // 각 자리의 문자가 동일하면 다음 문자 확인
			sortByChar(lines, x, y, idx + 1);
		}
		if(tempF > tempS) { // tempF가 더 큰 문자면 그것을 뒤로
			swap(lines, x, y);
		}
		
		return lines;
	}
}
