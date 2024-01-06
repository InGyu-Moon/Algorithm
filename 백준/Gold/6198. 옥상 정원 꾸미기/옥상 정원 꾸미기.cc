#include<iostream>
#include<vector>
#include<stack>

using namespace std;

vector<int> vec;
int main() {

    ios::sync_with_stdio(0);
    cin.tie(0);

    stack<int> stk;

    long long cnt = 0;

    int n;
    cin >> n;

    for (int i = 1; i <= n; i++) {
        long long height;
        cin >> height;

        while (!stk.empty()) {
            if (stk.top() > height) {
                cnt = cnt + stk.size();
                break;
            }
            else {
                stk.pop();
            }
        }
        stk.push(height);
        //cout << "[ "  << cnt << " ]";
    }

    cout << cnt;



    return 0;
}