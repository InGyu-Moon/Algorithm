#include<iostream>
#include<vector>
#include<algorithm>
#include<queue>

using namespace std;

int house[25][25] = {};
int visited[25][25] = {};
int totalHouse = 0;
vector<int> result;

void bfs(int i,int j);

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0); cout.tie(0);

	int n;
	cin >> n;
	for (int i = 0; i < n; i++) {
		string temp;
		cin >> temp;
		for (int j = 0; j < n; j++) {
			house[i][j] = temp[j] - '0';
		}
	}


	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (house[i][j] && !visited[i][j]) {

				bfs(i, j);
			}
		}
	}


	sort(result.begin(), result.end());
	
	cout << totalHouse << "\n";
	for (int i = 0; i < totalHouse; i++) {
		cout << result[i] << "\n";
	}


	return 0;
}

void bfs(int i, int j) {
	queue<pair<int, int>> que;
	que.push(make_pair(i, j));
	visited[i][j] = true;
	result.push_back(0);
	while (!que.empty()) {
		int a = que.front().first;
		int b = que.front().second;
		que.pop();
		result[totalHouse]++;
		//상하좌우 확인
		if (a + 1 <= 24 && house[a + 1][b] && !visited[a + 1][b]) {
			que.push(make_pair(a + 1, b));
			visited[a + 1][b] = true;
		}
		if (a - 1 >= 0 && house[a - 1][b] && !visited[a - 1][b]) {
			que.push(make_pair(a - 1, b));
			visited[a - 1][b] = true;
		}
		if (b + 1 <= 24 && house[a][b + 1] && !visited[a][b + 1]) {
			que.push(make_pair(a, b + 1));
			visited[a][b + 1] = true;
		}
		if (b - 1 >= 0 && house[a][b - 1] && !visited[a][b - 1]) {
			que.push(make_pair(a, b - 1));
			visited[a][b - 1] = true;
		}
	}
	totalHouse++;
}

/*
void bfs(int n) {
	
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (house[i][j] && !visited[i][j]) {
				queue<pair<int,int>> que;
				que.push(make_pair(i, j));
				visited[i][j] = true;
				while (!que.empty()) {
					int a = que.front().first;
					int b = que.front().second;
					que.pop();
					result[totalHouse]++;
					//상하좌우 확인
					if (a+1<=24 && house[a + 1][b] && !visited[a + 1][b]) {
						que.push(make_pair(a + 1, b));
						visited[a + 1][b] = true;
					}
					if (a-1>=0 && house[a - 1][b] && !visited[a - 1][b]) {
						que.push(make_pair(a - 1, b));
						visited[a - 1][b] = true;
					}
					if (b+1<=24 && house[a][b + 1] && !visited[a][b + 1]) {
						que.push(make_pair(a, b + 1));
						visited[a][b + 1] = true;
					}
					if (b-1>=0 && house[a][b - 1] && !visited[a][b - 1]) {
						que.push(make_pair(a, b - 1));
						visited[a][b - 1] = true;
					}
				}
				totalHouse++;
			}
		}
	}
	
}*/