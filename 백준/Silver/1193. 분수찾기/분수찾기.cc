#include<iostream>
using namespace std;
int main() {

	ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);

	int num;
	cin >> num;

	int i = 1, val = num;

	while (val > 0) {
		val -= i;
		i++;
	}
	i--;
	val += i;

	int up = 0, down = i;

	if (i % 2 == 0) {
		for (int j = 1; j < val; j++) {
			down--;
		}
		up = i + 1 - down;
	}
	else {
		down = val;
		up = i + 1 - down;
	}

	cout << up << "/" << down;

    return 0;
}
