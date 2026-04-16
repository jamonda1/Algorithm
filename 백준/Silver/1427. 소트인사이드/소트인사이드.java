import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] tokens = br.readLine().split("");
		
		int[] sorted = new int[tokens.length];
		for(int i = 0; i < sorted.length; i++) {
			sorted[i] = Integer.parseInt(tokens[i]);
		}
		
		Arrays.sort(sorted);
		for(int i = sorted.length - 1; i >= 0; i--) {
			bw.write(sorted[i] + "");
		}
		
		bw.flush();
		bw.close();
	}
}