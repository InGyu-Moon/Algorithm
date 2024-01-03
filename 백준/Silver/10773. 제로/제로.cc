#include<iostream>
#include<vector>
#include<stack>

using namespace std;


int main() {

    ios::sync_with_stdio(0);
    cin.tie(0);

    int num;
    cin >> num;

    stack<int> stk;

    while (num--) {
        int input;
        cin >> input;
        if (input != 0)
            stk.push(input);
        else
            stk.pop();
    }
    int sum = 0;
    int size = stk.size();
    for (int i = 0; i < size; i++) {
        sum += stk.top();
        stk.pop();
    }
    cout << sum << "\n";
    

    
    return 0;
}