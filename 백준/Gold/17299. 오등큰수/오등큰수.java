import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
/*
 * 수열에서 한 숫자가 등장한 횟수를 F(Ai)라고 한다.
 * Ai의 오등큰수는 오른쪽에 있으면서 수열 A에 등장한 횟수가 F(Ai)보다 많고, 그것들 중에서도 가장 왼쪽에 있는 수다.
 */
public class Main {
	
	static class Obj {
		int x, c;
		public Obj(int x, int c) {
			this.x = x;
			this.c = c;
		}
	}
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        Map<Integer, Integer> hm = new HashMap<>();
        Deque<Obj> stack = new ArrayDeque<>();
        
        int N = Integer.parseInt(br.readLine()); // 수열의 크기 N
                
        int[] arr = new int[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            hm.put(arr[i], hm.getOrDefault(arr[i], 0) + 1); // 개수를 value로
        } // 수열 입력 완료
        
        int[] result = new int[N];
        for(int i = 0; i < N; i++) {
        	while(!stack.isEmpty()) {
        		int next = hm.get(arr[i]); // 다음 값의 count
        		Obj peek = stack.peek();
        		
        		if(peek.c < next) {
        			result[peek.x] = arr[i]; // result에 숫자 저장
        			stack.poll();
        		} else break;
        	}
        	
        	// 스택에 인덱스 번호와 카운트를 같이 넣기
        	stack.push(new Obj(i, hm.get(arr[i])));
        }
        
        while(!stack.isEmpty()) { // 찌꺼기들은 모두 -1
        	Obj curr = stack.poll();
        	result[curr.x] = -1;
        }
        
        for(int i = 0; i < N; i++) {
        	bw.write(result[i] + " ");
        }
        bw.close();
    }
}