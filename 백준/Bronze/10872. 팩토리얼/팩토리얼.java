import java.io.*;

public class Main {
    public static long factorial(int n) {
        return n == 1 ? 1 : n * factorial(n - 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        if (n > 0) {
            bw.write(factorial(n) + "\n");
        } else {
            bw.write(1 + "\n");
        }
        bw.flush();
        bw.close();
    }
}