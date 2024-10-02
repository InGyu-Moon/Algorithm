import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n;
	static int[] arr;
	static int[] dp;
	static int ans;

	public static void sol() {
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < i; j++) {
				if (arr[i] > arr[j]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
				ans = ans > dp[i] ? ans : dp[i];
			}
		}
	}

	public static void init() throws Exception {
		n = Integer.parseInt(input.readLine());
		arr = new int[n + 1];
		token = new StringTokenizer(input.readLine());
		for (int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(token.nextToken());
		}
		dp = new int[n + 1];
		ans = Integer.MIN_VALUE;
	}

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(input.readLine());
		for (int t = 1; t <= T; t++) {
			init();
			sol();
			output.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.println(output);
	}

}