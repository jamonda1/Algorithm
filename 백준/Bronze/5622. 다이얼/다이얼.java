import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine(); // 값 입력
        int time = 0; // 시간 누적을 위한 변수

        for(int i = 0; i < input.length(); i++){
            if(65 <= input.charAt(i) && input.charAt(i) <= 67){
                time += 3;
            } else if(68 <= input.charAt(i) && input.charAt(i) <= 70){
                time += 4;
            } else if(71 <= input.charAt(i) && input.charAt(i) <= 73){
                time += 5;
            } else if(74 <= input.charAt(i) && input.charAt(i) <= 76){
                time += 6;
            } else if(77 <= input.charAt(i) && input.charAt(i) <= 79){
                time += 7;
            } else if(80 <= input.charAt(i) && input.charAt(i) <= 83){
                time += 8;
            } else if(84 <= input.charAt(i) && input.charAt(i) <= 86){
                time += 9;
            } else {
                time += 10;
            }
        }
        System.out.println(time);
    }
}