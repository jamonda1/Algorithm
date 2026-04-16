import java.io.*;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	static void pal(int n, int m, boolean b) throws IOException {
		String bar = "";
		if(m > 0) { // bar를 채우기 위한 반복문
			for(int i = 0; i < m; i++) {
				bar = bar + "____";
			}
		}
		
		if(b) {
			// 기본 출력
			System.out.println(bar + "\"재귀함수가 뭔가요?\"");
//			bw.write(bar + "\"재귀함수가 뭔가요?\"\n");
			
			if(n == m) { // 재귀를 통해 증가된 m이 n과 같아지면 최종 답변 출력하고 점점 줄여가기
				System.out.println(bar + "\"재귀함수는 자기 자신을 호출하는 함수라네\"");
//				bw.write(bar + "\"재귀함수는 자기 자신을 호출하는 함수라네\"\n");
				System.out.println(bar + "라고 답변하였지.");
//				bw.write(bar + "라고 답변하였지.\n");
				pal(n, m - 1, false);
			} else { 	 // m과 n이 다르면 계속 스토리만 말하기
				System.out.println(bar + "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.");
				System.out.println(bar + "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.");
				System.out.println(bar + "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"");
				
//				bw.write(bar + "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\r\n"
//						+ bar + "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\r\n"
//						+ bar + "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n");
			}
			// n과 m이 다르면 m을 계속 증가시키고 true 반환하기
			if(n != m) {
				pal(n, m + 1, true);
			}
		} else {
			// boolean이 false일 때는 점점 감소하고 여기서는 종료로 이어지는 문구 담기
			System.out.println(bar + "라고 답변하였지.");
			bw.write(bar + "라고 답변하였지.\n");
			if(m > 0) {
				pal(n, m - 1, false);				
			}
		}
	}
	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		
		System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
//		bw.write("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n");
		
		pal(N, 0, true);
		
		// 출력
//		bw.flush();
//		bw.close();
    }
}