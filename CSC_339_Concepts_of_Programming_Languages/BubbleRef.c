//This is my own work: Aaron Denton

#include <stdio.h>

void bubbleSort(int *aptr, int *sptr);

int main() {
	int intArray[20];	
	int i = 0, arrSize = 0;
		
	printf("Input the size of the array, maximum 20 spaces\n");
	scanf("%d", &arrSize);
	
	printf("Input %d integer values\n", arrSize);
	while(i < arrSize) {
		scanf("%d", &intArray[i]);
		i++;
	}
	
	int *arrptr = intArray;
	int *sizeptr = &arrSize;
	
	bubbleSort(arrptr, sizeptr);
	printf("BUBBLE SORTED:\n");
	
	i = 0;
	while(i < arrSize) {
		printf("%d ", intArray[i]);
		i++;
	}
	printf("\n");
	
	return 0;
}

void bubbleSort(int *aptr, int *sptr) {
	
	int i, j, temp;
	
		for(i = *sptr; i > 1; i--){
			for(j=0; j < *sptr-1; j++){
				if(aptr[j] > aptr[j+1]) {
					temp = aptr[j];
					aptr[j] = aptr[j+1];
					aptr[j+1] = temp;
				}
			}
		}
	
}
