import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] sp = br.readLine().split(" ");
        int s = Integer.parseInt(sp[0]); // dna 문자열의 길이
        int p = Integer.parseInt(sp[1]); // 민호가 비밀번호로 사용할 문자열의 길이

        String dna = br.readLine();
        String[] tokens = br.readLine().split(" ");
        
        int A = Integer.parseInt(tokens[0]); // 민혁이가 정한 A
        int C = Integer.parseInt(tokens[1]); // 민혁이가 정한 C
        int G = Integer.parseInt(tokens[2]); // 민혁이가 정한 G
        int T = Integer.parseInt(tokens[3]); // 민혁이가 정한 T
        
        int[] dnaCnt = new int[4]; // 주어진 문자열의 ACGT 카운트 저장
        
        for(int i = 0; i < p; i++) { // 처음 p까지 먼저 카운트하기
            char str = dna.charAt(i);
            
            switch(str) {
	            case 'A' :
	            	dnaCnt[0]++;
	            	break;
	            case 'C' :
	            	dnaCnt[1]++;
	            	break;
	            case 'G' :
	            	dnaCnt[2]++;
	            	break;
	            case 'T' :
	            	dnaCnt[3]++;
	            	break;
            }
        }
        // 다음 문자를 가져와서 배열++하고 그 전 문자는 배열--
        int result = 0;
        int idx = 0;
        for(int i = p; i < s; i++) {
        	if(dnaCnt[0] >= A && dnaCnt[1] >= C && dnaCnt[2] >= G && dnaCnt[3] >= T) {
        		result++; // 조건에 맞으면 ++
        	}
        	char prev = dna.charAt(idx++);
        	char next = dna.charAt(i);
        	
        	switch(prev) {
	            case 'A' :
	            	dnaCnt[0]--;
	            	break;
	            case 'C' :
	            	dnaCnt[1]--;
	            	break;
	            case 'G' :
	            	dnaCnt[2]--;
	            	break;
	            case 'T' :
	            	dnaCnt[3]--;
	            	break;
            }
        	switch(next) {
	            case 'A' :
	            	dnaCnt[0]++;
	            	break;
	            case 'C' :
	            	dnaCnt[1]++;
	            	break;
	            case 'G' :
	            	dnaCnt[2]++;
	            	break;
	            case 'T' :
	            	dnaCnt[3]++;
	            	break;
	        }
        }
        if(dnaCnt[0] >= A && dnaCnt[1] >= C && dnaCnt[2] >= G && dnaCnt[3] >= T) {
    		result++; // 마지막에 한 번 더 검사해야 함
    	}
        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }
}

