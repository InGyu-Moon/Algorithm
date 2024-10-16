import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n;
	static int[] arr, dp;

	public static void init() throws Exception {
		n = Integer.parseInt(input.readLine());
		arr = new int[n];
		dp = new int[n];
		token = new StringTokenizer(input.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(token.nextToken());
		}
	}

	public static void sol() {
		dp[0] = arr[0];
		for (int i = 0; i < n; i++) {
			dp[i] = arr[i];
			for (int j = 0; j < i; j++) {
				if (arr[i] > arr[j]) {
					dp[i] = Math.max(dp[i], dp[j] + arr[i]);
				}
			}
		}
		Arrays.sort(dp);
		output.append(dp[n - 1]);
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
		System.out.println(output);
	}

}