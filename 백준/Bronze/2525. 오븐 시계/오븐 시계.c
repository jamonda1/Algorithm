#include <stdio.h>

int main()
{
    int h, m, hours, min, sum, plus, a, b;
    
    scanf("%d %d %d", &hours, &min, &plus);
    
    sum = hours * 60 + min + plus; //sum/60은 hours, sum%60은 min
    a = sum / 60;
    b = sum % 60;
    m = b;
    
    if(a == 24){
        h = 0;
    }
    if(a > 24 || a < 48){
        h = a - 24;
    }
    if(a > 48){
        h = a - 48;
    }
    if(a < 24){
        h = a;
    }
    
    printf("%d %d", h, m);

    return 0;
}