#include <stdio.h>
int main() {
	int N, i;
	scanf("%d", &N);

	for (i = 1; i < 10; i++) {
		printf("%d * %d = %d", N, i, (N * i));
		puts("");
	}

	return 0;
}