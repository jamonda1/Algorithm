import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            String s = br.readLine();
            String[]  tokens = s.split(" ");

            int N = Integer.parseInt(tokens[0]); // 사람 수
            int K = Integer.parseInt(tokens[1]); // 몇 번째부터 삭제?

            ArrayList<Integer> list = new ArrayList<>();
            ArrayList<Integer> result = new ArrayList<>();

            for(int i = 1; i <= N; i++){
                list.add(i);
            }

            // 1 2 3 4 5 6 7
            // 에서 K가 3이니깐 반복문을 K - 1번 돌려서
            // 1. 1을 뒤로 보낸다
            // 2. 2를 뒤로 보낸다음 index(0)을 remove

            for(int i = 0; i < N; i++){
                for(int j = 0; j < K - 1; j++){ // K가 3이면 두 번만 반복
                    list.add(list.get(0));
                    list.remove(0);
                }
                result.add(list.get(0));
                list.remove(0);
            }

            System.out.print("<");
            for(int i : result){
                if(i == result.get(result.size() - 1)){
                    System.out.print(i);
                } else {
                    System.out.print(i + ", ");
                }
            }
            System.out.print(">");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}