//This is my own work: Aaron Denton

#include <iostream>

using namespace std;
void bubbleSort(int arr[], int size);

int main(){
	int intArray[20];
	int arrSize;
	
	cout << "Input the size of the array\n";
	cin >> arrSize;
	
	cout << "Input " << arrSize << " integer values\n";
	for(int i = 0; i < arrSize; i++){
		cin >> intArray[i];
	}
	
	bubbleSort(intArray, arrSize);
	
	cout << "BUBBLE SORTED:\n";
	for(int i = 0; i < arrSize; i++){
		cout << intArray[i] << " ";
	}
	cout << "\n";
	
	return 0;
}

void bubbleSort(int arr[], int size){
	
	int temp;
	
	for(int i = size; i > 1; i--){
		for(int j = 0; j < size-1; j++){
			if(arr[j] > arr[j+1]){
					temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
			}
		}
	}
	
}