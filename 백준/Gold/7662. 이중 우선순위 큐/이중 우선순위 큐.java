import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;
/*
 * 이중 우선순위 큐에서는 두 가지 연산이 사용된다. 하나는 데이터 삽입이고, 하나는 삭제하는 연산이다.
 * 삭제하는 연산은 또 두 가지가 있다. 하나는 우선순위가 가장 높은 것을 삭제하고, 하나는 가장 낮은 것을 삭제한다.
 * 정수만 저장하는 이중 우선순위 큐 Q가 있다. 정수 자체가 우선순위라고 가정하자. 특정 연산이 주어진 후에 최댓값과 최솟값을 구해라
 */
public class Main {
	
	static TreeMap<Integer, Integer> tm = new TreeMap<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int TC = Integer.parseInt(br.readLine());
		while(TC-- > 0) {
			tm.clear();
			int k = Integer.parseInt(br.readLine()); // 연산 수행 횟수 k
			
			while(k-- > 0) {
				st = new StringTokenizer(br.readLine());
				char o = st.nextToken().charAt(0); // 수행할 명령
				int n = Integer.parseInt(st.nextToken()); // 정수
				
				if(o == 'I') tm.put(n, tm.getOrDefault(n, 0) + 1); // 삽입
				if(o == 'D') delete(n); // 삭제
			}
			
			if(!tm.isEmpty()) bw.write(tm.lastKey() + " " + tm.firstKey() + "\n");
			else bw.write("EMPTY\n");
		} // 전체 테스트 케이스 종료
		bw.close();
	}

	private static void delete(int n) {
		if(n == -1 && !tm.isEmpty()) {
			int cnt = tm.get(tm.firstKey()) - 1;
			if(cnt < 1) tm.remove(tm.firstKey());
			else tm.put(tm.firstKey(), cnt);
		}
		if(n == 1 && !tm.isEmpty()) {
			int cnt = tm.get(tm.lastKey()) - 1;
			if(cnt < 1) tm.remove(tm.lastKey());
			else tm.put(tm.lastKey(), cnt);
		}
	}
}