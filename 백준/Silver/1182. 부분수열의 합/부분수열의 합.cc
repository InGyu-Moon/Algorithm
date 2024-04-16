#include <iostream>

using namespace std;

int N, S;
int arr[21];
int isUsed[21] = {};
int cnt = 0;

void func(int k,int sum);

int main() {
	
	cin >> N >> S;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}
	func(0, 0);
	if (S == 0)
		cnt--;
	cout << cnt;

}
void func(int curr,int tot) {
	if (curr == N) {
		if (tot == S) {
			cnt++;
		}
		return;
	}
	func(curr + 1, tot);
	func(curr + 1, tot + arr[curr]);
}