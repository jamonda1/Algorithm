import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){

            String input = br.readLine();

            String[] words = input.split(" ");

            int A = Integer.parseInt(words[0]);
            int B = Integer.parseInt(words[1]);
            int N = Integer.parseInt(words[2]);
            int x = A % B;
            int result = 0;

            for(int i = 0; i < N; i++){
                result = (x * 10) / B;
                x = (x * 10) % B; // 이건 나머지고 몫을 구해야 한다
            }
            System.out.println(result);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}