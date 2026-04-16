import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * 암호는 서로 다른 L개의 알파벳 소문자들로 구성
 * 암호로 사용했을 법한 문자의 종류는 C가지
 * 
 * 오름차순으로 정렬한 후에 조합??
 */
public class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	static int L, C;
	static char[] arr;
	static boolean[] visited; // arr에 대한 사용 처리
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken()); // 암호에 사용된 글자 수 L
		C = Integer.parseInt(st.nextToken()); // 암호 후보인 글자 수 C
		
		visited = new boolean[C];
		
		arr = new char[C];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < C; i++) {
			arr[i] = st.nextToken().charAt(0);
		} // 배열에 암호 후보들 입력 완료
		
		Arrays.sort(arr); // 오름차순 정렬
		
		combination(0, 0, new char[L]);
		bw.flush();
		bw.close();
	}

	private static void combination(int depth, int start, char[] result) throws IOException {
		if(depth == L) {
			int count = 0; // 배열에 모음이 몇 개나 있는가??
			for(int i = 0; i < L; i++) {
				if(result[i] == 'a' || result[i] == 'e' || result[i] == 'i' || result[i] == 'o' || result[i] == 'u')
					count++;
			}
			
			if(count > 0 && count <= L - 2) {
				// count가 0보다 크고 L-2보다 작거나 같아야 최소 모음 1개 자음 2개로 이루어지게 된다.
				for(char temp : result) {
					bw.write(temp + "");
				}
				bw.write("\n");
			}
			return;
		}
		
		for(int i = start; i < C; i++) {
			if(!visited[i]) {
				visited[i] = true; // 다음 재귀 때는 내가 선택한 문자를 집으면 안 된다.
				result[depth] = arr[i];
				combination(depth + 1, i + 1, result);
				visited[i] = false; // 다른 조합을 위해 방문 해제
			}
		}
	}
}