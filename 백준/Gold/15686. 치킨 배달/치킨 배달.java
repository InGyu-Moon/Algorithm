import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n, m;
	static int[][] arr;
	static List<int[]> chicken = new ArrayList<>();
	static int cnt;
	static boolean[] selected;
	static int select = 0;
	static int min = Integer.MAX_VALUE;

	public static int sol() {
		int sum = 0;
		List<int[]> list = new ArrayList<>();
		for (int i = 0; i < cnt; i++) {
			if (selected[i]) {
				list.add(chicken.get(i));
			}
		}
		boolean flag = true;
		if (list.size() == 0)
			flag = false;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				int min = Integer.MAX_VALUE;
				if (arr[i][j] != 1)
					continue;
				for (int[] curr : list) {
					int y = curr[0];
					int x = curr[1];
					int diff = Math.abs(y - i) + Math.abs(x - j);
					min = Math.min(min, diff);
				}
				sum += min;
			}
		}
		if (!flag)
			return -1;
		return sum;
	}

	public static void subset(int depth) {
		if (select > m)
			return;

		if (depth == cnt) {
			if (select != m)
				return;
			int diff = sol();
			if (diff != -1) {
				min = Math.min(min, diff);
			}
			return;
		}
		selected[depth] = false;
		subset(depth + 1);
		selected[depth] = true;
		select++;
		subset(depth + 1);
		select--;
	}

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		m = Integer.parseInt(token.nextToken());
		arr = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			token = new StringTokenizer(input.readLine());
			for (int j = 1; j <= n; j++) {
				arr[i][j] = Integer.parseInt(token.nextToken());
				if (arr[i][j] == 2) {
					chicken.add(new int[] { i, j });
				}
			}
		}
		cnt = chicken.size();
		selected = new boolean[cnt];
	}

	public static void main(String[] args) throws Exception {
		init();
		subset(0);
		System.out.println(min);
	}
}
