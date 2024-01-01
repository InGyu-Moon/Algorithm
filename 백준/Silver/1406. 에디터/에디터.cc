#include<iostream>
#include<vector>
#include<list>

using namespace std;


int main() {

    ios::sync_with_stdio(0);
    cin.tie(0);



    string str;
    int num;

    cin >> str;
    cin >> num;

    list<char> L;
    for (int i = 0; i < str.size(); i++) {
        L.push_back(str[i]);
    }
    auto t = L.end();


    for (int i = 0; i < num; i++) {
        char c;
        cin >> c;
        switch (c) {
        case 'L':
            if (t != L.begin())
                t--;
            break;
        case 'D':
            if (t != L.end())
                t++;
            break;

        case 'B':
            if (t != L.begin()) {
                t--;
                t = L.erase(t);
            }
            break;

        case 'P':
            char add;
            cin >> add;
            L.insert(t, add);
            break;

        }
    }

    for (auto a : L) {
        cout << a;
    }
   

    
    return 0;
}