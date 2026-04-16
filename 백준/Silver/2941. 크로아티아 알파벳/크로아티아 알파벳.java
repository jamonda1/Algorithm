import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int count = 0;
        String input = sc.next();

        for(int i = 0; i < input.length(); i++) {
            if (i < input.length() - 1 && input.startsWith("c=", i) || input.startsWith("c-", i)
                    || input.startsWith("d-", i) || input.startsWith("lj", i)
                    || input.startsWith("nj", i) || input.startsWith("s=", i)
                    || input.startsWith("z=", i)) {
                count++;
                i++;
            } else if (i < input.length() - 1 && input.startsWith("dz=", i)) {
                count++;
                i += 2;
            } else {
                count++;
            }

        }
        System.out.println(count);
    }
}