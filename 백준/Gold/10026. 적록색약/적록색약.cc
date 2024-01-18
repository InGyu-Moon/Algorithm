#include<iostream>
#include<queue>
using namespace std;

char arr[101][101] = {};
bool visited[101][101] = {};
queue<pair<int, int>> que;
int dx[4] = { 0,0,1,-1 };
int dy[4] = { 1,-1,0,0 };

int main() {

    ios::sync_with_stdio(0);
    cin.tie(0);


    int n;
    cin >> n;

    for (int i = 0; i < n; i++) {
        string str;
        cin >> str;
        for (int j = 0; j < n; j++) {
            arr[i][j] = str[j];
        }
    }

    
    int cnt1 = 0;
    int cnt2 = 0;

    for (int k = 0; k < n; k++) {
        for (int l = 0; l < n; l++) {
            if (visited[k][l])
                continue;


            que.push({ k,l });
            visited[k][l] = true;

            while (!que.empty()) {
                int a = que.front().first;
                int b = que.front().second;
                que.pop();
                for (int i = 0; i < 4; i++) {
                    int x = a + dx[i];
                    int y = b + dy[i];

                    if (visited[x][y] || x < 0 || y < 0 || x > n || y > n)
                        continue;

                    if (arr[a][b] == arr[x][y]) {
                        que.push({ x,y });
                        visited[x][y] = true;
                    }
                }
            }
            cnt1++;
        }
    }

    cout << cnt1 << " ";




    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            visited[i][j] = false;
            if (arr[i][j] == 'G')
                arr[i][j] = 'R';
        }
    }

    for (int k = 0; k < n; k++) {
        for (int l = 0; l < n; l++) {
            if (visited[k][l])
                continue;


            que.push({ k,l });
            visited[k][l] = true;

            while (!que.empty()) {
                int a = que.front().first;
                int b = que.front().second;
                que.pop();
                for (int i = 0; i < 4; i++) {
                    int x = a + dx[i];
                    int y = b + dy[i];

                    if (visited[x][y] || x < 0 || y < 0 || x > n || y > n)
                        continue;

                    if (arr[a][b] == arr[x][y]) {
                        que.push({ x,y });
                        visited[x][y] = true;
                    }
                }
            }
            cnt2++;
        }
    }
    cout << cnt2 << "\n";




    return 0;
}
