import java.util.Scanner;

public class Main
{
	public static void main(String[] args) {
		int in_1, in_2, a, b, c,
		    out_1, out_2, out_3;
		
		Scanner sc = new Scanner(System.in);
		in_1 = sc.nextInt();
		in_2 = sc.nextInt();
		
		a = in_2 / 100;
		b = (in_2 % 100) / 10;
		c = in_2 % 10;
		
		out_1 = in_1 * c;
		out_2 = in_1 * b;
		out_3 = in_1 * a;
		
		System.out.println(out_1);
		System.out.println(out_2);
		System.out.println(out_3);

		System.out.println(in_1 * in_2);
	}
}
