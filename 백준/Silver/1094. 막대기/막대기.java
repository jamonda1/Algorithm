import java.util.Scanner;
/*
 * 막대의 합이 X보다 크면 다음과 같은 연산을 수행한다.
 * 	1. 가지고 있는 막대 중 길이가 가장 짧은 것을 절반으로 자른다.
 * 	2. 자른 막대의 절반 중 하나를 버렸을 때, 나머지 막대의 길이의 합이 X보다 크면 막대를 버린다.
 * 이제 남은 모든 막대를 풀로 붙여서 X를 만든다.
 */
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int X = sc.nextInt();
		
		int count = 0;
		
		while(X > 0) {
			// 가장 오른쪽 비트를 확인했을 때 1이면 ++
			if((X & 1) == 1) count++;
			X = (X >> 1); // 그리고 오른쪽으로 밀어주기
			
		}
		
		System.out.println(count);
		sc.close();
	}
}