import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
/*
 * 스위치는 켜져 있거나, 꺼져 있는 상태다.
 * 스위치 8개의 상태가 표시되어 있다. 학생 몇을 뽑아서 번호를 나눠줬다
 * 학생들은 성별과 받은 숫자에 따라 스위치를 조작한다.
 * 남 = 번호가 자기 반은 수의 배수면 그 스위치의 상태를 바꾼다.
 * 여 = 번호가 같고, 좌우가 대칭이면 대칭인 구간 전체의 스위치 상태를 바꾼다.
 */
public class Main {
	
	static int N;
	static int[] toggle;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		
		N = Integer.parseInt(br.readLine()); // 스위치의 개수 N
		toggle = new int[N + 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			int temp = Integer.parseInt(st.nextToken());
			toggle[i] = 1;
			if(temp == 0) toggle[i] = -1;
		} // 토글 값 입력 완료
		
		int students = Integer.parseInt(br.readLine()); // 학생들의 수
		for(int i = 0; i < students; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken()); // 학생의 성별 A
			int B = Integer.parseInt(st.nextToken()); // 받은 번호 B
			
			changeToggle(A, B);
		}
		
		int count = 0;
		for(int i = 1; i <= N; i++) {
			bw.write((toggle[i] == 1 ? 1 : 0) + " ");
			count++;
			if(count == 20) { // 한 줄에는 20개만 출력해야 한다.
				bw.write("\n");
				count = 0;
			}
		}
		
		bw.flush();
		bw.close();
	}

	private static void changeToggle(int a, int b) {
		if(a == 1) { // 남학생일 때
			for(int i = b; i <= N; i+=b) {
				toggle[i] *= -1; // 배수만 스위치 변경
			}
		}
		
		if(a == 2) {
			int left = b; // 인덱스 기준 왼쪽
			int right = b;// 인덱스 기준 오른쪽
			
			while(toggle[left] == toggle[right]) {
				// 범위 안에 있고, 서로 값이 같을 때만 반복
				left--;
				right++;
				
				if(left == 0 || N < right) {
					break;
				}
			}
			
			// 반복문 종료 후 값을 보정해줘야 한다.
			for(int i = left+1; i < right; i++) {
				toggle[i] *= -1;
			}
		}
	}
}
