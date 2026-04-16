import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int tmp = n - 1;

        for(int i = 0; i < n; i++){

            for(int j = i; j < n - 1; j++){
                System.out.print(" "); // 좌측 공백 출력
            }
            for(int j = 0; j < i * 2 + 1; j++){
                System.out.print("*");
            }
            System.out.println();
        }

        for(int i = 1; i < n; i++){
            for(int j = 0; j < i; j++){
                System.out.print(" ");
            }
            for(int j = tmp * 2 - 1; j > 0; j--){
                System.out.print("*");
            }
            tmp --;
            System.out.println();
        }
    }
}
