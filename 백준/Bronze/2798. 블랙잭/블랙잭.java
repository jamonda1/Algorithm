import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] num = br.readLine().split(" ");
        String[] cards = br.readLine().split(" ");

        int N = Integer.parseInt(num[0]);
        
        int minAbs = 300001;
        int result = 0;
        
    	for(int i = 0; i < N; i++) { // 첫 번째 카드
            int x = Integer.parseInt(cards[i]);
            
            for(int j = 0; j < N; j++) { // 두 번째 카드
                int y = Integer.parseInt(cards[j]);
                if(i == j) continue;
                
                for(int k = 0; k < N; k++) { // 세 번째 카드
                    int z = Integer.parseInt(cards[k]);
                    if(i == k || j == k) continue;
                    
                    // 3카드의 합에서 21을 뺀 절댓값이 가장 작은 것이 정답
                    int tmp = (x + y + z) - Integer.parseInt(num[1]);
                    if(Math.abs(tmp) < minAbs && (x + y + z) <= Integer.parseInt(num[1])) {
                    	minAbs = Math.abs(tmp);
                    	result = x + y + z;
                    }
                }
            }
        }
        
        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }
}