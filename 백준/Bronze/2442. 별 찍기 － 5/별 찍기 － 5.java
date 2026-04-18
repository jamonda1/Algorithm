import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = sc.nextInt();
		
		for(int i = 1; i <= N; i++) {
			for(int j = i; j < N; j++) bw.write(" ");
			
			for(int j = 0; j < i*2-1; j++) bw.write("*");
			bw.write("\n");
		} bw.close();
	}
}