#include<iostream>
#include<vector>
#include<algorithm>
#include<string>

using namespace std;

int main() {

	ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);

	int x, y, w, h;
	
	cin >> x >> y >> w >> h;

	int a = x < w - x ? x : w - x;
	int b = y < h - y ? y : h - y;

	int c = a > b ? b : a;

	cout << c << "\n";

	return 0;
}
