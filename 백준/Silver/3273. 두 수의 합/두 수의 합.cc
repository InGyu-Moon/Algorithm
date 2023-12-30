#include<iostream>
#include<vector>
#include<algorithm>

using namespace std;

bool arr[2000001] = {};

int main() {

    ios::sync_with_stdio(0);
    cin.tie(0);

    int cnt = 0;

    int n;
    cin >> n;

    for (int i = 0; i < n; i++) {
        int temp;
        cin >> temp;
        arr[temp] = true;
    }

    int x;
    cin >> x;
    for (int i = 1; i < x + 1; i++) {
        if (arr[i]){
            if (arr[x - i]) {
                cnt++;
            }
        }
    }

    cout << cnt / 2;

    
    return 0;
}