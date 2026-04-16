import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.next();
        int[] arr = new int[26];

        for(int i = 0; i < 26; i++){ // 모든 배열에 -1 집어넣기
            arr[i] = -1;
        }

        for(int i = 0; i < input.length(); i++){
            int index = (int)input.charAt(i) - 97;
            if(arr[index] == -1){
                arr[index] = i;
            } else {
                continue;
            }
        }

        for(int i = 0; i < 26; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
