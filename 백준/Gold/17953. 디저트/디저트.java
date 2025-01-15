import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n, m;

	static int[][] arr, dp;

	public static void print() {
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void sol() {

		for (int j = 0; j < m; j++) {
			dp[j][0] = arr[j][0];
		}

		for (int i = 1; i < n; i++) {
			for (int j = 0; j < m; j++) {
				for (int k = 0; k < m; k++) {
					if (j == k) {
//						System.out.print(dp[j][i] + " " + dp[k][i - 1] + " " + (arr[j][i] / 2));
						dp[j][i] = Math.max(dp[j][i], dp[k][i - 1] + (arr[j][i] / 2));
//						System.out.println(" -> "+dp[j][i]);
					} else {
//						System.out.print(dp[j][i] + " " + dp[k][i - 1] + " " + (arr[j][i]));
						dp[j][i] = Math.max(dp[j][i], dp[k][i - 1] + arr[j][i]);
//						System.out.println(" -> "+dp[j][i]);
					}

				}
			}
		}
		int temp = 0;
		for (int i = 0; i < m; i++) {
			temp = Math.max(temp, dp[i][n - 1]);
		}
		System.out.println(temp);
	}

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		m = Integer.parseInt(token.nextToken());

		arr = new int[m][n];
		dp = new int[m][n];

		for (int i = 0; i < m; i++) {
			token = new StringTokenizer(input.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(token.nextToken());
			}
		}

	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
//		print();
	}
}