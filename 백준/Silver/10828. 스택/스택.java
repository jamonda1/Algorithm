import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            String s = br.readLine();
            int N = Integer.parseInt(s);

            ArrayList<Integer> list = new ArrayList<>();

            for(int i = 0; i < N; i++){
                String line = br.readLine();
                String[] tokens = line.split(" ");

                switch(tokens[0]){
                    case "push":
                        list.add(Integer.parseInt(tokens[1]));
                        break;
                    case "pop":
                        if(list.isEmpty()){
                            System.out.println("-1");
                            break;
                        }
                        System.out.println(list.get(list.size() - 1));
                        list.remove(list.size() - 1);
                        break;
                    case "size":
                        System.out.println(list.size());
                        break;
                    case "empty":
                        if(list.isEmpty()){
                            System.out.println("1");
                            break;
                        }
                        System.out.println("0");
                        break;
                    case "top":
                        if(list.isEmpty()){
                            System.out.println("-1");
                            break;
                        }
                        System.out.println(list.get(list.size() - 1));
                        break;
                    default :
                        break;
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}