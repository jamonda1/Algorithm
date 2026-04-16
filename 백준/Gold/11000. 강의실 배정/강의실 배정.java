import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] room = new int[N][2]; // 각각 시작 시간과 종료 시간을 저장
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			room[i][0] = Integer.parseInt(st.nextToken());
			room[i][1] = Integer.parseInt(st.nextToken());
		}
		
		// 시작 시간이 빠른 순으로 정렬, 같다면 종료 시간 기준
		Arrays.sort(room, (o1, o2) -> {
			if(o1[0] == o2[0]) return o1[1] - o2[1];
			return o1[0] - o2[0];
		});
		
		// 이곳에 종료 시간을 저장해서 종료 시간 기준 오름차순을 해준다.
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.add(room[0][1]);
		
		for(int i = 1; i < N; i++) {
			// pq에 담긴 종료시간보다 시작 시간이 같거나 큰가?
			if(pq.peek() <= room[i][0]) {
				pq.poll(); // 그렇다면 끄집어낸다.
			}
			pq.add(room[i][1]); // 그리고 무조건 종료 시간을 넣는다.
		}
		
		System.out.println(pq.size());
	}
}