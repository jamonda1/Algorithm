import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine()); // 수열의 크기 N
        
        int[] arr = new int[N];
        int[] result = new int[N];
        Arrays.fill(result, -1);
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        } // 수열 저장 완료
        
        Stack<Integer> stack = new Stack<>();
        
        for(int i = 0; i < N - 1; i++) {
        	stack.add(i); // 일단 지금 인덱스 스택에 추가
        	
        	while(!stack.isEmpty()) {
        		if(arr[stack.peek()] < arr[i+1]) {
        			result[stack.pop()] = arr[i+1];        			
        		} else break;
        	}
        }
        
        for(int r : result) {
        	bw.write(r + " ");
        }
        bw.close();
    }
}