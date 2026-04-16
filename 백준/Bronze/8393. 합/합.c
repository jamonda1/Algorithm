#include <stdio.h>

int main()
{
    int num, sum;
    scanf("%d", &num);
    
    sum = 0;
    for(int i=1; i<=num; ++i){
        sum += i;
    }
    printf("%d\n", sum);

    return 0;
}