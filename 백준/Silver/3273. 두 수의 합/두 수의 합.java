import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        
        String[] tokens = br.readLine().split(" ");
        for(int i = 0; i < N; i++) {
        	arr[i] = Integer.parseInt(tokens[i]);
        }
        
        int x = Integer.parseInt(br.readLine());
        
        Arrays.sort(arr);
        
        int s = 0, e = N - 1, sum = 0;
        int result = 0;
        while(s < e) {
        	sum = arr[s] + arr[e];
        	
        	if(sum <= x) {
        		if(sum == x) {
        			result++;
        			s++; e--;
        		}
        		if(sum < x) {
        			s++;
        		}
        	} else {
        		e--;
        	}
        }
        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }
}
