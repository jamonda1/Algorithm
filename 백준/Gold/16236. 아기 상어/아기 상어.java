/*
 * N * N 수조에 물고기 M 마리와 아기 상어 1 마리
 * 아기 상어와 물고기는 각각의 크기가 존재한다. 상어의 초기값은 2
 * 상어는 1초마다 상하좌우로 1칸씩 이동한다.
 * 
 * 자신보다 큰 물고기가 있는 칸은 이동할 수 없고, 나머지는 이동 가능
 * 만약 이동한 곳에 자신보다 작은 물고기가 있을 경우에는 먹을 수 있다.
 * 
 * 먹을 수 있는 물고기가 2마리 이상이라면 가장 가까운 물고기를 먹으러 간다.
 * 거리는 아기 상어가 있는 칸에서 물고기가 있는 칸으로 이동할 때, 지나야 하는 칸의 개수의 최솟값
 * 		가까운 물고기가 많다면, 가장 위에 있는 물고기를 먹는다.
 * 		가깝고 위에 있는 물고기가 많다면, 가장 왼쪽 물고기를 먼저 먹는다.
 * 
 * 자신의 크기와 같은 수의 물고기를 먹을 때마다 크기가 1 증가한다.
 * 자신의 크기가 2라면 두 마리를 먹어야 3이 된다.
 * 
 * 아기 상어는 몇 초동안 물고기를 먹을 수 있는가? 먹을 수 있는 게 없으면 엄마를 호출!
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, sharkSize = 2, exp = 0, baby_x, baby_y, time = 0;
	static int[][] map;
	static int[] r = {-1, 1, 0, 0};
	static int[] c = {0, 0, -1, 1};
	
	static class Fish implements Comparable<Fish> {
		int row;  // 행, 세로의 위치
		int col;  // 열, 가로의 위치
		int dist; // 상어와의 거리
		
		public Fish(int row, int col, int dist) {
			this.row = row;
			this.col = col;
			this.dist = dist;
		}
		
		@Override
		public int compareTo(Fish o) {
			if(this.dist != o.dist) {// 더 가까운 거리를 반환
				return this.dist - o.dist;
			}
			if(this.row != o.row) {  // 더 위에 있는 것을 반환
				return this.row - o.row;
			}
			return this.col - o.col; // 더 왼쪽에 있는 것을 반환
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int [N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) { // 만약 상어의 위치가 입력 된다면
					baby_x = i; // i 저장
					baby_y = j; // j 저장
					map[i][j] = 0; // 0으로 안 하면 상어가 이동할 수 없다.
				}
			}
		}
		// 자신보다 작고 가장 가까운 물고기를 찾아야 한다.
		// 그것이 여러 마리라면 i값이 가장 작은 물고기를 찾아야 한다.
		// 그것이 여러 마리거나, 내가 제일 높은 위치라면 가장 j값이 가장 작은 물고기를 찾아보자.
		// 지금 내 위치에서 4방 탐색을 돌려서 먹을 수 있는 물고기를 찾아 큐에 넣는다.
		for(;;) {
			Fish target = findFish(); // 먹을 수 있는 가장 최적의 물고기를 찾자.
			if(target == null) break;
			
			int x = target.row; // 해당 물고기의 좌표
			int y = target.col;
			
			map[x][y] = 0; // 물고기 먹었으니 0으로
			exp++; // 경험치 1 증가
			if(exp == sharkSize) {
				exp = 0;
				sharkSize++;
			}
			time += target.dist; // 소요시간 증가
			baby_x = x; // 상어 위치 업데이트
			baby_y = y;
		}
		System.out.println(time);
	}

	private static Fish findFish() {
		// 이 큐를 사용하면 Fish에 담긴 Compare을 기준으로 우선순위를 뽑아준다.
		PriorityQueue<Fish> fish = new PriorityQueue<>();
		Queue<Fish> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		
		queue.add(new Fish(baby_x, baby_y, 0));
		visited[baby_x][baby_y] = true; // 지금 있는 위치도 이미 방문 완료
		
		while(!queue.isEmpty()) {
			Fish current = queue.poll();
			// 기존에 있는 물고기가 지금 탐색하려는 것보다 가깝다면 바로 탈출
			if(!fish.isEmpty() && fish.peek().dist < current.dist) break;
			
			for(int i = 0; i < 4; i++) { // 초기 상어의 위치 주변으로 탐색 실시
				int X = current.row + r[i];
				int Y = current.col + c[i];
				
				if(X < 0 || Y < 0 || X >= N || Y >= N) continue; // 상어가 범위를 벗어나면 다시
				if(map[X][Y] > sharkSize) continue; // 물고기가 상어보다 크면 다시
				if(visited[X][Y]) continue; // 방문한 곳이면 다시
				
				// 다 통과했다면??
				visited[X][Y] = true;
				queue.add(new Fish(X, Y, current.dist + 1));
				
				if(map[X][Y] != 0 && map[X][Y] < sharkSize) {
					fish.add(new Fish(X, Y, current.dist + 1));
				}
			}
		} // bfs 종료
		if(fish.isEmpty()) {
			return null;
		} else {
			return fish.poll();
		}
	}
}