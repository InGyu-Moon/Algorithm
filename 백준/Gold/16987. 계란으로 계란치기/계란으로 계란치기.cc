#include <iostream>
#include<algorithm>

using namespace std;

int n;
int s[10], w[10];
int mx = 0, cnt = 0;

void solve(int a) {
	if (a == n) {
		mx = max(mx, cnt);
		return;
	}
	if (s[a] <= 0 or cnt == n - 1) {
		solve(a + 1);
		return;
	}

	for (int i = 0; i < n; i++) {
		if (i == a || s[i] <= 0) continue;
		s[a] -= w[i];
		s[i] -= w[a];
		if (s[i] <= 0) cnt++;
		if (s[a] <= 0) cnt++;
		solve(a + 1);
		if (s[a] <= 0) cnt--;
		if (s[i] <= 0) cnt--;
		s[a] += w[i];
		s[i] += w[a];
	}
}


int main() {
	cin >> n;
	for (int i = 0; i < n; i++) {
		cin >> s[i] >> w[i];
	}
	solve(0);
	cout << mx;
	return 0;
}

