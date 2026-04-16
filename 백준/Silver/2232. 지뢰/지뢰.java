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

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		List<Integer> result = new ArrayList<>();// 직접 터트려야 하는 지뢰의 인덱스 번호+1 저장
		
		int N = Integer.parseInt(br.readLine()); // 지뢰의 개수 입력
		int[] mine = new int[N];				 // 지뢰의 충격 강도 저장
		
		for(int i = 0; i < N; i++) {
			mine[i] = Integer.parseInt(br.readLine());
		}
		
		// 지뢰는 초과하는 힘을 가해야 터진다. 같은 숫자면 터지지 않는다
		check(result, mine, 0, N);
		
		for(int i = 0; i < result.size(); i++) {
			bw.write(result.get(i) + "\n");
		}
		
		bw.flush();
		bw.close();
	}
	
	static List<Integer> check(List<Integer> result, int[] mine, int idx, int N) {
		
		if(idx == N) { // idx가 N과 같아질 때까지 반복
			return result;
		}
		
		int pre = (idx == 0 ? 0 : mine[idx - 1]); 		// 이전 지뢰
		int middle = mine[idx];							// 피벗 지뢰
		int post = (idx == N - 1 ? 0 : mine[idx + 1]);  // 다음 지뢰
		
		if(pre <= middle && middle >= post) { // 피벗 지뢰보다 큰 값이 양쪽에 없으면 직접 터트려야 함
			result.add(idx + 1);
			check(result, mine, idx + 1, N);  // 리스트에 추가한 후에 다음 값 탐색
		} else {
			check(result, mine, idx + 1, N);  // 큰 값이 양쪽에 있으면 다음 값 탐색 (추가x)
		}
		
		return result;
	}
}
