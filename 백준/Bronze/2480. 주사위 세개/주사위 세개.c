#include <stdio.h>

int main()
{
    int a, b, c, money;
    
    scanf("%d %d %d", &a, &b, &c);
    
    if(a == b && b == c){
        money = 10000 + a * 1000;
    }
    else if(a == b || b == c || a == c){
        if(a == b){
            money = 1000 + a * 100;
        }
        if(b == c){
            money = 1000 + b * 100;
        }
        if(a == c){
            money = 1000 + c * 100;
        }
    }
    else{
        if(a > b && a > c){
            money = a * 100;
        }
        if(b > a && b > c){
            money = b * 100;
        }
        if(c > b && c > a){
            money = c * 100;
        }
    }
    
    printf("%d", money);

    return 0;
}