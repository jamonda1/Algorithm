import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * N + 1일째 되는 날에 퇴사를 하려고 한다. 남은 날동안 최대한 많은 상담을 하고 싶다
 * 비서는 하루에 하나씩 서로 다른 상담을 잡아놓았다.
 * 각각의 상담은 상담을 완료하는데 걸리는 기간T와 받을 수 있는 금액P로 이러우져 있다.
 * 상담을 적절히 했을 때 백준이가 얻을 수 있는 최대 수익을 구해라
 */
public class Main {

    static int N, result = 0;
    static int[][] schedule;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 백준이가 상담할 수 있는 날 N

        schedule = new int[N][2]; // 상담 일정

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            schedule[i][0] = Integer.parseInt(st.nextToken()); // 상담 소요 기간
            schedule[i][1] = Integer.parseInt(st.nextToken()); // 받는 금액
        } // 상담 일정 입력 완료

        dfs(0, 0);

        System.out.println(result);
    }

    private static void dfs(int idx, int sum) {
        if(idx >= N) { // idx가 퇴사일을 넘기게 되면 끝
            result = Math.max(result, sum);
            return;
        }

        int day = idx + schedule[idx][0]; // 지금 날짜 + 지금 가능한 상담의 소요일
        if(day <= N) { // 그게 퇴사일 안쪽이면?
            dfs(day, sum + schedule[idx][1]);
        }

        dfs(idx + 1, sum); // 퇴사일보다 크면 다음날 상담을 보자
    }
}