import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            String s = br.readLine();
            int K = Integer.parseInt(s);
            int result = 0;

            ArrayList<Integer> list = new ArrayList<>();

            for(int i = 0; i < K; i++) {
                String n = br.readLine();
                int tmp = Integer.parseInt(n);
                if(tmp > 0) {
                    list.add(tmp);
                } else {
                    list.remove(list.size() - 1);
                }
            }
            for(int i = 0; i< list.size(); i++) {
                result += list.get(i);
            }

            System.out.println(result);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}