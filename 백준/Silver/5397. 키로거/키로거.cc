#include<iostream>
#include<vector>
#include<list>

using namespace std;


int main() {

    ios::sync_with_stdio(0);
    cin.tie(0);

    int test;
    cin >> test;
    while (test--) {
        list<char> L;
        auto curr = L.begin();
        string str;
        cin >> str;
        for (int i = 0; i < str.size(); i++) {
            char c = str[i];
            if (c == '<') {
                if (curr != L.begin())
                    curr--;
            }
            else if (c == '>') {
                if (curr != L.end())
                    curr++;
            }
            else if (c == '-') {
                if (curr != L.begin()) {
                    curr--;
                    curr = L.erase(curr);
                }
            }
            else {
                L.insert(curr, c);
            }
        }
        for (auto i : L) {
            cout << i;
        }
        cout << "\n";
    }

    

    
    return 0;
}