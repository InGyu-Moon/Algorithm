#include<iostream>
#include<queue>

using namespace std;


int maze[101][101] = {};
bool visited[101][101] = {};
int dx[4] = { 0,0,1,-1 };
int dy[4] = { 1,-1,0,0 };
int si, sj;

int bfs(int a,int b) {
    queue<pair<int, int>> que;
    que.push({ a,b });
    visited[a][b] = true;
    while (!que.empty()) {
        int x = que.front().first;
        int y = que.front().second;
        que.pop();
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 100 || ny >= 100 || nx < 0 || ny < 0) continue;
            if (maze[nx][ny] == 1) continue;
            if (visited[nx][ny]) continue;
            if (maze[nx][ny] == 3) return 1;
            visited[nx][ny] = true;
            que.push({ nx,ny });
            
        }
    }
    return 0;
}
int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    int test;
    int ans;
    for (int t = 1; t <= 10; t++) {

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                visited[i][j] = false;
            }
        }

        cin >> test;
        for (int i = 0; i < 100; i++) {
            string str;
            cin >> str;
            for (int j = 0; j < 100; j++) {
                maze[i][j] = str[j] - '0';
                if (maze[i][j] == 2) {
                    si = i;
                    sj = j;
                }
            }
        }

        cout << "#" << test << " " << bfs(si, sj) << "\n";
    }
    

    return 0;
}