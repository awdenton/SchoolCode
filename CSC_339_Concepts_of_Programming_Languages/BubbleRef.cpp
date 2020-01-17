//This is my own work: Aaron Denton

#include <iostream>

using namespace std;
void bubbleSort(int *aptr, int *sptr);

int main(){
	int intArray[20];
	int arrSize;
	
	cout << "Input the size of the array\n";
	cin >> arrSize;
	
	cout << "Input " << arrSize << " integer values\n";
	for(int i = 0; i < arrSize; i++){
		cin >> intArray[i];
	}
	
	int *arrPtr = intArray;
	int *sizePtr = &arrSize;
	
	bubbleSort(arrPtr, sizePtr);
	
	cout << "BUBBLE SORTED:\n";
	for(int i = 0; i < arrSize; i++){
		cout << intArray[i] << " ";
	}
	cout << "\n";
	
	return 0;
}

void bubbleSort(int *aptr, int *sptr){
	
	int temp;
	
	for(int i = *sptr; i > 1; i--){
		for(int j = 0; j < *sptr-1; j++){
			if(aptr[j] > aptr[j+1]) {
				temp = aptr[j];
				aptr[j] = aptr[j+1];
				aptr[j+1] = temp;
			}
		}
	}
	
}