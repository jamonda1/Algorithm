import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		Queue<Integer> queue = new LinkedList<Integer>(); // 큐
		
		String[] nk = br.readLine().split(" "); // n과 k 입력 받기
		int n = Integer.parseInt(nk[0]);
		int k = Integer.parseInt(nk[1]);
		
		int[] numbers = new int[n]; // 요세푸스 순열을 저장할 배열
		
		for(int i = 0; i < n; i++) {// 큐에 1부터 n까지 add
			queue.add(i + 1);
		}
		
		int idx = 0;
		for(;;) {
			if(queue.isEmpty()) break; // queue가 빌 때까지 반복
			
			for(int i = 0; i < k; i++) { // poll과 add를 반복해서 k에 달하면 numbers에 저장
				int x = queue.poll();
				if(i == k - 1) {
					numbers[idx++] = x;
				} else {
					queue.add(x);			
				}
			}
		}
		bw.write("<"); // 출력 조절
		for(int i = 0; i < n; i++) {
			if(i < n - 1) {
				bw.write(numbers[i] + ", ");				
			} else {
				bw.write(numbers[i] + ">");	
			}
		}
		bw.flush();
		bw.close();
	}
}
