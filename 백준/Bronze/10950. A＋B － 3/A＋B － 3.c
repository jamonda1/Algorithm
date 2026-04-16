#include <stdio.h>

int main()
{
    int line, i;
    scanf("%d", &line);
    
    for(i=0; i<line; i++){
        int a, b;
        scanf("%d %d", &a, &b);
        printf("%d\n", a+b);
    }
    

    return 0;
}