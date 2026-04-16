import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 현우는 용돈을 효율적으로 활용하고자 한다.
 * 앞으로 N일 동안 자신이 사용할 금액을 계산했고, 정확히 M번만 통장에서 돈을 빼기로 했다.
 * 현우는 K원을 인출해서 하루를 보낸다. 쓰다가 돈이 부족하면 나머지를 입금하고 다시 K원을 인출한다.
 *
 * 그런데 현우는 M이라는 숫자를 좋아한다.
 * 정확히 M번을 맞추기 위해 남은 금액이 그날 사용할 충분한 금액이어도 다시 통장에 넣고 K원을 인출할 수 있다.
 * 현우는 돈을 아끼기 위해 인출 금액 K원을 최소화하고자 한다.
 * 최소금액 K를 계산해보자! - 최대 범위는 10억
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());  // 현우는 N일을 보내기 위해 M번만 출금을 한다.
        int N = Integer.parseInt(st.nextToken()); // 현우가 보내야 할 일 수 N
        int M = Integer.parseInt(st.nextToken()); // 현우가 출금할 횟수 M
        
        int left = 0;
        int right = 0;
        
        int[] money = new int[N];
        for(int i = 0; i < N; i++) {
        	money[i] = Integer.parseInt(br.readLine());
        	left = Math.max(left, money[i]); // 배열의 최댓값보다 작으면 출금해도 못쓴다..
        	right += money[i]; // 10일 써야 하는데 출금 하루만 가능할 경우를 대비
        }
        
        int result = 0;
        while(left <= right) {
        	int mid = left + (right - left) / 2; // 오버플로우 방지
        	
        	int count = 1; // 최초 1회 출금
        	int tempMid = mid; // 원본 mid 보존
        	for(int curr : money) {
        		if(tempMid < curr) { // 남은 돈으로 생활이 안 되면?
        			tempMid = mid; // 출금하자!!
        			count++; // 출금했으니 카운팅
        		}
        		tempMid -= curr; // 소비할 수 있으면 소비하자!!
        	}
        	
        	if(count <= M) { // 출금 횟수가 적으면 과출금으로 돈 줄이기
        		result = mid;
        		right = mid - 1;
        	} else { // 출금 횟수가 많으면 부족한 것이므로 돈 늘리기
        		left = mid + 1;
        	}
        }
        System.out.println(result);
    }
}