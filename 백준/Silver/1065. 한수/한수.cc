#include<iostream>
using namespace std;
int isHan(int input);
int main() {

	
	int input;
	int result = 0;
	cin >> input;
	for (int i = 1; i <= input; i++) {
		if (isHan(i))
			result++;
	}
	cout << result;
	return 0;
}

int isHan(int input) {
	int arr[4] = {};
	int idx = 0;
	while (input != 0) {
		arr[idx] = input % 10;
		input /= 10;
		idx++;
	}
	if (idx == 1) {
		return 1;
	}
	if (idx == 2) {
		return 1;
	}
	for (int i = 0; i < idx - 2; i++) {
		if ((arr[i] - arr[i + 1]) != (arr[i + 1] - arr[i + 2])) {
			return 0;
		}
	}
	return 1;
}