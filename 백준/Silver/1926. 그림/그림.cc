#include <iostream>
#include<algorithm>
#include<queue>

using namespace std;
int n, m;
int arr[500][500];
int mx = 0;
int cnt = 0;
int dx[4] = { 1,-1,0,0 };
int dy[4] = { 0,0,1,-1 };
bool visited[500][500] = {};
void bfs(int aa,int bb) {
	int temp = 0;
	queue<pair<int,int>> q;
	q.push({ aa,bb });
	visited[aa][bb] = true;
	temp++;
	while (!q.empty()) {
		int a = q.front().first;
		int b = q.front().second;
		q.pop();
		for (int i = 0; i < 4; i++) {
			int x = a + dx[i];
			int y = b + dy[i];
			if (x < 0 or y < 0 or x >= n or y >= m) continue;
			if (visited[x][y]) continue;
			if (arr[x][y] == 0) continue;
			q.push({ x,y });
			visited[x][y] = true;
			temp++;
		}
	}
	mx = max(mx, temp);
}

int main() {
	

	cin >> n >> m;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cin >> arr[i][j];
		}
	}

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if (visited[i][j]) continue;
			if (arr[i][j] == 0) continue;
			bfs(i, j);
			cnt++;
		}
	}
	cout << cnt << "\n" << mx;
	return 0;
}

