#include <iostream>
#include <vector>
#include <map>
#include <algorithm>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, m, ans = 0;
    cin >> n >> m;

    vector<pair<int, int>> request(n);
    vector<pair<int, int>> bus(m);
    map<int, int> candidate;

    for (int i = 0; i < n; i++) {
        cin >> request[i].first >> request[i].second;
    }

    for (int i = 0; i < m; i++) {
        cin >> bus[i].first >> bus[i].second;
    }

    // 요청 및 버스를 정렬
    sort(request.begin(), request.end());
    sort(bus.begin(), bus.end());

    int r_idx = 0;
    for (int b_idx = 0; b_idx < m; b_idx++) {
        // 현재 버스의 정원보다 탑승 인원이 적거나 같은 배차 요청을 후보군에 추가
        while (r_idx < n && bus[b_idx].first >= request[r_idx].first) {
            candidate[request[r_idx].second]++;
            r_idx++;
        }

        // 버스의 도착 예정 시간 이상이면서 가장 가까운 최대 대기 가능 시간을 가진 배차 요청 찾기
        auto iter = candidate.lower_bound(bus[b_idx].second);
        if (iter != candidate.end()) {
            ans++;
            if (iter->second == 1) {
                candidate.erase(iter);
            } else {
                iter->second--;
            }
        }
    }

    cout << ans << '\n';
    return 0;
}