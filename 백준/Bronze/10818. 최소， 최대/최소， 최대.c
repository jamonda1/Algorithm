#include <stdio.h>

int main()
{
    int num, max, min;
    
    scanf("%d", &num);
    int array[num];
    
    max = -1000000;
    min = 1000000;
    for(int i=0; i<num; i++){
        scanf("%d", &array[i]);
        if(array[i] > max){
            max = array[i];
        }
        if(array[i] < min){
            min = array[i];
        }
    }
    printf("%d %d", min, max);
    return 0;
}