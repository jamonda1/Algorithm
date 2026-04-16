import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] s1 = br.readLine().split("");
        String[] s2 = br.readLine().split("");
        String[] s3 = br.readLine().split("");
        String[] s4 = br.readLine().split("");
        String[] s5 = br.readLine().split("");

        int[] lengths = {s1.length, s2.length, s3.length, s4.length, s5.length};
        int max = 0;
        for(int i = 0; i < 5; i++) {
            if(max < lengths[i]) {
                max = lengths[i];
            }
        }

        for(int i = 0; i < max; i++) {
            if(s1.length > i) {
                bw.write(s1[i]);
            }
            if(s2.length > i) {
                bw.write(s2[i]);
            }
            if(s3.length > i) {
                bw.write(s3[i]);
            }
            if(s4.length > i) {
                bw.write(s4[i]);
            }
            if(s5.length > i) {
                bw.write(s5[i]);
            }
        }

        bw.flush();
        bw.close();
    }
}