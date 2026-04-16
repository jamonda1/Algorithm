import java.io.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		int result = 0;
		
		for(int i = 0; i < N; i++) {
			int sum = i;
			String[] tokens = Integer.toString(i).split("");
			int size = tokens.length;
			for(int j = 0; j < size; j++) {
				sum += Integer.parseInt(tokens[j]);
			}
			if(N == sum) {
				result = i;
				break;
			}
		}
		
		bw.write(result + "\n");
		bw.flush();
		bw.close();
    }
}