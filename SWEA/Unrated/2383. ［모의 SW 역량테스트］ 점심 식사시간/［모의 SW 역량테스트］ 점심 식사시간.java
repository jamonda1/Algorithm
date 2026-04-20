import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
/*
 * 방 안의 사람(1)들은 계단(3 or 5)로 내려가야 한다. 내려가는 시간 = 이동하는 시간 + 내려가는 시간
 * 
 *  1. 계단 이동 시간 = 계단까지의 멘하탄 거리
 *  2. 계단을 내려가는 시간
 *      - 입구 도착부터 완전히 내려갈 때까지의 시간.
 *      - 입구 도착하면 1분 후에 내려갈 수 있다.
 *      - 계단을 내려가는 것은 한 번에 3명까지만 된다.
 *      - 계단마다 길이가 있고, 완전히 내려가기까지 K분이 걸린다.
 *  
 * 모든 사람이 다 내려가는 최소 시간을 찾아라!!!
 */
public class Solution {
    
    static class Adrs {
        int x, y;
        public Adrs(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int result;
    static int[][] map;
    static List<Adrs> person, exit, stair; // 사람 저장할 리스트
// ---------- main start ---------- //
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        int TC = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= TC; tc++) {
            
            int N = Integer.parseInt(br.readLine()); // 맵의 크기 N
            
            map = new int[N][N];
            person = new ArrayList<>(); // 사람들 위치 저장
            exit = new ArrayList<>();// 출구 위치 저장
            stair = new ArrayList<>();// 계단에 있는 사람들 저장
            
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j] == 1) person.add(new Adrs(i, j));
                    if(map[i][j] != 0 && map[i][j] != 1) exit.add(new Adrs(i, j));
                }
            } // 맵 정보 입력 완료
            
            result = Integer.MAX_VALUE; // 탐색 시작 전 초기화
            List<Adrs>[] list = new ArrayList[2];
            for(int i = 0; i < 2; i++) list[i] = new ArrayList<>();
            
            lunch(0, list);
            
            bw.write("#" + tc + " " + result + "\n");
        } bw.close(); // 전체 테스트 케이스 종료
    }
// ---------- main end ---------- //
    
    private static void lunch(int idx, List<Adrs>[] list) {
        if(idx == person.size()) { // 기저 조건 도달
        	int time = goDown(list);
        	result = Math.min(result, time);
        	return;
        }
        
        for(int i = 0; i < 2; i++) {
        	Adrs e = exit.get(i); // 계단
        	Adrs p = person.get(idx);
        	
        	int len = Math.abs(e.x - p.x) + Math.abs(e.y - p.y); // 출구까지의 거리
        	
        	list[i].add(new Adrs(idx, len));
        	lunch(idx + 1, list);
        	list[i].remove(list[i].size()-1); // 추가했던 요소 지워주기
        }
    }

	private static int goDown(List<Adrs>[] p) { // 여기서는 x가 사람의 번호, y가 계단까지의 시간이다.
		List<Adrs>[] list = new ArrayList[2];

		int time = 0;
		for(int i = 0; i < 2; i++) {
			stair.clear();
			list[i] = new ArrayList<>();
			for(Adrs a : p[i]) list[i].add(new Adrs(a.x, a.y)); // 리스트 완전 복사하기
			
			Adrs ex = exit.get(i);
			int temp = 0; // 각 계단으로 내려가는 시간 저장
			
			int size = list[i].size();
			int count = 0; // 계단에서 나온 사람 카운트
			boolean[] checked = new boolean[size];
			
			while(count < size) {
				temp++; // while문 한 번당 1분 증가
				int s = stair.size();
				for(int j = s-1; j >= 0; j--) { // 역순으로 순회해서 y가 0이되면 해당 인덱스 삭제
					if(--stair.get(j).y == 0) {
						stair.remove(j);
						count++;
					}
				}
				
				for(int j = 0; j < size; j++) {
					if(checked[j]) continue; // 이미 계단에 들어간 사람은 패스
					
					if(--list[i].get(j).y < 0 && stair.size() < 3) { // 계단에 3명 이하로 있고, 사람이 내려갈 수 있으면?
						stair.add(new Adrs(list[i].get(j).x , map[ex.x][ex.y])); // 계단에 넣어주기
						checked[j] = true; // 그리고 계단에 들어갔다는 체크
					}
				}
			} // 계단 내려가기 종료
			
			time = Math.max(time, temp);
		}
		
		return time;
	}
}