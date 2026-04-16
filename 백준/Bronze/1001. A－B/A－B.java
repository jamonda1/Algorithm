import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a, b;
		
		a = sc.nextInt();
		b = sc.nextInt();
		
		if(0<a || 0<b) {
			System.out.println((a-b));
		}
		sc.close();

	}

}
