#include<iostream>
using namespace std;

int N[1000000];
int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    int n, b, c;
    cin >> n;
    for (int i = 0; i < n; i++) {
        cin >> N[i];
    }
    cin >> b >> c;

    long long sol = 0;
    sol += n;
    for (int i = 0; i < n; i++) {
        int temp = N[i] - b;
        if (temp > 0) {
            int cc = temp / c;
            int dd = temp % c;
            if (dd != 0) {
                sol++;
            }
            sol += cc;
        }
    }
    cout << sol << "\n";

    return 0;
}
