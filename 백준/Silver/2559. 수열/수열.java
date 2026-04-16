import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String[] nk = br.readLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        int k = Integer.parseInt(nk[1]);
        
        int[] days = new int[n];
        
        String[] tokens = br.readLine().split(" ");
        for(int i = 0; i < n; i++) {
        	days[i] = Integer.parseInt(tokens[i]);
        }
        
        int temp = 0;
        for(int i = 0; i < k; i++) {
        	temp += days[i];
        }
        
        int s = 0, e = k, max = temp;
        while(e < n) {
        	temp -= days[s++];
        	temp += days[e++];
        	if(temp > max) max = temp;
        }
        bw.write(max + "\n");
        bw.flush();
        bw.close();
    }
}
