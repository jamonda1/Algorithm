#include <stdio.h>

int main()
{
    int array[9];
    int max = 0;
    int index = 0;
    
    for(int i=1; i<10; i++){
        scanf("%d", &array[i]);
        if(array[i] > max){
            max = array[i];
            index = i;
        }
    }
    printf("%d\n%d", max, index);
    return 0;
}