import java.util.Scanner;
import java.math.BigInteger;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		BigInteger n, m;
		
		n = sc.nextBigInteger();
		m = sc.nextBigInteger();
		
		BigInteger a = new BigInteger("0");
		BigInteger b = new BigInteger("0");
		
		a = n.divide(m);
		b = n.mod(m);
		
		System.out.println(a);
		System.out.println(b);
		
		sc.close();

	}

}
