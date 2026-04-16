import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 입력

        for(int t = 1; t <= T; t++) { // 테스트 케이스만큼 반복
            String[] tokens = br.readLine().split(" ");
            int a = Integer.parseInt(tokens[0]); // 첫 번째 수
            int b = Integer.parseInt(tokens[1]); // 두 번째 수

            bw.write("yes\n");
        }
        bw.flush();
        bw.close();
    }
}
