import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n, m;
	static int[] arr, dp;
	static long cnt;
	static long ans;

	public static void sol() {
		Arrays.sort(dp);
		cnt = 1;
		for (int i = 1; i <= n; i++) {
			if (dp[i] == dp[i - 1]) {
				cnt++;
			} else {
//				System.out.println("cnt:" + cnt);
				ans += (cnt * (cnt - 1) / 2);
				cnt = 1;
			}
		}
		if (cnt != 1) {
//			System.out.println("cnt:" + cnt);
			ans += (cnt * (cnt - 1) / 2);
		}
	}

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		m = Integer.parseInt(token.nextToken());
		arr = new int[n + 1];
		dp = new int[n + 1];
		token = new StringTokenizer(input.readLine());
		for (int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(token.nextToken()) % m;
			dp[i] = (dp[i - 1] + arr[i]) % m;
		}
		ans = 0;
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
		System.out.println(ans);
	}

}