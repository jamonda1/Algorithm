import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int num;
        double aver = 0, max = 0;

        num = sc.nextInt();
        double arr[] = new double[num];

        for(int i=0; i<num; i++) {
            arr[i] = sc.nextInt();
        }
        for(int i=0; i<num; i++) {
            if(arr[i] >= max) max = arr[i];
        }
        for(int i=0; i<num; i++) {
            arr[i] = arr[i] / max * 100;
            aver = aver + arr[i];
        }

        System.out.println(aver / num);
    }
}