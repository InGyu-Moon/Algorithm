import java.io.*;
import java.util.*;

public class Solution {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n, x, m;
	static Pair[] pairs;

	static int[] selected;

	static List<int[]> ans;

	public static boolean check(int[] selected) {
		for (Pair pair : pairs) {
			int sum = 0;
			for (int i = pair.l - 1; i < pair.r; i++) {
				sum += selected[i];
			}
			if (sum != pair.s) {
				return false;
			}
		}
		return true;
	}

	public static void dfs(int depth) {
		// 기저조건
		if (depth == n) {
			// 조건 확인
			if (check(selected)) {
//				ans.add(selected);
				ans.add(selected.clone());
			}
			return;
		}
		// 반복 조건
		for (int i = x; i >= 0; i--) {
			selected[n - 1 - depth] = i;
			dfs(depth + 1);
		}

	}

	public static void sol() {
		dfs(0);
		if (ans.size() == 0) {
			output.append("-1\n");
		} else {
//			Collections.sort(ans, (a, b) -> {
//				for (int i = 0; i < a.length; i++) {
//					return 1;
//				}
//				return 0;
//			});
			int[] temp = ans.get(0);
			for (int i = 0; i < n; i++) {
				output.append(temp[i]).append(" ");
			}
			output.append("\n");
		}
	}

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		x = Integer.parseInt(token.nextToken());
		m = Integer.parseInt(token.nextToken());
		pairs = new Pair[m];
		for (int i = 0; i < m; i++) {
			token = new StringTokenizer(input.readLine());
			int l = Integer.parseInt(token.nextToken());
			int r = Integer.parseInt(token.nextToken());
			int s = Integer.parseInt(token.nextToken());
			pairs[i] = new Pair(l, r, s);
		}

		selected = new int[n];
		ans = new ArrayList<int[]>();

	}

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(input.readLine());
		for (int t = 1; t <= T; t++) {
			output.append("#").append(t).append(" ");
			init();
			sol();
		}
		System.out.println(output);
	}

	static class Pair {
		int l;
		int r;
		int s;

		public Pair(int l, int r, int s) {
			this.l = l;
			this.r = r;
			this.s = s;
		}
	}
}