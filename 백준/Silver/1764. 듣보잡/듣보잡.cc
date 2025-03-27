#include<iostream>
#include<vector>
#include<algorithm>

using namespace std;

int main() {

	ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);

	int N, M;
	cin >> N >> M;
	vector<string> vecD(N);
	vector<string> vecB(M);
	for (int i = 0; i < N; i++) {
		cin >> vecD[i];
	}
	for (int i = 0; i < M; i++) {
		cin >> vecB[i];
	}

	sort(vecD.begin(), vecD.end());
	sort(vecB.begin(), vecB.end());

	vector<string> vec;

	if (vecD.size() >= vecB.size()) {
		for (int i = 0; i < vecB.size(); i++) {
			if (binary_search(vecD.begin(), vecD.end(), vecB[i])) {
				vec.push_back(vecB[i]);
			}
		}
	}
	else {
		for (int i = 0; i < vecD.size(); i++) {
			if (binary_search(vecB.begin(), vecB.end(), vecD[i])) {
				vec.push_back(vecD[i]);
			}
		}
	}
	vec.erase(unique(vec.begin(), vec.end()), vec.end());
	cout << vec.size() << "\n";
	for (int i = 0; i < vec.size(); i++) {
		cout << vec[i] << "\n";
	}

	return 0;
}
