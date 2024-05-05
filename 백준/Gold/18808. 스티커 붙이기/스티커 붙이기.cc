#include <bits/stdc++.h>
using namespace std;
int n, m, k;
int r, c;
int note[41][41];
int sticker[11][11];

void rotate() {
    int st[11][11];
    for (int i = 0; i < r; i++)
        for (int j = 0; j < c; j++)
            st[i][j] = sticker[i][j];
    for (int i = 0; i < c; i++) {
        for (int j = 0; j < r; j++) {
            sticker[i][j] = st[r-1-j][i];
        }
    }
    swap(r, c);
}

bool pos(int x, int y) {
    for (int i = 0; i < r; i++) {
        for (int j = 0; j < c; j++) {
            if (note[x + i][y + j] == 1 && sticker[i][j] == 1)
                return false;
        }
    }
    for (int i = 0; i < r; i++) {
        for (int j = 0; j < c; j++) {
            if (sticker[i][j] == 1)
                note[x + i][y + j] = 1;
        }
    }
    return true;
}

int main() {
    cin >> n >> m >> k;
    for (int l = 0; l < k; l++) {
        cin >> r >> c;
        for (int  i= 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                cin >> sticker[i][j];
            }
        }

        for (int dir = 0; dir < 4; dir++) {
            bool flag = false;
            for (int i = 0; i <= n-r; i++) {
                if (flag) break;
                for (int j = 0; j <= m-c; j++) {
                    if (pos(i, j)) {
                        flag = true;
                        break;
                    }
                }
            }
            if (flag) break;
            rotate();
        }
    }
    int ans = 0;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            ans += note[i][j];
        }
    }
    cout << ans;
}