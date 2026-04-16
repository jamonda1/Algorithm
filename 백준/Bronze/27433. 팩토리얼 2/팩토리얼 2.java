import java.util.Scanner;

public class Main {
	public static long fac(int N) {
		if(N == 0 || N == 1) {
			return 1;
		} else {
			return N * fac(N - 1);
		}
	}
	
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		System.out.println(fac(N));
	}
}