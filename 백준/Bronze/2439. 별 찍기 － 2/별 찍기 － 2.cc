#include<iostream>
using namespace std;
int main() {

	ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);

	int input;
	cin >> input;

	for (int i = 0; i < input; i++) {
		for (int j = input-1; j > i; j--) {
			cout << " ";
		}
		for (int k = 0; k <= i; k++) {
			cout << "*";
		}
		cout << "\n";
	}
	return 0;
}
