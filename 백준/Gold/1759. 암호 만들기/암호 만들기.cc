#include<iostream>
#include<algorithm>
#include<vector>
using namespace std;

int main() {

    ios::sync_with_stdio(0);
    cin.tie(0);


    int l, c;
    
    char arr[15];
    int temp[15];
    cin >> l >> c;
    for (int i = 0; i < c; i++) {
        cin >> arr[i];
        temp[i] = 0;
    }
    sort(arr, arr + c);
    for (int i = l; i < c; i++) {
        temp[i] = 1;
    }
    do {
        int cnt1 = 0;
        int cnt2 = 0;
        for (int i = 0; i < c; i++) {
            if (temp[i] == 0) {
                if (arr[i] == 'a' || arr[i] == 'e' || arr[i] == 'i' || arr[i] == 'o' || arr[i] == 'u') {
                    cnt1++;
                }
                else {
                    cnt2++;
                }
            }
        }
        if (cnt1 == 0 || cnt2 == 0 || cnt2 == 1)
            continue;

        for (int i = 0; i < c; i++) {
            if (temp[i] == 0) {
                cout << arr[i];
            }
        }
        cout << "\n";
    } while (next_permutation(temp, temp +c));

    return 0;
}
