#include <iostream>
#include<vector>
#include<queue>
using namespace std;
#define INF 987654321

int N, E;
int dt1, dt2;
vector<pair<int,int>> vec[801];
int d[801] = {};
int cnt = 0;

void sol(int start) {
    for (int i = 1; i <= N; i++) {
        d[i] = INF;
    }
    d[start] = 0;
    priority_queue<pair<int, int>> pq;
    pq.push(make_pair(0, start));
    while (!pq.empty()) {
        int topVal = -pq.top().first;
        int topNode = pq.top().second;
        pq.pop();
        if (d[topNode] < topVal)
            continue;
        for (int i = 0; i < vec[topNode].size(); i++) {
            int targetNode = vec[topNode][i].second;
            int targetVal = vec[topNode][i].first;
            if (d[targetNode] > targetVal + topVal) {
                d[targetNode] = targetVal + topVal;
                pq.push(make_pair(-(targetVal + topVal), targetNode));
            }
        }
    }
}

int main() {
    
    cin >> N >> E;

    for (int i = 0; i < E; i++) {
        int a, b, c;
        cin >> a >> b >> c;
        vec[a].push_back(make_pair(c, b));
        vec[b].push_back(make_pair(c, a));

    }

    cin >> dt1 >> dt2;

    sol(1);
    int To_dt1 = d[dt1];
    int To_dt2 = d[dt2];

    sol(dt1);
    int dt1_To_dt2 = d[dt2];
    int dt1_To_n = d[N];

    sol(dt2);
    int dt2_To_n = d[N];

    if (To_dt1 >= INF || To_dt2 >= INF || dt1_To_dt2 >= INF || dt1_To_n >= INF || dt2_To_n >= INF) {
        cout << -1;
        return 0;
    }


    int result;

    result = min(INF, To_dt1 + dt1_To_dt2 + dt2_To_n);
    result = min(result, To_dt2 + dt1_To_dt2 + dt1_To_n);
    cout << result;

   
    return 0;
}