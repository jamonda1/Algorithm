import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            String s = br.readLine();
            int T = Integer.parseInt(s);
            int counter = 0;

            for(int i = 0; i < T; i++){
                String parenthesis = br.readLine();
                String[] tokens = parenthesis.split("");

                if(tokens[0].equals(")") || tokens[tokens.length-1].equals("(")){
                    System.out.println("NO");
                    continue;
                }
                for(String j : tokens){
                    if(j.equals("(")){
                        counter++;
                    } else {
                        counter--;
                    }
                    if(counter == -1){
                        System.out.println("NO");
                        break;
                    }
                }
                if(counter == 0){
                    System.out.println("YES");
                } else if(counter > 0) {
                    System.out.println("NO");
                }
                counter = 0;
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}