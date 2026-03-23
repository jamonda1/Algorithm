import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;
/*
 * 서로 높이가 다른 탑이 N개 있다. 모든 탑의 꼭대기에서 좌측 수평으로 레이저를 쏜다.
 * 수신하는 탑의 번호를 출력하라
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine());
        
        int[] top = new int[N+1]; // 탑의 높이 저장
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            top[i] = Integer.parseInt(st.nextToken());
        } // 탑의 높이 입력 완료
        
        Stack<Integer> stack = new Stack<>();
        
        int[] result = new int[N+1];
        
        for(int i = 1; i <= N; i++) {
        	stack.push(i - 1);
        	int pivot = stack.peek();
        	
        	if(top[pivot] > top[i]) { // 스택의 탑이 다음 탑보다 높다면 레이저 수신 가능
        		result[i] = pivot;
        		stack.push(i);
        	} else {
        		while(!stack.isEmpty()) {
        			pivot = stack.peek();
        			if(top[pivot] > top[i]) {
        				result[i] = pivot;
                		stack.push(i);
                		break;
        			}
        			stack.pop();
        		}
        	}
        }
        
        for(int i = 1; i <= N; i++) {
            bw.write(result[i] + " ");
        }
        bw.close();
    }
}