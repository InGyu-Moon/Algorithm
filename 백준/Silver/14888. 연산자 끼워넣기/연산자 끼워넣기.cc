#include<iostream>
#include <vector>

using namespace std;

int mymin = 1000000001;
int mymax = -1000000001;
int n;
vector<int> vec(11);
vector<int> op(4);

void function(int num, int val) {


	if (num == n-1 ) {
		if (val > mymax)
			mymax = val;
		if (val < mymin)
			mymin = val;
		return;
	}


	for (int i = 0; i < 4; i++) {
		if (op[i] == 0)
			continue;
		op[i]--;
		if (i == 0) {
			function(num + 1, val + vec[num + 1]);
		}
		else if (i == 1) {
			function(num + 1, val - vec[num + 1]);
		}
		else if (i == 2) {
			function(num + 1, val * vec[num + 1]);
		}
		else if (i == 3) {
			function( num + 1, val / vec[num + 1]);
		}
		op[i]++;
	}
	return;
}




int main() {
	cin >> n;
	for (int i = 0; i < n; i++) {
		cin >> vec[i];
	}
	for (int i = 0; i < 4; i++) {
		cin >> op[i];
	}

	function(0, vec[0]);
	cout << mymax << '\n';
	cout << mymin;

	return 0;
}