#include<iostream>
#include<vector>
#include<queue>

using namespace std;

queue<int> que;

int main() {

    ios::sync_with_stdio(0);
    cin.tie(0);

    

    int t;
    cin >> t;

    for (int i = 1; i <= t; i++) {
        que.push(i);
    }

    while (que.size() != 1) {
        que.pop();
        que.push(que.front());
        que.pop();
    }

    cout << que.front();
  


    return 0;
}
