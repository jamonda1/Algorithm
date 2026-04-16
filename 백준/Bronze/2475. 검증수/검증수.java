import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){

            String s = br.readLine();

            s = s.replaceAll(" ", "");

            int[] arr = new int[s.length()];
            int sum = 0, result = 0;

            for(int i = 0; i < s.length(); i++){
                arr[i] = Integer.parseInt(s.charAt(i) + "");
                arr[i] *= arr[i];
                sum += arr[i];
            }

            result = sum % 10;

            System.out.println(result);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}