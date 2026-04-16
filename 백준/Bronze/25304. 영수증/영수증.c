#include <stdio.h>

int main()
{
    int sum, num, money, things, max, a;
    scanf("%d %d", &sum, &num);
    
    max = 0;
    for(int i=0; i<num; i++){
        scanf("%d %d", &money, &things);
        a = money * things;
        max += a;
    }
    if(max == sum){
        printf("Yes");
    }
    else{
        printf("No");
    }

    return 0;
}