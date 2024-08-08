#include <iostream>
#include<algorithm>
#include<queue>
#include<vector>
using namespace std;

int N;
int arr[21][21] = {};
int sx, sy;
int shark = 2, eatCnt = 0;
int dx[4] = { 0,-1,0,1 };
int dy[4] = { -1,0,1,0 };
int visited[21][21] = {};

int ans = 0;

queue<pair<int,pair<int, int>>> q;
vector<pair<int, pair<int, int>>>  vec;

bool compare(pair<int, pair<int, int>> a, pair<int, pair<int, int>> b) {
	if (a.first == b.first) {
		if (a.second.first == b.second.first) {
			return a.second.second < b.second.second;
		}
		return a.second.first < b.second.first;
	}
	return a.first < b.first;
}

void bfs(int sx, int sy);
void eat();
void reset();

int main() {

	cin >> N;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> arr[i][j];
			if (arr[i][j] == 9) {
				sx = i; sy = j;
				arr[i][j] = 0;
				visited[i][j] = 1;
			}
		}
	}

	// 로직 시작
	while (true) {
		bfs(sx,sy);
		if (vec.size() == 0) { //후보 먹이가 없으면 종료
			cout << ans;
			return 0;
		}
		else {
			sort(vec.begin(), vec.end(), compare); // 먹이 후보 정렬 (거리, 위, 좌)
			ans += vec[0].first; // 움직인 거리 추가
			sx = vec[0].second.first; // 상어 위치 조정
			sy = vec[0].second.second; // 상어 위치 조정
			eat(); // 먹이를 먹음
		}
		//후보 배열 초기화, visited 배열 초기화
		reset();
	}
	return 0;
}

void bfs(int sx, int sy) {
	q.push({ 0,{sx,sy} });
	while (!q.empty()) {
		int x = q.front().second.first;
		int y = q.front().second.second;
		int d = q.front().first;
		q.pop();
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx >= N || ny >= N || nx < 0 || ny < 0) continue;
			if (visited[nx][ny]) continue; // 방문 확인
			if (shark < arr[nx][ny]) continue; //상어, 먹이 크기 비교
			if (vec.size() != 0 && vec[0].first < d + 1) continue;
			if (arr[nx][ny] == 0 || shark == arr[nx][ny]) {
				q.push({ d + 1,{nx,ny} }); // que에 추가
				visited[nx][ny] = 1; // 방문 처리
			}
			else {
				vec.push_back({ d + 1,{nx,ny} }); // 먹이 후보 배열에 추가
				visited[nx][ny] = 1; //방문처리
			}
		}
	}
}
void eat() {
	arr[sx][sy] = 0; // 먹은곳 값을 0으로
	eatCnt++;
	if (shark == eatCnt) {
		shark++;
		eatCnt = 0;
	}
}

void reset() {
	vec.clear();
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			visited[i][j] = 0;
		}
	}
}
