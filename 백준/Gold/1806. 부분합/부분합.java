import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n, sum;
	static int[] arr;
	static int[] dp;

	public static void sol() {
		int min = Integer.MAX_VALUE;
		int s = 0;
		int e = 1;
		while (true) {
			int num = dp[e] - dp[s];
			if (num >= sum) {
				min = Math.min(min, e - s);
				s++;
			} else if (num < sum) {
				e++;
			}
			if (e == n + 1)
				break;
		}
		if (min == Integer.MAX_VALUE)
			output.append("0");
		else
			output.append(min);
	}

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		sum = Integer.parseInt(token.nextToken());
		arr = new int[n + 1];
		dp = new int[n + 1];
		token = new StringTokenizer(input.readLine());
		for (int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(token.nextToken());
		}
		dp[1] = arr[1];
		for (int i = 2; i <= n; i++) {
			dp[i] = dp[i - 1] + arr[i];
		}
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
		System.out.println(output);
	}
}
