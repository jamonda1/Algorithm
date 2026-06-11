import java.util.*;
/*
노드를 방문할 때마다 양과 늑대가 따라온다.
늑대가 양의 수 이상이 되면 모든 양을 잡아먹는다. 최대한 많은 수의 양을 모아서 루트로 가야 한다.
*/
class Solution {
    
    int result = 0;
    int[] info;
    List<Integer>[] graph = new ArrayList[18];
    boolean[] visited = new boolean[18];
    
    public int solution(int[] info, int[][] edges) {
        this.info = info;
        
        // 그래프 초기화
        for(int i = 0; i < info.length; i++) graph[i] = new ArrayList<>();
        for(int[] e : edges) { // 양방향 연결
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        
        visited[0] = true;
        dfs(1, 0); // 탐색 시작
        
        return result;
    }
    
    void dfs(int sc, int wc) { // sc=양, wc=늑대
        result = Math.max(result, sc);
        
        for(int i = 0; i < info.length; i++) {
            if(!visited[i]) continue; // true인 곳만 확인
            for(int n : graph[i]) {
                if(visited[n]) continue; // false인 곳만 확인
                
                boolean f = (info[n] == 0) ? true : false; // 다음 노드가 양이면 true
                int nextSc = (f) ? sc + 1 : sc;
                int nextWc = (f) ? wc : wc + 1;
                
                if(nextSc <= nextWc) continue;
                
                visited[n] = true;
                dfs(nextSc, nextWc);
                visited[n] = false;
            }
        }
    }
}