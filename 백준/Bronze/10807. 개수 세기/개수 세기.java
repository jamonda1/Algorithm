import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int n, v, count = 0;

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        int[] arr = new int[n];
        
        for(int i=0; i<n; i++){     //배열 입력
            arr[i] = sc.nextInt();
        }

        v = sc.nextInt();           //찾을 수 입력

        for(int i=0; i<n; i++){
            if(arr[i] == v) count++;
        }

        System.out.println(count);
    }
}