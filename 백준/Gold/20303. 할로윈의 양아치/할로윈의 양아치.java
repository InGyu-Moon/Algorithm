import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n, m, k;
	static int[] candy, parent;
	static List<int[]> list;
	static int[][] dp;

	public static void knapsack() {
		int size = list.size();
		dp = new int[size][k];
		for (int i = 1; i < size; i++) {
			int w = list.get(i)[0];
			int v = list.get(i)[1];
			for (int j = 1; j < k; j++) {
				if (w <= j) {
					dp[i][j] = Math.max(dp[i - 1][j - w] + v, dp[i - 1][j]);
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}
		System.out.println(dp[size-1][k - 1]);
	}

	public static void sol() {
		boolean[] visited = new boolean[n + 1];
		list.add(new int[] { 0, 0 });
		for (int i = 1; i <= n; i++) {
			if (visited[i])
				continue;
			int num = parent[i];
			int sum = 0;
			int cnt = 0;
			for (int j = i; j <= n; j++) {
				if (num == parent[j]) {
					sum += candy[j];
					cnt++;
					visited[j] = true;
				}
			}
			list.add(new int[] { cnt, sum });
		}
	}

	public static void union(int a, int b) {
		int rootA = getRoot(a);
		int rootB = getRoot(b);
		parent[rootA] = rootB;
	}

	public static int getRoot(int a) {
		if (a == parent[a])
			return a;
		return parent[a] = getRoot(parent[a]);
	}

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		m = Integer.parseInt(token.nextToken());
		k = Integer.parseInt(token.nextToken());
		candy = new int[n + 1];
		token = new StringTokenizer(input.readLine());
		for (int i = 1; i <= n; i++) {
			candy[i] = Integer.parseInt(token.nextToken());
		}

		parent = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			parent[i] = i;
		}
		for (int i = 0; i < m; i++) {
			token = new StringTokenizer(input.readLine());
			int a = Integer.parseInt(token.nextToken());
			int b = Integer.parseInt(token.nextToken());
			union(a, b);
		}
		for (int i = 1; i <= n; i++) {
			parent[i] = getRoot(i);
		}
		list = new ArrayList<>();
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
		knapsack();
	}
}
