#include<iostream>
#include<vector>
#include<algorithm>
#include<queue>

using namespace std;

int visited[301][301] = {};
int chess[301][301] = {};

int dx[8] = { -1,1,2,2,1,-1,-2,-2 };
int dy[8] = { -2,-2,-1,1,2,2,1,-1 };

int bfs(int l, int a1, int a2, int b1, int b2);
void makeClean();

int dis[301][301] = {};

int main() {

	ios::sync_with_stdio(0);
	cin.tie(0); cout.tie(0);

	int t;
	cin >> t;
	while (t--) {
		int l,a1, a2, b1, b2;
		cin >> l;
		cin >> a1 >> a2;

		cin >> b1 >> b2;
		cout << bfs(l, a1, a2, b1, b2) << "\n";
		makeClean();
	}



	return 0;
}
int bfs(int l, int a1, int a2, int b1, int b2) {

	queue<pair<int, int>> que;

	que.push(make_pair(a1, a2));
	visited[a1][a2] = true;
	while (!que.empty()) {
		int x = que.front().first;
		int y = que.front().second;
		que.pop();
		if (x == b1 && y == b2) {
			return dis[x][y];
		}
		for (int i = 0; i < 8; i++) {
			if (!visited[x + dx[i]][y + dy[i]] && x + dx[i] >= 0 && y + dy[i] >= 0 && x + dx[i] <= l-1 && y + dy[i] <= l-1) {
				que.push(make_pair(x + dx[i], y + dy[i]));
				visited[x + dx[i]][y + dy[i]] = true;
				dis[x + dx[i]][y + dy[i]] = dis[x][y] + 1;
			}
		}
	}

}
void makeClean() {
	for (int i = 0; i < 301; i++) {
		for (int j = 0; j < 301; j++) {
			visited[i][j] = 0;
			dis[i][j] = 0;
		}
	}
}