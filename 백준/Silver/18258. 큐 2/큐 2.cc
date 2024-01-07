#include<iostream>
#include<vector>
#include<queue>

using namespace std;

vector<int> vec;
int main() {

    ios::sync_with_stdio(0);
    cin.tie(0);

    queue<int> que;

    int n;
    cin >> n;
     
    while (n--) {
        int temp;

        string str;
        cin >> str;

        if (str == "push") {
            cin >> temp;
            que.push(temp);
        }
        else if (str == "pop") {
            if (!que.empty()) {
                cout << que.front() << "\n";
                que.pop();
            }
            else {
                cout << -1 << "\n";
            }
        }
        else if (str == "size") {
            cout << que.size() << "\n";
        }
        else if (str == "empty") {
            if (!que.empty()) {
                cout << 0 << "\n";
            }
            else
                cout << 1 << "\n";
        }
        else if (str == "front") {
            if (!que.empty()) {
                cout << que.front() << "\n";
            }
            else {
                cout << -1 << "\n";
            }
        }
        else {
            if (!que.empty()) {
                cout << que.back() << "\n";
            }
            else {
                cout << -1 << "\n";
            }
        }
    }


    return 0;
}