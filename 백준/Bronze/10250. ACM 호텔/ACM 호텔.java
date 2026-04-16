import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {
            String[] input = br.readLine().split(" ");
            int H = Integer.parseInt(input[0]);
            int N = Integer.parseInt(input[2]);

            int A = N % H;
            int B = N / H;

            if(A == 0) {
                if(B < 10) {
                    bw.write(H + "0" + B + "\n");
                } else {
                    bw.write(H + "" + B + "\n");
                }
            } else {
                B += 1;
                if(B < 10) {
                    bw.write(A + "0" + B + "\n");
                } else {
                    bw.write(A + "" + B + "\n");
                }
            }
            bw.flush();
        }
        bw.close();
    }
}