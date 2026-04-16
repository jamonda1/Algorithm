import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String num1 = sc.next();
        String num2 = sc.next();

        String tmp1 = "";
        String tmp2 = "";

        for(int i=num1.length(); i>0; i--){
            tmp1 += num1.charAt(i-1);
        }
        for(int i=num2.length(); i>0; i--){
            tmp2 += num2.charAt(i-1);
        }

        if(Integer.parseInt(tmp1) > Integer.parseInt(tmp2)){
            System.out.println(tmp1);
        } else {
            System.out.println(tmp2);
        }
    }
}