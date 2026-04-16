import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String inputNumber = br.readLine();
        int N = Integer.parseInt(inputNumber);
        int result = 0;

        for(int i = 0; i < N; i++) {
            String inputWord = br.readLine();
            if(checkWord(inputWord)) { // 만약 값이 true면?
                result++;
            }
        }

        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }

    public static boolean checkWord(String word) {
        boolean[] bool = new boolean[26];
        Arrays.fill(bool, false);
        int x = 0;

        for(int i = 0; i < word.length(); i++) {
            char temp = word.charAt(i); // 탐지할 글자

            if(i == 0) { // 첫 번째 글자는 무조건 true
                bool[temp - 'a'] = true;
            } else if(temp == word.charAt(i - 1)) { // 직전 글자와 값이 똑같다면 true
                bool[temp - 'a'] = true;
            } else if(temp != word.charAt(i - 1) && !bool[temp - 'a']) { // 직전과 다른 글자라면
                bool[temp - 'a'] = true;
            } else {
                x = 1;
            }
        }
        return x == 0;
    }
}