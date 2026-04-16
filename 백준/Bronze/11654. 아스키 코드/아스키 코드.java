import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        char a = sc.next().charAt(0); //문자 하나 입력

        int b = 0;                    //아스키 코드로 저장할 b 초기화

        b = a;                        //int인 b에 문자를 저장하면 자동으로 아스키 코드로 변환

        System.out.println(b);
    }
}