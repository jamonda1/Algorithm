import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int result = 2;
		
		for(int i = 1; i <= N; i++) {
			result = result * 2 - 1;
		}
		
		System.out.println(result * result);
		
	}
}
