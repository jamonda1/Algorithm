import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int totalStep = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 정리해야 하는 책의 수
		int M = Integer.parseInt(st.nextToken()); // 한 번에 들 수 있는 책의 수
		
		int[] books = new int[N + 1]; // 0을 추가하기 위해 +1
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			books[i] = Integer.parseInt(st.nextToken());
		}
		books[N] = 0; // 정렬할 때 0을 기준으로 음수 양수 가르기 위해 추가
		
		Arrays.sort(books);
		
		int minusCnt = 0; // 음수의 개수
		int plusCnt = 0;  // 양수의 개수
		int zeroIdx = 0;  // 기준이 될 0의 인덱스 번호
		
		for(int i = 0; i < N + 1; i++) {
			if(books[i] == 0) zeroIdx = i;
			if(0 < books[i]) ++plusCnt;
			if(books[i] < 0) ++minusCnt;
		}
		
		plusCountStep(books, plusCnt, zeroIdx, M);
		minusCountStep(books, minusCnt, zeroIdx, M);
		// 다시 되돌아 올 필요가 없으니깐 가장 큰 값을 빼줘야 한다.
		int maxAbs = ((books[0] * -1) > books[N] ? (books[0] * -1) : books[N]);
		totalStep -= maxAbs;
		
		bw.write(totalStep + "\n");
		bw.flush();
		bw.close();
	}
	
	// 0보다 큰 곳에 위치한 책 놓기
	private static void plusCountStep(int[] books, int plusCnt, int zeroIdx, int m) {
		// m은 2일 때 양수의 개수가 5라면 첫 번째 부분을 먼저 탐색해야 한다.
		int temp = (plusCnt != 0 ? plusCnt % m : 0);
		
		if(temp != 0) { // 0에서 m보다 안쪽에 있는 것을 더해준다. 책을 가져다두고 다시 0으로 와서 책을 집어야 하니깐 *2
			totalStep += (books[zeroIdx + temp] * 2);
			temp += m;
		}
		// 그 뒤로는 m만큼 증가하면서 더해준다
		for(int i = zeroIdx + temp; i <= zeroIdx + plusCnt; i+=m) {
			totalStep += (books[i] * 2);
		}
	}
	// 0보다 작은 곳에 위치한 책 놓기
	private static void minusCountStep(int[] books, int minusCnt, int zeroIdx, int m) {
		int temp = (minusCnt != 0 ? minusCnt % m : 0);
		
		if(temp != 0) {
			totalStep += (books[zeroIdx - temp] * -2);
			temp += m;
		}
		for(int i = zeroIdx - temp; i >= 0; i-=m) {
			totalStep += (books[i] * -2);
		}
	}
}
