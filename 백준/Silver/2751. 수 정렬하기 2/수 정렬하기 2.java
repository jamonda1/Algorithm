import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){

            String n = br.readLine();
            int line = Integer.parseInt(n);

            int[] input = new int[line];

            for(int i = 0; i < line; i++){ // 입력 받기
                input[i] = Integer.parseInt(br.readLine());
            }
            Arrays.sort(input);

            for (int i : input) { // i에 input이 끝날 때까지 input값을 하나씩 넣는다
                System.out.println(i);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}