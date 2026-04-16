#include <stdio.h>
int main() {
	int N, i, k, j;
	scanf("%d", &N);

	for (i = 1; i <= N; i++) {
		for (k = (N-i-1); k >= 0; k--)
			printf(" ");
		for (j = 1; j <= i; j++)
			printf("*");
		
		puts("");
	}

	return 0;
}