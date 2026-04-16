import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        Deque<String> queue = new ArrayDeque<>();

        while(N --> 0) {
            String[] input = br.readLine().split(" ");

            switch(input[0]) {
                case "push":
                    queue.offerLast(input[1]);
                    break;
                case "pop":
                    bw.write((queue.isEmpty() ? "-1" : queue.pollFirst()) + "\n");
                    break;
                case "size":
                    bw.write(queue.size() + "\n");
                    break;
                case "empty":
                    bw.write((queue.isEmpty() ? "1" : "0") + "\n");
                    break;
                case "front":
                    bw.write((queue.isEmpty() ? "-1" : queue.peekFirst()) + "\n");
                    break;
                case "back":
                    bw.write((queue.isEmpty() ? "-1" : queue.peekLast()) + "\n");
                    break;
            }
        }
        bw.flush();
        bw.close();
    }
}