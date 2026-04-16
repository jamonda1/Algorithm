import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            String s;
            s = br.readLine();
            String[] tokens = s.split(" ");
            int N = Integer.parseInt(tokens[0]);
            int M = Integer.parseInt(tokens[1]);

            ArrayList<String> A = new ArrayList<>(); // 듣도 못한 사람
            ArrayList<String> B = new ArrayList<>(); // 보도 못한 사람
            ArrayList<String> C = new ArrayList<>(); // 정답 저장

            for(int i = 0; i < N; i++){
                s = br.readLine();
                A.add(s);
            }
            Collections.sort(A);
            for(int i = 0; i < M; i++){
                s = br.readLine();
                B.add(s);
            }

            for(String tmp : B){
                int x = Collections.binarySearch(A, tmp);
                if(x >= 0) {
                    C.add(tmp);
                }
            }
            Collections.sort(C);
            bw.write(C.size() + "\n");
            for(String tmp : C) {
                bw.write(tmp + "\n");
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}