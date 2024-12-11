import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int[][] arr;
	static boolean[] visited;

	static int ans, sum;

	public static void dfs(int depth) {
		if (depth == 11) {
			ans = Math.max(ans, sum);
			return;
		}

		for (int i = 0; i < 11; i++) {
			if (arr[i][depth] == 0 || visited[i])
				continue;
			sum += arr[i][depth];
			visited[i] = true;
			dfs(depth + 1);
			sum -= arr[i][depth];
			visited[i] = false;
		}
	}

	public static void init() throws Exception {
		ans = sum = 0;
		arr = new int[11][11];
		visited = new boolean[11];
		for (int i = 0; i < 11; i++) {
			token = new StringTokenizer(input.readLine());
			for (int j = 0; j < 11; j++) {
				arr[i][j] = Integer.parseInt(token.nextToken());
			}
		}
	}

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(input.readLine());
		for (int t = 0; t < T; t++) {
			init();
			dfs(0);
			output.append(ans).append("\n");
		}
		System.out.println(output);
	}
}