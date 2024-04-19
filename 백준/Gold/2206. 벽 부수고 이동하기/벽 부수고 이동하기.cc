#include <iostream>
#include<algorithm>
#include<queue>
#include<tuple>

using namespace std;
int n, m;
int dx[4] = { 1,-1,0,0 };
int dy[4] = { 0,0,1,-1 };
int map[1000][1000];
int visited[1000][1000][2] = {};
void bfs() {
	queue<tuple<int, int, int>> q;
	q.push({ 0,0,0 });
	visited[0][0][0] = 1;
	while (!q.empty()) {
		int a, b, isBroken;
		tie(a, b, isBroken) = q.front();
		if (a == n - 1 and b == m - 1) {
			cout << visited[a][b][isBroken] << "\n";
			return;
		}
		q.pop();
		for (int i = 0; i < 4; i++) {
			int x = a + dx[i];
			int y = b + dy[i];
			if (x < 0 or b < 0 or x >= n or y >= m) continue;
			if (map[x][y] == 1) { // 벽을 만남
				if (!isBroken && !visited[x][y][!isBroken]) { // 아직 벽을 부순적이 없으면 
					visited[x][y][1] = visited[a][b][0] + 1;
					q.push({ x,y,1 });
				}
			}
			else if (map[x][y] == 0) { // 벽이 아니면
				if (!visited[x][y][isBroken]) { // 해당 코드 추가
					visited[x][y][isBroken] = visited[a][b][isBroken] + 1;
					q.push({ x,y,isBroken });
				}
			}
		}
	}

	cout << "-1\n";
}

int main() {
	
	cin >> n >> m;
	string str;
	for (int i = 0; i < n; i++) {
		cin >> str;
		for (int j = 0; j < m; j++) {
			map[i][j] = str[j] - '0';
		}
	}

	bfs();
	
	return 0;
}

