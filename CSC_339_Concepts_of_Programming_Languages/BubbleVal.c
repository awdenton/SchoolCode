//This is my own work: Aaron Denton

#include <stdio.h>

void bubbleSort(int arr[], int size);

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
	
	bubbleSort(intArray, arrSize);
	printf("BUBBLE SORTED:\n");
	
	i = 0;
	while(i < arrSize) {
		printf("%d ", intArray[i]);
		i++;
	}
	printf("\n");
	
	return 0;
}

void bubbleSort(int arr[], int size) {
	
	int j, temp;
	
		while(size > 1){
			for(j=0; j < size-1; j++){
				if(arr[j] > arr[j+1]) {
					temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
			size--;
		}
	
}
