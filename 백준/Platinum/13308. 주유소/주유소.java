import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    
    // 그래프 연결 정보를 담을 클래스
    static class Edge {
        int to, weight;
        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    // 우선순위 큐에 들어갈 '상태(State)' 클래스
    static class State implements Comparable<State> {
        int node;       // 현재 위치한 도시
        int minOil;     // 지금까지 지나온 도시 중 가장 싼 기름값
        long totalCost; // 현재까지 누적된 총 이동 비용

        public State(int node, int minOil, long totalCost) {
            this.node = node;
            this.minOil = minOil;
            this.totalCost = totalCost;
        }

        @Override
        public int compareTo(State o) {
            // 총비용이 적은 순서대로 큐에서 먼저 빠져나오도록 정렬
            return Long.compare(this.totalCost, o.totalCost);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 도시의 수
        int M = Integer.parseInt(st.nextToken()); // 도로의 수

        // 각 도시의 기름값 입력
        int[] oilPrice = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            oilPrice[i] = Integer.parseInt(st.nextToken());
        }

        // 양방향 그래프 초기화
        ArrayList<Edge>[] graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        // 간선 정보 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Edge(v, w));
            graph[v].add(new Edge(u, w));
        }

        // 2차원 최단 거리 배열: dist[현재 도시][지금까지의 최소 주유 가격]
        // 주유소 가격의 최댓값이 2500이므로 2501 사이즈로 할당
        long[][] dist = new long[N + 1][2501];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dist[i], Long.MAX_VALUE);
        }

        // 다익스트라 시작 세팅
        PriorityQueue<State> pq = new PriorityQueue<>();
        pq.add(new State(1, oilPrice[1], 0)); // 1번 도시 출발
        dist[1][oilPrice[1]] = 0;

        long answer = -1;

        while (!pq.isEmpty()) {
            State curr = pq.poll();

            // [최적화] 이미 더 싼 비용으로 해당 상태를 방문한 적이 있다면 스킵
            if (dist[curr.node][curr.minOil] < curr.totalCost) continue;

            // 목적지(N)에 도착했다면, 큐의 특성상 이것이 무조건 최소 비용
            if (curr.node == N) {
                answer = curr.totalCost;
                break;
            }

            // 인접한 다음 도시들 탐색
            for (Edge next : graph[curr.node]) {
                // 이동 비용 = 다음 도로의 길이 * 내가 들고 있는 가장 싼 기름값
                long nextCost = curr.totalCost + ((long) next.weight * curr.minOil);
                
                // 도착한 도시의 기름값과 비교하여 더 싼 가격으로 VIP 카드 갱신
                int nextMinOil = Math.min(curr.minOil, oilPrice[next.to]);

                // 해당 (도시, 기름값) 상태로 가는 기존 비용보다 지금이 더 싸면 갱신 후 큐에 삽입
                if (dist[next.to][nextMinOil] > nextCost) {
                    dist[next.to][nextMinOil] = nextCost;
                    pq.add(new State(next.to, nextMinOil, nextCost));
                }
            }
        }

        System.out.println(answer);
    }
}