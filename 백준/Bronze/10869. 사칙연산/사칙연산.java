import java.util.Scanner;

public class Main
{
	public static void main(String[] args) {
	    
	    int input_1, input_2,
	        add, sub, mul, div_1, div_2;
		
		Scanner sc = new Scanner(System.in);
		
		input_1 = sc.nextInt();
		input_2 = sc.nextInt();
		
		add = input_1 + input_2;
		sub = input_1 - input_2;
		mul = input_1 * input_2;
		div_1 = input_1 / input_2;
		div_2 = input_1 % input_2;
		
		System.out.println(add);
		System.out.println(sub);
		System.out.println(mul);
		System.out.println(div_1);
		System.out.println(div_2);
	}
}
