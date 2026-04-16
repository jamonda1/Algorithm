import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 수빈이의 위치
        int K = Integer.parseInt(st.nextToken()); // 동생의 위치
        
        if(N == K) { // 같은 위치에 있다면 바로 종료
            System.out.println(0); // 소요 시간
            System.out.println(1); // 경우의 수
            System.exit(0);
        }
        if(K < N) { // 동생이 더 뒤에 있다면 바로 종료
            System.out.println(N - K);
            System.out.println(1);
            System.exit(0);
        }
        
        int[] move = {-1, 1};
        Queue<int[]> queue = new LinkedList<>();
        boolean[] visited = new boolean[100001];
        
        queue.add(new int[] {N, 0}); // 수빈이의 위치와 초기 시간 넣기
        
        int time = Integer.MAX_VALUE; // 최소 시간 저장을 위해 최대 시간 넣기
        int count = 0; // 최소 시간을 만족하는 경우의 수는 몇 가지인가?
        
        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            visited[current[0]] = true; // 큐에서 당긴 것만 방문처리를 한다.
            
            if(current[0] == K) { // 수빈이의 위치가 동생의 위치라면?
                if(time > current[1]) { // 더 짧은 시간에 도달했다면?
                    time = current[1];  // 최소 시간 업데이트
                    count = 1;          // 경우의 수는 1개부터 시작
                } else if(time == current[1]) {
                    count++;
                } else if (time < current[1]) break;
            }
            for(int i = 0; i < 3; i++) {
                int X = current[0];
                
                if(i != 2) X += move[i];
                if(i == 2) X *= 2; // 조건에 맞는 3방 탐색 시작
                
                // 범위를 벗어나면 다시
                if(X < 0 || 100000 < X) continue;
                // 이미 방문한 곳이면 다시
                if(visited[X]) continue;
                // 여기서 모든 방문처리를 해버리면 수빈이가 1에 있고, 동생이 2에 있을 때
                // 1 + 1과 1 * 2 모두 2라서 경우의 수는 2개인데 여기서 다 방문 처리를 해버리면
                // 1 + 1로 인해 이미 좌표2는 true가 되어 방문했다고 체크하게 된다.
                // 그렇기 때문에 1 * 2를 통해서는 좌표2에 도달할 수 없어진다.
                queue.add(new int[] {X, current[1] + 1});
            }
        }
        System.out.println(time);
        System.out.println(count);
    }
}