import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int num = sc.nextInt();
        int tmp = num;

        for(int i = 0; i < num; i++){
            System.out.println(tmp--);
        }
    }
}