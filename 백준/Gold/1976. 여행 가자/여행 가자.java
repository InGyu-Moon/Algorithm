import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n, m;
	static int[][] arr;
	static int[] list;
	static int[] root;

	public static int findRoot(int a) {
		if (a == root[a]) {
			return a;
		}
		return root[a] = findRoot(root[a]);
	}

	public static void union(int a, int b) {
		int aRoot = findRoot(a);
		int bRoot = findRoot(b);

		if (aRoot != bRoot) {
			root[bRoot] = aRoot;
		}
	}

	public static boolean check(int a, int b) {
		int aRoot = findRoot(a);
		int bRoot = findRoot(b);
		if (aRoot == bRoot)
			return true;
		return false;
	}

	public static void sol() {
		for (int i = 0; i < m - 1; i++) {
			if (check(list[i], list[i + 1])) {
				continue;
			} else {
				output.append("NO\n");
				return;
			}
		}
		output.append("YES\n");
	}

	public static void init() throws Exception {
		n = Integer.parseInt(input.readLine());
		m = Integer.parseInt(input.readLine());

		root = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			root[i] = i;
		}

		arr = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			token = new StringTokenizer(input.readLine());
			for (int j = 1; j <= n; j++) {
				int num = Integer.parseInt(token.nextToken());
				if (num != 1)
					continue;
				// i와 j의 부모가 같다.
//				root[j] = root[i];
				union(i, j);

			}
		}

		token = new StringTokenizer(input.readLine());
		list = new int[m + 1];
		for (int i = 0; i < m; i++) {
			list[i] = Integer.parseInt(token.nextToken());
		}
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
		System.out.println(output);
	}

}