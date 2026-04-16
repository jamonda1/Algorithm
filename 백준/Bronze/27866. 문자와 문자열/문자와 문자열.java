import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String word = sc.nextLine();
        char c;

        int num = sc.nextInt();

        c = word.charAt(num-1);

        System.out.println(c);
        
    }
}