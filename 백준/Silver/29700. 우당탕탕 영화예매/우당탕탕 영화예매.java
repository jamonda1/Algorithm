import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] NMK = br.readLine().split(" ");
        int N = Integer.parseInt(NMK[0]); // 영화관 세로줄의 개수
        int M = Integer.parseInt(NMK[1]); // 영화관 가로줄의 개수
        int K = Integer.parseInt(NMK[2]); // 동아리원의 수
        
        int[][] cinema = new int[N][M]; // 영화관 예매 현황 저장
        for(int i = 0; i < N; i++) {
        	String[] tokens = br.readLine().split("");
        	for(int j = 0; j < M; j++) {
        		int tmp = Integer.parseInt(tokens[j]);
        		if(tmp == 1) {
        			tmp = 0;
        		} else {
        			tmp = 1;
        		}
        		cinema[i][j] = tmp; // 편한 구간합을 위해 1은 0으로 0은 1로 해서 저장
        	}
        }
        
        int result = 0; // 결과값 저장
        if(M >= K) { // 가로줄의 수보다 동아리원이 더 많으면 바로 0
        	for(int i = 0; i < N; i++) {
            	int s = 0, e = K, sum = 0;
            	
            	for(int j = 0; j < K; j++) {
            		sum += cinema[i][j]; // 초기 구간합
            	}
            	
            	for(;;) {
        			if(sum == K) result++;
        			if(e == M) break; // 끝점이 가로줄과 같아지면 탈출
        			
        			sum -= cinema[i][s++];
        			sum += cinema[i][e++];
            	}
            }
        }
        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }
}
