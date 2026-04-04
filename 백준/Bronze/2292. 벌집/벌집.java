import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        if(N == 1) { // 1이면 바로 출력하자
            System.out.println(1);
            return;
        }

        int before = 1;
        int pivot = 0;

        int idx = 1;
        for(;;) {
            pivot += idx;

            int next = 6 * pivot + 1;
            if(before + 1 <= N && N <= next) { // 범위에 들어오면 출력 후 종료
                System.out.println(idx + 1);
                return;
            }

            before = next;
            idx++;
        }
    }
}