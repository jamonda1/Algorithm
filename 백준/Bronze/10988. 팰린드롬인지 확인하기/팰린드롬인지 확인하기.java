import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        StringBuffer sb = new StringBuffer(input);

        String rev = sb.reverse().toString();

        int result = input.equals(rev) ? 1 : 0;

        System.out.println(result);

    }
}