import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int result = 0;
	static List<Integer>[] graph;
	static int[] score = {0, 2, 4, 6, 8 , 10, 12, 14, 16,18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40,
						  0, 13, 16, 19, 25, 30, 35, 22, 24, 28, 27, 26};
	static int[] player, dice;
	private static List<Integer>[] init() {
		List<Integer>[] graph = new ArrayList[33];
		for(int i = 0; i < 33; i++) graph[i] = new ArrayList<>();
		
		// 출발부터 도착까지
		for(int i = 0; i < 20; i++) graph[i].add(i+1);
		graph[20].add(-1);
		
		graph[5].add(22); // 10부터 40까지
		for(int i = 22; i < 27; i++) graph[i].add(i+1);
		
		graph[10].add(28); // 파란 20에서
		graph[28].add(29);
		graph[29].add(25); // 중심으로
		
		graph[15].add(30); // 파란 30에서
		for(int i = 30; i <= 31; i++) graph[i].add(i+1);
		graph[32].add(25); // 중심으로
		
		graph[27].add(20); // 중심에서 40으로
		
		return graph;
	}
// ---------- main start ---------- //
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		player = new int[4];// 각 말의 위치 저장
		dice = new int[10]; // 각 주사위의 숫자 저장
		for(int i = 0; i < 10; i++) dice[i] = Integer.parseInt(st.nextToken());
		
		graph = init(); // 그래프 초기화
		
		dfs(0, 0, 0, 0); // 시작
		
		System.out.println(result);
	}
// ---------- main end ---------- //
	
	private static void dfs(int depth, int cnt, long visited, int goal) {
		if(depth == 10) {
			result = Math.max(result, cnt);
			return;
		}
		
		for(int i = 0; i < 4; i++) { // 말은 총 4개
			if((goal & (1 << i)) != 0) continue; // 이미 골인한 말은 패스
			
			int curr = player[i]; // 지금 위치		
			int idx = move(curr, dice[depth]); // 새로운 위치
			
			long next = visited ^ (1L << curr); // 원래 있던 위치 false로 변경
			player[i] = idx; // 플레이어 위치 이동
			
			if(idx == -1) dfs(depth + 1, cnt, next, goal | (1 << i)); // 골에 도착
			
			else if((visited & (1 << idx)) == 0) {
				dfs(depth + 1, cnt + score[idx], next | (1L << idx), goal);
			}
			
			player[i] = curr; // 원복
		}
	}
	
	private static int move(int idx, int num) {
		if(idx == 5 || idx == 10 || idx == 15) { // 지금 idx가 5, 10, 15라면? 파란 칸이다.
			num--;
			idx = graph[idx].get(1);
		}
		
		while(num-- > 0) { // num만큼 계속 전진하기
			if(idx == -1) break;
			idx = graph[idx].get(0);
		}

		return idx;
	}
}