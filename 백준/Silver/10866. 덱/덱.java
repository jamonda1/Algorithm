import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
        // push_front일 경우에는 tmp배열에 값을 넣고, tmp + 본 배열을 한 후에 본 배열 = tmp을 한다
            String s = br.readLine();
            int N = Integer.parseInt(s);

            String line;
            String prompt;

            ArrayList<Integer> tmpList = new ArrayList<>();
            ArrayList<Integer> list = new ArrayList<>();

            for(int i = 0; i < N; i++){
                line = br.readLine();
                String[] tokens = line.replace("_", " ").split(" ");

                switch (tokens[0]){
                    case "push": // front일 때랑 back일 때를 구분
                        if(Objects.equals(tokens[1], "front")){
                            // tmpList에 정수를 담고, 그 뒤에 list를 붙이기
                            tmpList.add(Integer.parseInt(tokens[2]));
                            tmpList.addAll(list);
                            // 그다음 list에 값이 있을 수도 있으니 clrear
                            list.clear();
                            // list에 다시 tmpList를 붙이기
                            list.addAll(tmpList);
                            // tmpList는 역할을 다 했으니 clrear
                            tmpList.clear();
                            break;
                        }
                        list.add(Integer.parseInt(tokens[2]));
                        break;

                    case "pop": // front일 때랑 back일 때를 구분
                        if(Objects.equals(tokens[1], "front")){
                            if(!list.isEmpty()) { // 리스트에 값이 있다면
                                // 첫 번째 값을 출력한 다음
                                System.out.println(list.get(0));
                                // 해당 값을 pop
                                list.remove(0);
                            } else {
                                System.out.println(-1);
                            }
                            break;
                        } else {
                            if(list.isEmpty()) {
                                System.out.println(-1);
                            } else {
                                System.out.println(list.get(list.size()-1));
                                list.remove(list.size()-1);
                            }
                        }
                        break;
                    case "size":
                        System.out.println(list.size());
                        break;
                    case "empty":
                        if(list.isEmpty()){
                            System.out.println(1);
                        } else {
                            System.out.println(0);
                        }
                        break;
                    case "front":
                        if(list.isEmpty()){
                            System.out.println(-1);
                        } else {
                            System.out.println(list.get(0));
                        }
                        break;
                    case "back":
                        if(list.isEmpty()){
                            System.out.println(-1);
                        } else {
                            System.out.println(list.get(list.size()-1));
                        }
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}