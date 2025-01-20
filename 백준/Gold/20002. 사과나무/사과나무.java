import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n;
	static int[][] arr, sum;

	public static void sol() {
		int ans = -1000;
		for (int size = 1; size <= n; size++) {
			for (int i = 1; i <= n + 1 - size; i++) {
				for (int j = 1; j <= n + 1 - size; j++) {
					int temp = sum[size + i - 1][size + j - 1] + sum[i - 1][j - 1]
							- (sum[i - 1][size + j - 1] + sum[size + i - 1][j - 1]);
					ans = Math.max(ans, temp);
				}
			}
		}
		System.out.println(ans);
	}

	public static void init() throws Exception {
		n = Integer.parseInt(input.readLine());
		arr = new int[n + 1][n + 1];
		sum = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			token = new StringTokenizer(input.readLine());
			for (int j = 1; j <= n; j++) {
				arr[i][j] = Integer.parseInt(token.nextToken());
				sum[i][j] = arr[i][j] + sum[i][j - 1] + sum[i - 1][j] - sum[i - 1][j - 1];
			}
		}
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
	}
}