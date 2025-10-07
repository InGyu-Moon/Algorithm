class Solution {

    public int solution(int n, int w, int num) {
        int rows = (n + w - 1) / w;     // 올바른 행 수 (ceil)
        int[][] arr = new int[rows][w];

        int cnt = 1;
        boolean ltr = true;             // left-to-right 여부

        for (int i = 0; i < rows && cnt <= n; i++) {
            if (ltr) {
                for (int j = 0; j < w && cnt <= n; j++) {
                    arr[i][j] = cnt++;
                }
            } else {
                for (int j = w - 1; j >= 0 && cnt <= n; j--) {
                    arr[i][j] = cnt++;
                }
            }
            ltr = !ltr;
        }

        // num 위치 탐색
        int r0 = -1, c0 = -1;
        outer:
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < w; j++) {
                if (arr[i][j] == num) { r0 = i; c0 = j; break outer; }
            }
        }
        if (r0 == -1) return 0;         // 못 찾으면 0 (원래 로직 유지 원하면 1로 바꾸세요)

        // 아래로 연속 개수 카운트 (자기 자신 포함)
        int answer = 1;
        int r = r0 + 1;
        while (r < rows && arr[r][c0] != 0) {
            answer++;
            r++;
        }
        return answer;
    }
}
