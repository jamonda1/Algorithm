import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
	static boolean[] visited;
	static int result = 0;
	
	static void dfs(int node, int depth) {
		visited[node] = true;
		boolean isLeaf = true;
		
		for(int next : tree.get(node)) {
			if(!visited[next]) {
				isLeaf = false;
				dfs(next, depth + 1);
			}
		}
		
		if(isLeaf) result += depth;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		ArrayList<int[]> tempTree = new ArrayList<>();
		Queue<Integer> queue = new LinkedList<>();
		
		int N = Integer.parseInt(br.readLine()); // 트리 정점의 개수 = N개
		
		visited = new boolean[N + 1];
		
		for(int i = 0; i <= N; i++) {			 // 0번째 노드는 미사용
			tree.add(new ArrayList<>());
		}
		
		for(int i = 1; i < N; i++) {
			String[] tokens = br.readLine().split(" "); // 임시 트리에 토큰들 배열 형태로 저장
			tree.get(Integer.parseInt(tokens[0])).add(Integer.parseInt(tokens[1]));
			tree.get(Integer.parseInt(tokens[1])).add(Integer.parseInt(tokens[0]));
//			tempTree.add(new int[] {Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1])});
		}
		
		// 1. 피벗인 1과 비교
		// 2. 둘 중 하나라도 1이면 나머지 하나를 1번 tree와 queue에 집어넣고, tempTree에서 해당 인덱스 삭제
		// 3. 피벗 = queue.poll();
//		queue.add(1);
//		while(!queue.isEmpty()) { 	 // tempTree에 입력된 값을 트리로 정렬
//			int node = queue.poll(); // 큐에 담긴 숫자를 기준으로 탐색 시작 (초기값 1)
//			for(int i = 0; i < tempTree.size(); i++) {
//				int x = tempTree.get(i)[0];
//				int y = tempTree.get(i)[1];
//				
//				if(node == x || node == y) { // 만약 x나 y가 node랑 일치하면?
//					int temp = (node == x ? y : x); // 일치한 것의 반대를 저장
//					tree.get(node).add(temp); // 그것을 node의 자식으로 집어넣고
//					queue.add(temp);		  // 다음 탐색을 위해 큐에도 넣고
//					tempTree.remove(i--);	  // 임시 트리에는 제거해준다. 그리고 제거하면 인덱스가 당겨지니깐 다시 --
//				}
//			}
//		}
		
		dfs(1, 0);
		
		System.out.println((result % 2 == 0 ? "No" : "Yes"));
	}
}
