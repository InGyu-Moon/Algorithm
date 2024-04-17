#include<iostream>
#include<algorithm>
#include<vector>
using namespace std;

int main() {

    ios::sync_with_stdio(0);
    cin.tie(0);

    int k;
    int s[13];
    while (true) {
        cin >> k;
        if (k == 0)
            break;
        for (int i = 0; i < k; i++) {
            cin >> s[i];
        }
        vector<int> arr(6, 0);
        for (int i = 0; i < (k - 6); i++) {
            arr.push_back(1);
        }
        do {
            for (int i = 0; i < k; i++) {
                if (arr[i] == 0) {
                    cout << s[i] << " ";
                }
            }
            cout << "\n";   
        } while (next_permutation(arr.begin(),arr.end()));
        cout << "\n";
    }


    return 0;
}
