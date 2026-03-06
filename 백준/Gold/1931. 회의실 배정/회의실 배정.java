
/*
 * 하나의 회의실을 사용하고자 하는 N개의 회의가 있다.
 * 각 회의에 대해 시작 시간과 종료 시간이 주어진다.
 * 
 * 일단 종료 시간이 빠른 순으로 정렬해야 한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][] meeting = new int[N][2];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			meeting[i][0] = Integer.parseInt(st.nextToken()); // 시작 시간
			meeting[i][1] = Integer.parseInt(st.nextToken()); // 종료 시간
		}
		
		Arrays.sort(meeting, (o1, o2) -> {
			if(o1[1] == o2[1]) { // 만약 종료 시간이 같다면 시작 시간이 빠른 회의가 우선
				return o1[0] - o2[0];
			}
			return o1[1] - o2[1]; // 종료 시간을 기준으로 오름차순
		});
		
		// 일단 빨리 끝나는 것부터 시작
		// 그리고 끝난 시간과 가장 가까운 start를 선택
		int count = 1;
		int end = meeting[0][1]; // 가장 첫 번째 회의의 종료 시간
		for(int i = 1; i < N; i++) {
			int tempStart = meeting[i][0];
			if(end <= tempStart) { // 기존 종료 시간보다 같거나 큰 시작 시간인 회의가 있다면?
				end = meeting[i][1]; // 종료 시간에 선택된 회의의 종료 시간을 저장
				count++;			
			}
		}
		System.out.println(count);
	}
}