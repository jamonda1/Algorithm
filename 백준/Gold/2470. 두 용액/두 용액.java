import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        // 용액들을 입력 받아서 오름차순 정렬
        int N = Integer.parseInt(br.readLine());
        
        int[] arr = new int[N]; // 용액들 저장
        
        String[] tokens = br.readLine().split(" ");
        for(int i = 0; i < N; i++) {
        	arr[i] = Integer.parseInt(tokens[i]);
        }
        
        Arrays.sort(arr); // 용액들 오름차순으로 정렬
        
        int s = 0, e = N - 1, min = 2_000_000_001; // 최대값은 산성의 최대값 * 2 + 1
        int a = 0, b = 0, temp; // 출력해야 하는 두 용액의 특성값 저장
        while(s < e) {
        	temp = Math.abs(arr[s] + arr[e]);  // 절대값으로 비교
        	
        	if(temp < min) { // min보다 작으면 값들 저장
        		min = temp;
        		a = arr[s];
        		b = arr[e];
        	}
        	if(Math.abs(arr[s + 1] + arr[e]) < temp) { // 해당 값이 이전 temp보다 작으면?
        		s++; // s++ 진행
        	} else {
        		e--; // 아니면 e-- 줄여서 다시 반복
        	}
        }
        bw.write(a + " " + b);
        bw.flush();
        bw.close();
    }
}
