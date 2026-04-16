import java.io.*;
import java.util.Stack;

public class Main {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		Stack<String> stack = new Stack<>();
		
		while(N --> 0) {
			String[] input = br.readLine().split(" ");
			
			switch(input[0]) {
				case "1" : // 정수 x를 스택에 push
					stack.push(input[1]);
					break;
				case "2" : // 스택의 맨 위 정수를 출력한 다음 pop
					if(stack.size() > 0) {
						bw.write(stack.peek() + "\n");
						stack.pop();
					} else {
						bw.write(-1 + "\n");
					}
					break;
				case "3" :
					bw.write(stack.size() + "\n");
					break;
				case "4" :
					if(stack.isEmpty()) {
						bw.write(1 + "\n");
					} else {
						bw.write(0 + "\n");
					}
					break;
				case "5" :
					if(stack.isEmpty()) {
						bw.write(-1 + "\n");
					} else {
						bw.write(stack.peek() + "\n");
					}
					break;
			}
			bw.flush();
		}
		bw.close();
	}
}