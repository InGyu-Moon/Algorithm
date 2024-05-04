#include<iostream>
#include<vector>
#include <tuple>
using namespace std;

int n, m;
int x, y;
int map[10][10] = {};
int map2[10][10] = {};
//int dx[4] = { 1,-1,0,0 };
//int dy[4] = { 0,0,1,-1 };
int dx[4] = { 1,0,-1,0 };
int dy[4] = { 0,1,0,-1 };
vector<pair<int, int>> cctv;
int mn = 0;

bool check(int a,int b) {
    return (a > n || b > m || a < 0 || b < 0);
}
/*
void sol(int a, int b, int dir) {
    dir %= 4;
    while (true) {
        int nx = a + dx[dir];
        int ny = b + dy[dir];
        if (check(a, b) || map2[nx][ny]) break;
        if (map2[nx][ny] != 0) continue;
        map2[nx][ny] = 7;
    }
}*/
void sol(int x, int y, int dir) {
    dir %= 4;
    while (1) {
        x += dx[dir];
        y += dy[dir];
        if (check(x, y) || map2[x][y] == 6) return; // 범위를 벗어났거나 벽을 만나면 함수를 탈출
        if (map2[x][y] != 0) continue; // 해당 칸이 빈칸이 아닐 경우(=cctv가 있을 경우) 넘어감
        map2[x][y] = 7; // 빈칸을 7로 덮음
    }
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> n >> m;

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            cin >> map[i][j];
            if (map[i][j] != 0 && map[i][j] != 6) cctv.push_back({ i,j });
            if (map[i][j] == 0) mn++;
        }
   
    }
    int totalNum = 1;
    for (int i = 1; i <= cctv.size(); i++) {
        totalNum *= 4;
    }
    for (int i = 0; i < totalNum; i++) {
        for (int a = 0; a < n; a++) {
            for (int b = 0; b < m; b++) {
                map2[a][b] = map[a][b];
            }
        }
        int brute = i;
        for (int j = 0; j < cctv.size(); j++) {
            int temp = brute % 4;
            brute /= 4;
            tie(x, y) = cctv[j];
            if (map[x][y] == 1) {
                sol(x, y, temp);
            }
            else if (map[x][y] == 2) {
                //sol(x, y, temp);
                //sol(x, y, temp + 1);
                sol(x, y, temp);
                sol(x, y, temp + 2);
            }
            else if (map[x][y] == 3) {
                //sol(x, y, temp);
                //sol(x, y, temp + 2);
                sol(x, y, temp);
                sol(x, y, temp + 1);
            }
            else if (map[x][y] == 4) {
                sol(x, y, temp);
                sol(x, y, temp + 1);
                sol(x, y, temp + 2);
            }
            else if (map[x][y] == 5) {
                sol(x, y, temp);
                sol(x, y, temp + 1);
                sol(x, y, temp + 2);
                sol(x, y, temp + 3);
            }
        }
        int cnt = 0;
        for (int a = 0; a < n; a++) {
            for (int b = 0; b < m; b++) {
                if (map2[a][b] == 0) cnt++;
            }
        }
        //cout << "mn: "  << mn << ", cnt: " << cnt << "\n";
        mn = min(mn, cnt);
    }
    cout << mn;

    return 0;
}