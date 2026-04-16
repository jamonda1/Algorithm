import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String word = sc.nextLine();
        int count = 0;

        for(int i=0; i<word.length(); i++) {
            count++;
        }
        System.out.println(count);
    }
}