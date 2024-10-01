import java.io.*;
import java.util.*;

public class Solution {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n, k;
	static int[] v, c;
	static int[][] arr;

	public static void sol() {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= k; j++) {
				if (j - v[i] >= 0) {
					int a = arr[i - 1][j];
					int b = arr[i - 1][j - v[i]] + c[i];
					arr[i][j] = Math.max(a, b);
				} else {
					arr[i][j] = arr[i - 1][j];
				}
			}
		}
		output.append(arr[n][k]).append("\n");
	}

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		k = Integer.parseInt(token.nextToken());
		v = new int[n + 1];
		c = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			token = new StringTokenizer(input.readLine());
			v[i] = Integer.parseInt(token.nextToken());
			c[i] = Integer.parseInt(token.nextToken());
		}
		arr = new int[n + 1][k + 1];
	}

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(input.readLine());
		for (int t = 1; t <= T; t++) {
			init();
			output.append("#").append(t).append(" ");
			sol();
		}
		System.out.println(output);
	}
}