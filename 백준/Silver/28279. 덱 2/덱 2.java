import java.io.*;
import java.util.ArrayDeque;

public class Main {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		ArrayDeque<String> deque = new ArrayDeque<>();
		
		while(N --> 0) {
			String[] input = br.readLine().split(" ");
			
			switch(input[0]) {
				case("1"): // 덱 앞에 삽입
					deque.addFirst(input[1]);
					break;
					
				case("2"): // 덱 뒤에 삽입
					deque.addLast(input[1]);
					break;
					
				case("3"): // 앞의 정수 출력하고 제거
					if(deque.size() > 0) {
						bw.write(deque.poll() + "\n");
					} else {
						bw.write(-1 + "\n");
					}
					break;
				case("4"): // 뒤의 정수 출력하고 제거
					if(deque.size() > 0) {
						bw.write(deque.pollLast() + "\n");
					} else {
						bw.write(-1 + "\n");
					}
					break;
				case("5"):
					bw.write(deque.size() + "\n");
					break;
				case("6"):
					if(deque.isEmpty()) {
						bw.write(1 + "\n");
					} else {
						bw.write(0 + "\n");
					}
					break;
				case("7"):
					if(deque.size() > 0) {
						bw.write(deque.getFirst() + "\n");
					} else {
						bw.write(-1 + "\n");
					}
					break;
				case("8"):
					if(deque.size() > 0) {
						bw.write(deque.getLast() + "\n");
					} else {
						bw.write(-1 + "\n");
					}
					break;
			}
			bw.flush();
		}
		bw.close();
	}
}