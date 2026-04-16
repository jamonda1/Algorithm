#include <stdio.h>
int main() {
	int a, b, c, d, e, count=0;
	scanf("%d", &a);
	int f = a;

	while (1) {
		b = a / 10; //10의자리
		c = a % 10; //1의 자리
		d = (b + c) % 10; //1, 10의 자리 더한 것
		e = (c * 10) + d; //새로운 수
		a = e; //새로운 수를 e로 변경
		count++;
		if (e == f) break;
	}
	printf("%d", count);

	return 0;
}