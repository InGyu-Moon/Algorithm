#include<iostream>
#include<algorithm>
#include<queue>
using namespace std;

int dx[4] = { 1,-1,0,0 };
int dy[4] = { 0,0,1,-1 };
int t;
int w, h;
char maze[1000][1000];
int visited[1000][1000] = {};
int fire[1000][1000] = {};
queue<pair<int, int>> f;
queue<pair<int, int>> q;

void bfs() {
   
    while (!q.empty()) {
        int x = q.front().first;
        int y = q.front().second;
        q.pop();
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            
            if (nx < 0 || ny < 0 || nx >= w || ny >= h) {
                cout << visited[x][y] << "\n";
                return;
            }
            if (maze[nx][ny] == '#' || maze[nx][ny] == '*') continue;
            
            if (visited[nx][ny]) continue;

            if (fire[nx][ny] != 0 && fire[nx][ny] <= visited[x][y] + 1) continue;
            
            visited[nx][ny] = visited[x][y] + 1;
            q.push({ nx,ny });
        }
    }
    cout << "IMPOSSIBLE\n";
    return;
}
int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> t;
    while (t--) {
        cin >> h >> w;

        while (!q.empty()) q.pop();
        while (!f.empty()) f.pop();
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                visited[i][j] = 0;
                fire[i][j] = 0;
            }
        }

        string str;
        for (int i = 0; i < w; i++) {
            cin >> str;
            for (int j = 0; j < h; j++) {
                maze[i][j] = str[j];
                if (str[j] == '*') {
                    f.push({ i,j });
                    fire[i][j] = 1;
                }
                if (str[j] == '@') {
                    q.push({ i,j });
                    visited[i][j] = 1;
                }
            }
        }

        while (!f.empty()) {
            int x = f.front().first;
            int y = f.front().second;
            f.pop();
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || ny < 0 || nx >= w || ny >= h) continue;
                if (maze[nx][ny] == '#') continue;
                if (fire[nx][ny]) continue; 
                fire[nx][ny] = fire[x][y] + 1;
                f.push({ nx,ny });
            }
        }

        bfs();

    }
    return 0;
}
