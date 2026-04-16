import java.io.*;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 입력
        
        for(int t = 1; t <= T; t++) {
        	ArrayList<ArrayList<Integer>> graph = new ArrayList<>(); // 2차원 동적 배열을 위해
        	
        	String[] nm = br.readLine().split(" ");
        	int n = Integer.parseInt(nm[0]); // 국가의 수 입력
        	int m = Integer.parseInt(nm[1]); // 비행기의 종류 입력
        	
        	int[][] arr = new int[m][2];	 // 왕복하는 쌍 저장
        	
        	for(int i = 0; i <= n; i++) {	 // 0번 노드는 사용하지 않으므로 <= n 번째 노드까지 생성
        		graph.add(new ArrayList<Integer>());
        	}
        	
        	for(int i = 0; i < m; i++) {
        		String[] tokens = br.readLine().split(" "); // a와 b를 왕복하는 쌍 입력
        		int a = Integer.parseInt(tokens[0]);
        		int b = Integer.parseInt(tokens[1]);
        		if(a < b) {
        			graph.get(a).add(b); // 그래프의 a(출발지)에 b(도착지) 연결
        		} else {
        			graph.get(b).add(a);
        		}
        	}
        	int result = graph.size() - 2; // 전체 사이즈에서 0번째 노드랑 n-1이 간선이기 때문에 총 -2
        	bw.write(result + "\n");
        }
        bw.flush();
        bw.close();
    }
}
