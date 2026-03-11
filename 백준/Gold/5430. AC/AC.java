import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
        // R은 배열 뒤집기, D는 첫 번째 원소 버리기
            String s = br.readLine();
            int T = Integer.parseInt(s); // 테스트 케이스의 개수 T
            int reverse = 1;
            int rCount = -1;
            int please = 1;

            ArrayList<Integer> list = new ArrayList<>();

            StringBuilder sb = new StringBuilder(); // 출력될 내용

            for(int i = 0; i < T; i++){
                // 수행할 함수 입력
                String p = br.readLine();
                String[] tokens1 = p.split("");

                // 배열에 들어가는 수의 개수
                String num = br.readLine();
                int n = Integer.parseInt(num);

                // 배열에 들어가는 숫자들
                String input = br.readLine();
                input = input.replace("[", "").replace("]", "").replace(",", " ");
                String[] tokens2 = input.split(" ");

                // 각각의 정수로 쪼갠 다음 list에 차례대로 추가
                if(n != 0){
                    for(String j : tokens2) {
                        list.add(Integer.parseInt(j));
                    }
                }
                // 함수가 담긴 tokens1을 활용하여 배열 조작
                for (String string : tokens1) {
                    switch (string) {
                        case "R":    // R일 경우 우선 리스트가 비었는지 탐색. 비었으면 바로 탈출
                            reverse *= rCount; // R이 한 번이면 -1이 된다. 두 번이면 다시 1이 된다
                            break;

                        case "D": // D일 경우 우선 리스트가 비었는지 탐색
                            if(list.isEmpty()){
                                please = -1;
                            } else {
                                if(reverse > 0){ // reverse가 1인 경우에는 정배열로
                                    list.remove(0);
                                } else {
                                    list.remove(list.size()-1);
                                }

                            }
                            break;
                    }
                }
                // 결과 출력
                // 그냥 사이즈가 1일 때 하나만 출력하는 걸 만들고, 2 이상일 때 reverse를 체크해도 되는 거 아닌가???
                if(please == 1){
                    if(!list.isEmpty()){
                        if(list.size() == 1) { // 리스트에 담긴 원소가 1개밖에 없을 떄
                            sb.append(list.get(0));

                        } else if(reverse > 0) { // 원소가 2개 이상이면서 정방향 출력일 때
                            for(int j = 0; j < list.size(); j++){
                                sb.append(list.get(j));
                                if(j < list.size()-1){
                                    sb.append(",");
                                }
                            }
                        } else if(reverse < 0) { // 원소가 2개 이상이면서 역방향 출력일 때
                            // 역순으로 출력하는 거 생각하자
                            for(int j = list.size()-1; j >= 0; j--) {
                                sb.append(list.get(j));
                                if(j > 0) {
                                    sb.append(",");
                                }
                            }
                        }
                        System.out.println("[" + sb + "]");
                    } else {
                        System.out.println("[]");
                    }
                } else {
                    System.out.println("error");
                }

                // 다음 케이스를 위해 값들 전부 비우기
                please = 1;
                reverse = 1;
                sb.delete(0, sb.length());
                list.clear();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}