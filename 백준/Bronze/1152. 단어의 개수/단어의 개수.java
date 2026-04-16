import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        input = input.trim();

        int nullCount = 0;

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == ' ') {
                nullCount++;
            }
            if(i == input.length()-1) {
                nullCount ++;
            }
        }

        System.out.println(nullCount);

    }
}