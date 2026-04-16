import java.util.Scanner;
import java.math.BigInteger;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String a = sc.next();
		int b = Integer.parseInt(a, 16);
		
		System.out.println(b);
		
		sc.close();
	}

}
