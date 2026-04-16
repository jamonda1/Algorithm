import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 공백을 기준으로 수가 각각 주어지므로 바로 배열로 나누기
		String[] tokens = br.readLine().split(" ");
		// e = 지구, s = 태양, m = 달
		int e = Integer.parseInt(tokens[0]);
		int s = Integer.parseInt(tokens[1]);
		int m = Integer.parseInt(tokens[2]);
		// 카운팅을 위한 초기값
		int eCnt = 1, sCnt = 1, mCnt = 1; 
		int result = 1;
		
		for(;;) {
			// 카운팅 값과 입력한 값이 일치하면 바로 탈출
			if(eCnt == e && sCnt == s && mCnt == m) break;
			
			eCnt++; // 각각 1씩 늘리고 그 때마다 result도 ++
			sCnt++;
			mCnt++;
			result++;
			
			if(eCnt == 16) eCnt = 1; // 정해진 범위를 벗어나면 다시 1로 초기화
			if(sCnt == 29) sCnt = 1;
			if(mCnt == 20) mCnt = 1;
		}
		// 출력
		bw.write(result + "\n");
		bw.flush();
		bw.close();
	}
}
