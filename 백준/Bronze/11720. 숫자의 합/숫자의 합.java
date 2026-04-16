import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n, count = 0;
        n = sc.nextInt();

        String arr = sc.next();

        for(int i=0; i<n; i++){
            count += arr.charAt(i) - 48;
        }
        System.out.println(count);
    }
}