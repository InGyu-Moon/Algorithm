#include<iostream>
#include<stack>
#include <string>
using namespace std;

int main() {

    ios::sync_with_stdio(0);
    cin.tie(0);
    int t;
    cin >> t;

    int cnt = 0;
    while (t--){
        string str;
        stack<char> stk;
        cin >> str;


        for (int i = 0; i < str.size(); i++) {
            if (stk.empty())
                stk.push(str[i]);
            else {
                if (stk.top() == str[i])
                    stk.pop();
                else {
                    stk.push(str[i]);
                }
            }
        }

        if (stk.empty())
            cnt++;

    }
    cout << cnt << "\n";
  


    return 0;
}
