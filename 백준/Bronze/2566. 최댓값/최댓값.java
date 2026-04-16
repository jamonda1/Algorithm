import java.io.*;

public class Main {
    public static void main(String[] args) {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            int [][] matrix = new int [9][9]; // 9 x 9 의 배열 생성
            int max = 0;
            int x = 0;
            int y = 0;

            for(int i = 0; i < 9; i++) {
                String input = br.readLine();              // 한 줄씩 입력
                String[] numbers = input.split(" "); // 공백을 기준으로 숫자 나누기

                for(int j = 0; j < 9; j++) {
                    matrix[i][j] = Integer.parseInt(numbers[j]);
                }
            }

            for(int i = 0; i < 9; i++) {
                for(int j = 0; j < 9; j++) {
                    if(matrix[i][j] >= max) {
                        max = matrix[i][j];
                        x = i + 1;
                        y = j + 1;
                    }
                }
            }
            bw.write(max + "\n");
            bw.write(x + " " + y);

            bw.flush();
            bw.close();
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}