import java.io.*;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n, m;
	static int[][] arr;
	static int[] memory;
	static int[] cost;
	static int sum = 0;
	static int ans = Integer.MAX_VALUE;

	public static void sol() {
		
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j <= sum; j++) {
				if (j - cost[i] >= 0) {
					int a = arr[i - 1][j];
					int b = memory[i] + arr[i - 1][j - cost[i]];
					arr[i][j] = Math.max(a, b);
				} else {
					arr[i][j] = arr[i - 1][j];
				}
				if (arr[i][j] >= m) {
					ans = ans > j ? j : ans;
					break;
				}

			}
		}
	}

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		m = Integer.parseInt(token.nextToken());

		memory = new int[n + 1];
		token = new StringTokenizer(input.readLine());
		for (int i = 1; i <= n; i++) {
			memory[i] = Integer.parseInt(token.nextToken());
		}
		cost = new int[n + 1];
		token = new StringTokenizer(input.readLine());
		for (int i = 1; i <= n; i++) {
			cost[i] = Integer.parseInt(token.nextToken());
			sum += cost[i];
		}
		arr = new int[n + 1][sum + 1];

	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
		System.out.println(ans);

	}
}