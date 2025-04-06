import java.util.*;
import java.io.*;
class Solution {
    int[][] dp;

    public int solution(int[][] info, int n, int m) {
        dp = new int[info.length + 1][m];

        // 초기화
        for (int i = 1; i <= info.length; i++) {
            Arrays.fill(dp[i], n);
        }
        dp[0][0] = 0;

        // DP 수행
        for (int i = 1; i <= info.length; i++) {
            int a = info[i - 1][0];
            int b = info[i - 1][1];

            for (int j = 0; j < m; j++) {
                // A도둑이 물건을 훔치는 경우
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + a);

                // B도둑이 물건을 훔치는 경우
                if (j + b < m)
                    dp[i][j + b] = Math.min(dp[i][j + b], dp[i - 1][j]);
            }
        }

        // 정답 반환
        int answer = n;
        for (int j = 0; j < m; j++) {
            answer = Math.min(answer, dp[info.length][j]);
        }

        return answer >= n ? -1 : answer;
    }
}