import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine()); // 재료의 개수
        int M = Integer.parseInt(br.readLine()); // 갑옷을 만드는데 필요한 수
        String[] tokens = br.readLine().split(" ");
        
        int[] arr = new int[N]; // 재료의 고유한 번호들 저장
        int result = 0;			// 최종 만들 수 있는 갑옷의 수
        
        for(int i = 0; i < N; i++) {
        	arr[i] = Integer.parseInt(tokens[i]);
        }
        
        Arrays.sort(arr); // 오름차순으로 정렬
        
        int s = 0;	   // 앞에서 출발하는 포인터
        int e = N - 1; // 뒤에서 출발하는 포인터
        int sum = 0;   // M과의 비교를 위한 sum
        while(s < e) { // s가 e랑 같아지면 끝
        	sum = arr[s] + arr[e];
        	if(sum == M) { // sum이 M이랑 같으면?
        		result++;
        		s++;
        		e--;
        	} else if(sum > M) { // sum이 크면 큰 수의 포인터인 e--
        		e--;
        	} else { 			 // sum이 작으면 작은 수의 포인터인 s++
        		s++;
        	}
        }
        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }
}

