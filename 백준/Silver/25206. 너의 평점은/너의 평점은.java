import java.util.Objects;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[][] credit = new String[20][3];

        double total = 0;
        double cnt = 0;

        for(int i = 0; i < 20; i++){
            for(int j = 0; j < 3; j++){
                credit[i][j] = sc.next();

                if(Objects.equals(credit[i][2], "A+")){
                    credit[i][2] = "4.5";
                } else if (Objects.equals(credit[i][2], "A0")){
                    credit[i][2] = "4.0";
                } else if (Objects.equals(credit[i][2], "B+")){
                    credit[i][2] = "3.5";
                } else if (Objects.equals(credit[i][2], "B0")){
                    credit[i][2] = "3.0";
                } else if (Objects.equals(credit[i][2], "C+")){
                    credit[i][2] = "2.5";
                } else if (Objects.equals(credit[i][2], "C0")){
                    credit[i][2] = "2.0";
                } else if (Objects.equals(credit[i][2], "D+")){
                    credit[i][2] = "1.5";
                } else if (Objects.equals(credit[i][2], "D0")){
                    credit[i][2] = "1.0";
                } else if (Objects.equals(credit[i][2], "F")){
                    credit[i][2] = "0.0";
                }
            }
        }

        for(int i = 0; i < 20; i++){
            if(Objects.equals(credit[i][2], "P")){
                continue;
            } else {
                total += (Double.parseDouble(credit[i][1]) * Double.parseDouble(credit[i][2]));
                cnt += Double.parseDouble(credit[i][1]);
            }
        }

        System.out.println(total / cnt);
    }
}