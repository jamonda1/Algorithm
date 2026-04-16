import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 국가 예산의 총액은 미리 정해져있다. 그래서 모든 예산 요청을 배정해주기는 어렵다.
 * 정해진 총액 이하에서 가능한 최대의 총 예산을 다음과 같은 방법으로 배정한다.
 *
 * 1. 모든 요청이 배정될 수 있다면 그대로 배정
 * 2. 배정될 수 없으면 상한액을 정하고, 상한액 이상이면 상한액까지, 이하면 요청 그대로 배정한다.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()); // 지방의 수 N
        int[] city = new int[N];
        int max = 0; // 예산의 최대값 입력
        int sum = 0;

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            city[i] = Integer.parseInt(st.nextToken());
            sum += city[i];
            if(city[i] > max) max = city[i];
        } // 각 지방이 요청한 예산 입력 완료

        int M = Integer.parseInt(br.readLine()); // 국가의 총 예산 M

        if(sum <= M) { // 요청 예산의 합이 총 예산 이하라면 바로 종료
            System.out.println(max);
            return;
        }

        // 위를 짜르고 남은 합이 예산과 가장 가까워야 한다.
        int left = 0;
        int right = max;
        int result = 0;
        while(left <= right) { // 값이 같아지면 탈출해야 한다.
            int middle = (left + right) / 2;

            int tempSum = 0; // 이것을 M과 비교해야 한다.
            for(int i = 0; i < N; i++) {
                if(city[i] <= middle) { // temp 이하라면 가능하므로 바로 더하기
                    tempSum += city[i];
                } else { // temp보다 크다면 위를 짜르고 더해야 한다.
                    tempSum += middle;
                }
            }

            if(tempSum <= M) { // 더한 값이 M 이하라면 혹시 더 적절한 값이 있을 수도 있으니 left를 늘려보자
                result = middle;
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        // 가장 마지막에 저장된 result가 답
        System.out.println(result);
    }
}