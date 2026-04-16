import java.io.*;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		Stack<Integer> stack = new Stack<>(); // 스택
		
		int N = Integer.parseInt(br.readLine()); // 이번 학기가 몇 분인가?
		int score = 0;
		
		for(int i = 0; i < N; i++) { // N분만큼 반복
			String[] tokens = br.readLine().split(" "); // 값 입력
			String s = tokens[0]; // 1이냐 0이냐
			int time = 0;		  // 소요 시간 관리
			
			switch(s) { // 1분에 행해지는 것들
				case "1" :
					stack.push(Integer.parseInt(tokens[1])); // 일단 스택에 점수 집어넣기
					time = Integer.parseInt(tokens[2]) - 1;  // 받자마자 시작하는 거니깐 바로 1 줄이기
					if(time == 0) { // 시간이 0이 되면 바로 점수 등록
						score += stack.pop();
					} else {		// 시간이 남아있으면 push
						stack.push(time);						
					}
					break;
					
				case "0" :
					if(!stack.isEmpty()) {			// 일단 스택이 비어있는지 체크
						if(stack.size() % 2 == 0) { // 전에 하던 과제 이어서
							time = stack.pop();     // 저장된 시간 꺼내기
							time--;
						}
						if(time == 0) { // 시간이 0이 되면 바로 점수 등록
							score += stack.pop();
						} else {		// 시간이 남아있으면 push
							stack.push(time);
						}
					}
					break;
			}
		}
		bw.write(score + "\n");
		bw.flush();
		bw.close();
	}
}
