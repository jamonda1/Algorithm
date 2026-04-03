import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
/*
 * 왼쪽부터 스카이라인을 보아 고도가 바뀌는 지점의 좌표가 주어진다.
 * 각 좌표를 활용하여 최소 건물 개수를 구해보자
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine()); // 좌표의 개수 N
        
        Stack<Integer> stack = new Stack<>();
        
        int result = 0; // 1 <= N이므로 최소 1개의 건물이 있다.
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // a는 사용 안 함
            int pivot = Integer.parseInt(st.nextToken()); // 비교할 값
            
            if(pivot == 0) {
            	stack.clear();
            	continue;
            }
            
            boolean f = true; // 이전에 같은 값이 나왔었는지 체크
            while(!stack.isEmpty()) {
            	if(stack.peek() < pivot) break; // 다음 값이 더 크면 탈출
            	else if(stack.peek() == pivot) f = false;
            	
            	stack.pop(); // pivot 이하의 수들은 전부 pop
            }
            
            if(f) result++;
            stack.add(pivot);
        }
        
        System.out.println(result);
    }
}