#include<iostream>
#include<vector>
#include<algorithm>

using namespace std;

int arr[10] = {};

int main() {

    ios::sync_with_stdio(0);
    cin.tie(0);

    int num;
    cin >> num;

    while (num > 0) {
        arr[num % 10]++;
        num /= 10;
    }
   
    arr[6] += arr[9];
    arr[6]++;
    arr[6] /= 2;

    int sol = *max_element(arr, arr+9);
    
    cout << sol << "\n";
    return 0;
}