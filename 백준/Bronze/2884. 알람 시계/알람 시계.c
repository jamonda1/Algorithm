#include <stdio.h>

int main(void) {
	int a, b;
	scanf("%d%d",&a, &b);
	
	if(a>0 && a<=23){
		if(b<45){
			printf("%d %d", a-1, 60+b-45); }
		if(b>=45){
			printf("%d %d", a, b-45); }
	}
	else if(a==0){
		if(b<45){
			printf("23 %d", 60+b-45); }
		if(b>=45){
			printf("%d %d", a, b-45); }
	}
	
	return 0;
}
