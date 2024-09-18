import java.util.*;
import java.io.*;

// 정수 삼각형 1932
class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n;
	static int[][] arr;
	static int[][] dp;

	public static void sol() {
		dp[0][0] = arr[0][0];
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j <= i; j++) {
				dp[i + 1][j] = Math.max(dp[i + 1][j], arr[i + 1][j] + dp[i][j]);
				dp[i + 1][j + 1] = Math.max(dp[i + 1][j + 1], arr[i + 1][j + 1] + dp[i][j]);
			}
		}
	}

	public static void init() throws Exception {
		n = Integer.parseInt(input.readLine());
		arr = new int[n][n];
		dp = new int[n][n];
		for (int i = 0; i < n; i++) {
			token = new StringTokenizer(input.readLine());
			for (int j = 0; j <= i; j++) {
				arr[i][j] = Integer.parseInt(token.nextToken());
			}
		}
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
		Arrays.sort(dp[n - 1]);
		System.out.println(dp[n - 1][n - 1]);
	}
}