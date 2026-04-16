import java.io.*;

public class Main {
	static boolean palindrome(String s) {
		String temp = "";
		// 뒤집을 문자열을 저장하기 위한 임시 문자열 변수 선언 후 문자열 뒤집기
		for(int i = s.length() - 1; i >= 0; i--) {
			temp += s.charAt(i);
		}
		// 초기값과 비교하여 결과에 맞게 true false 반환
		if(s.equals(temp)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String s = br.readLine(); // 문자열 S 입력
		String pal = s;
		int cnt = 0;
		
		int result = 0; // 최종 결과값 저장
		
		for(;;) {
			if(palindrome(pal)) { 	   // 문자열이 팰린드롬이면?
				result = pal.length(); // 길이 저장하고 탈출
				break;
			} else {				   // 팰린드롬이 아니면?
				pal = s;
				for(int i = cnt; i >= 0; i--) {
					pal += s.charAt(i);// 문자 더 붙여서 다시 비교
				}
				cnt++; // 다음 비교에는 문자열을 하나 더 붙여야 하므로 cnt++
			}
		}
		// 출력
		bw.write(result + "\n");
		bw.flush();
		bw.close();
	}
}
