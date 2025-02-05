import java.io.*;
import java.util.Arrays;

public class Main {
    
    static int[] move;
    static int[][][] dp;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] line = br.readLine().split(" ");
        move = new int[line.length - 1]; // 마지막 0 제외
        for (int i = 0; i < line.length - 1; i++) {
            move[i] = Integer.parseInt(line[i]);
        }
        
        int N = move.length;
        dp = new int[5][5][N + 1];
        
        // DP 배열 초기화 (최대값으로 설정)
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Arrays.fill(dp[i][j], Integer.MAX_VALUE);
            }
        }
        
        // 초기 위치 설정
        dp[0][0][0] = 0;

        // DP 진행
        for (int c = 0; c < N; c++) { // 현재 몇 번째 움직임인지
            int next = move[c]; // 다음 이동할 위치
            
            for (int L = 0; L < 5; L++) { // 현재 왼발 위치
                for (int R = 0; R < 5; R++) { // 현재 오른발 위치
                    if (dp[L][R][c] == Integer.MAX_VALUE) continue; // 계산되지 않은 상태

                    // 1. 왼발을 이동시키는 경우
                    dp[next][R][c + 1] = Math.min(dp[next][R][c + 1], dp[L][R][c] + energy(L, next));

                    // 2. 오른발을 이동시키는 경우
                    dp[L][next][c + 1] = Math.min(dp[L][next][c + 1], dp[L][R][c] + energy(R, next));
                }
            }
        }

        // 최적의 최소값 찾기 (마지막 상태에서 가장 작은 에너지)
        int result = Integer.MAX_VALUE;
        for (int L = 0; L < 5; L++) {
            for (int R = 0; R < 5; R++) {
                result = Math.min(result, dp[L][R][N]);
            }
        }

        System.out.println(result);
    }
    
    static int energy(int pos, int des) {
        int num = Math.abs(pos - des);
        if (pos == 0) return 2; // 처음 밟는 경우
        else if (num == 0) return 1; // 같은 위치
        else if (num == 1 || num == 3) return 3; // 인접한 칸
        else return 4; // 반대 위치
    }
}