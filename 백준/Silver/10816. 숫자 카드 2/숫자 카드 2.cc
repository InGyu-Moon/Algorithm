#include<iostream>
#include<vector>
#include<algorithm>

using namespace std;

int main() {

	ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);

	int N, M;

	cin >> N;

	vector<int> vec1(N);
	for (int i = 0; i < N; i++) {
		cin >> vec1[i];
	}

	cin >> M;
	vector<int> vec2(M);
	for (int i = 0; i < M; i++) {
		cin >> vec2[i];
	}

	sort(vec1.begin(), vec1.end());

	for (int i = 0; i < M; i++) {
		cout << upper_bound(vec1.begin(), vec1.end(), vec2[i]) - lower_bound(vec1.begin(), vec1.end(), vec2[i]) << " ";
	}







	return 0;
}
