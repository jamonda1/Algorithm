import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	// 1과 2를 통해 각각 집들의 위치와 치킨집의 위치 배열을 리턴하는 메서드
	static int[][] address(String[][] sArr, int cnt, int n, String s) {
		int[][] result = new int[cnt][2];
		int idx = 0;
		for(int i = 0; i < n; i++) {	   // s를 탐색해서 좌표를 저장
			for(int j = 0; j < n; j++) {
				if(sArr[i][j].equals(s)) {
					result[idx][0] = i;
					result[idx][1] = j;
					if(idx < cnt) {
						idx++;
					}
				}
			}
		}
		return result;
	}
	// 최소 치킨 거리 구하는 메서드
	static int minChicken(int[][] house, int[][] chicken, int bruteMax, int m) {
		int min = 100000;
		
		for(int i = 0; i < bruteMax; i++) {
			int sum = 0;
			for(int j = 0; j < house.length; j++) {
				int x = house[j][0]; // 집 좌표 계산을 위해
				int y = house[j][1];
				
				int temp = 100000;
				
				for(int k = 0; k < m * 2; k+=2) {
					int a = chicken[i][k]; // 치킨 좌표 계산을 위해
					int b = chicken[i][k + 1];
					
					int distance = Math.abs(a - x) + Math.abs(b - y); // 두 위치 사이의 거리 구하는 공식
					
					if(temp > distance) { // 집에서 가장 가까운 치킨집 거리를 저장
						temp = distance;
					}
				}
				sum += temp; // 저장된 거리를 sum에 합산
			}
			if(min > sum) {  // sum이 min보다 작으면 그게 최소 거리가 된다.
				min = sum;
			}
		}
		return min;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] nm = br.readLine().split(" ");
		int N = Integer.parseInt(nm[0]); // 도시의 전체 크기
		int M = Integer.parseInt(nm[1]); // 남길 치킨집의 수
		
		int countChicken = 0;		     // 치킨집이 몇 개나 있는가?
		int countHouse = 0;				 // 집에 몇 개나 있는가?
		
		String[][] city = new String[N][N]; // 도시의 정보 저장할 배열
	
		for(int i = 0; i < N; i++) {  	    // 도시 정보 입력
			String[] tokens = br.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				if(tokens[j].equals("2")) countChicken++;
				if(tokens[j].equals("1")) countHouse++;
				city[i][j] = tokens[j];
			}
		}
		
		int[][] allChickenAddress = address(city, countChicken, N, "2"); // 치킨집 좌표 저장할 배열 
		int[][] allHouseAddress = address(city, countHouse, N, "1");	 // 집 좌표 저장할 배열
		int[][] bruteChickenAddress = new int[10000][M * 2]; 		 // 치킨집 주소 조합을 저장할 배열
		
		int[] idx = new int[M]; // 조합을 위한 인덱스 배열 저장
		for(int i = 0; i < M; i++) {
			idx[i] = i;
		}
		
		int maxSize = 0; // 실제로 가능한 치킨집 조합의 수 저장
		for(;;) {	     // 조합 구하는 방법은 AI 도움 받음...
			int temp = 0;
			for(int i = 0; i < M * 2; i+=2) {
				bruteChickenAddress[maxSize][i] = allChickenAddress[idx[temp]][0];
				bruteChickenAddress[maxSize][i + 1] = allChickenAddress[idx[temp]][1];
				temp++;
			}
			maxSize++;
			
			// 뒷 부분부터 탐색해야 함
			int check = -1;
			for(int i = M - 1; i >= 0; i--) {
				if(idx[i] < countChicken - M + i) { // idx를 다시 되돌릴 무언가가 필요...
					check = i;
					break;
				}
			}
			if(check == -1) break;
			
			idx[check]++;
			
			for(int i = check + 1; i < M; i++) {
				idx[i] = idx[i - 1] + 1;
			}
		}
		
		int[][] brute = new int[maxSize][M * 2]; // null이 제거된 bruteChickenAddress
		for(int i = 0; i < maxSize; i++) {
			for(int j = 0; j < M * 2; j++) {
				brute[i][j] = bruteChickenAddress[i][j];
			}
		}
		
		int result = minChicken(allHouseAddress, brute, maxSize, M);
		
		bw.write(result + "\n");
		bw.flush();
		bw.close();
	}
}
