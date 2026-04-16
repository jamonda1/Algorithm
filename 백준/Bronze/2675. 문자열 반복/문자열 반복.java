import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int num = sc.nextInt(); // 테스트 케이스의 개수 입력
        int[] numArr = new int[num]; // 단어를 반복할 횟수를 저장하는 Arr
        ArrayList<String> list = new ArrayList<String>(); // 문자열을 저장할 ArrayList

        for(int i = 0; i < num; i++) {
            numArr[i] = sc.nextInt(); // 반복할 횟수 입력
            String line = sc.nextLine();
            list.add(line.trim()); // 반복될 문자열 입력
        }

        for(int i=0; i< num; i++) {
            for(int j=0; j<list.get(i).length(); j++) {
                for(int k=0; k<numArr[i]; k++) {
                    System.out.print(list.get(i).charAt(j));
                }
            }
            System.out.println();
        }
    }
}