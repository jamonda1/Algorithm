import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
 * 계단에는 점수가 적혀있다. N번째 계단까지 가면서 밟은 총 점수를 구해야 하는데 규칙이 있다.
 * 		1. 한 번에 한 칸, 또는 두 칸씩 오를 수 있다. 즉, 지금 위치의 다음 또는 다다음 계단으로 갈 수 있다.
 * 		2. 연속된 세 개의 계단은 밟을 수 없다.
 * 		3. 마지막 계단은 무조건 밟아야 한다.
 * 즉 첫 번째 계단을 밟고, 다음 또는 다다음 계단으로 한 번에 갈 수 있다.
 * 하지만, 첫 번째 계단에서 다음 계단을 밟은 후 또 다음 계단은 2번의 규칙에 어긋난다.
 * 다다음 계단을 밟고, 다음을 밟은 후 또 다음을 밟는 것도 불가능하다.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine()); // 총 계단의 수 N
        
        int[] step = new int[N + 1]; // 각 계단별 점수 저장
        for(int i = 1; i <= N; i++) {
        	step[i] = Integer.parseInt(br.readLine());
        } // 각 계단별 점수 정보 입력 완료
        
        if(N == 1) { // 계단이 1칸이면 바로 출력 후 종료
        	System.out.println(step[1]);
        	return;
        }
        
        int[] oneStep = new int[N + 1]; // 누적 점수 저장
        int[] twoStep = new int[N + 1]; // 누적 점수 저장
        oneStep[1] = step[1];
        twoStep[1] = step[1];
        for(int i = 2; i <= N; i++) { // 1은 바로 출력하므로, 2부터 시작
        	oneStep[i] = twoStep[i-1] + step[i];
        	// 자기 자신의 두 칸 전과 oneStep의 두 칸 전 중에 큰 것을 저장
        	twoStep[i] = Math.max(oneStep[i-2] + step[i], twoStep[i-2] + step[i]);
        }
        
        System.out.println(Math.max(oneStep[N], twoStep[N]));
    }
}