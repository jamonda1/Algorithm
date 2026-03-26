import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	
	static class Tall {
		int idx, cnt;
		public Tall(int idx, int cnt) {
			this.idx = idx;
			this.cnt = cnt;
		}
	}
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine()); // 사람의 수 N
        
        int[] people = new int[N];
        
        for(int i = 0; i < N; i++) {
            people[i] = Integer.parseInt(br.readLine());
        } // 사람들의 키 입력 완료
        
        Stack<Tall> stack = new Stack<>();
        
        long result = 0;
        for(int i = 0; i < N; i++) {
        	int cnt = 1;
        	
        	while(!stack.isEmpty()) {
        		int top = people[stack.peek().idx];
        		if(top > people[i]) { // 탑보다 다음 값이 작으면 +1
        			result++;
        			break;
        		} else if(top < people[i]) { // 탑보다 다음 값이 크면?
        			result += stack.pop().cnt;
        			continue;
        		} else { // 탑이랑 다음 값이랑 같으면?
        			int tempCnt = stack.pop().cnt;
        			cnt += tempCnt;
        			result += tempCnt;
        		}
        	}
        	
        	stack.add(new Tall(i, cnt));
        }
        
        System.out.println(result);
    }
}