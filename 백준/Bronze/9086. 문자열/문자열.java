import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int num = sc.nextInt();
        String word;

        for(int i=0; i<num; i++){
            word = sc.next();

            System.out.println(String.valueOf(word.charAt(0)) + word.charAt(word.length()-1));
        }
    }
}