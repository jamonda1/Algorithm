import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, result = Integer.MAX_VALUE;
	static int[] pop; // population 인구
	static List<Integer>[] city;
	static boolean canDivide = false;
	static boolean[] selected;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// 백준 시는 1번부터 N번까지의 구역이 있다.
		// 구역을 2개의 선거구로 나눠야 하고, 각 구역은 무조건 둘 중 하나에 속해야 한다.
		// 선거구는 구역을 적어도 하나는 가지고 있어야 한다.
		// 한 선거구의 구역끼리는 각각 연결되어 있어야 한다.
		// 두 선거구에 포함된 인구 차이의 최솟값을 구하라!
		
		N = Integer.parseInt(br.readLine()); // 구역의 개수 N
		pop = new int[N + 1]; // 구역은 1번부터 시작
		selected = new boolean[N + 1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i < N + 1; i++) { // 각 구역의 인구 입력
			pop[i] = Integer.parseInt(st.nextToken());
		}
		
		city = new ArrayList[N + 1]; // 구역과 인접한 구역 리스트로 저장
		
		for(int i = 1; i < N + 1; i++) { // 해당 구역에 인접한 구역 입력
			city[i] = new ArrayList<>(); // 0번은 초기화 x
			st = new StringTokenizer(br.readLine());
			int loop = Integer.parseInt(st.nextToken());
			for(int j = 0; j < loop; j++) { // 인접한 구역이 몇 개인지가 loop에 들어있다
				int temp = Integer.parseInt(st.nextToken());
				city[i].add(temp); // 인접 구역을 다 알려주니깐 단방향으로만 연결해도 된다.
			}
		} // ---------- 변수 선언 및 입력 종료 ----------
		
		divide(1);
		
		// 두 구역으로 나눌 수 없으면 -1을 출력해야 한다.
		System.out.println(canDivide ? result : -1);
	}
	
	static void divide(int area) {
		// 일단 두 구역으로 나눠야 한다.
		// 선거구를 나눈 후에 연결되어 있는지 확인
		// 이후에 인구수 계산
		if(area == N + 1) {
			List<Integer> a = new ArrayList<>();
			List<Integer> b = new ArrayList<>();
			
			for(int i = 1; i < N + 1; i++) {
				if(selected[i]) a.add(i);
				if(!selected[i]) b.add(i);
			}
			
			if(a.size() == 0 || b.size() == 0) return;
			
			if(check(a) && check(b)) { // a랑 b가 잘 연결되어 있는가?
				int first = 0, second = 0;
				canDivide = true; // 이제 여기서 반복문으로 인구를 계산한다
				// 두 수를 구한 후 가장 최저인 값만 전달
				for(int temp : a) { // a의 요소를 가져와서 더하기
					first += pop[temp];
				}
				for(int temp : b) { // a의 요소를 가져와서 더하기
					second += pop[temp];
				}
				
				int min = Math.abs(first - second);
				if(min < result) result = min;
			}
			return;
		}
		
		selected[area] = true; // 선택한 곳이 참인 경우와
		divide(area + 1);
		selected[area] = false; // 거짓인 경우를 나눠서 재귀
		divide(area + 1);
	}

	private static boolean check(List<Integer> area) {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[N + 1]; // 이곳이 모두 false 여야만 진행할 수 있다.
		
		
		queue.add(area.get(0)); // 리스트의 가장 첫 번째에 있는 요소가 초기값
		visited[queue.peek()] = true;
		int count = 1;
		while(!queue.isEmpty()) {
			int temp = queue.poll();
			
			for(int next : city[temp]) { // 처음에 temp가 1이면 2랑 4를 전달
				if(area.contains(next) && !visited[next]) {
					// 전달받은 리스트에 구역이 포함되어 있고, 아직 방문하지 않았으면
					visited[next] = true; // 방문체크
					queue.add(next); // 2랑 4를 큐에 담기
					count++; // 담은 수만큼 증가
				}
			}
		}
		// count랑 area.size()가 같다면 true 아니면 false를 반환할 것
		return count == area.size();
	}
}