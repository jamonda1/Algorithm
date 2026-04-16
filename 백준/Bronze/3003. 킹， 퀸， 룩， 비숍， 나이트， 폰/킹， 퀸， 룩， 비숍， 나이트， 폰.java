import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] unit = {1, 1, 2, 2, 2, 8};
        int[] n = new int[6];

        for(int i = 0; i < 6; i++) {
            n[i] = sc.nextInt();
        }

        for(int i = 0; i < 6; i++) {
            System.out.print(unit[i] - n[i] + " ");
        }

    }
}
