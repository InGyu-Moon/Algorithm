import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n, ans;
	static int[][] arr;
	static boolean[] selected;

	public static void subset(int idx) {
		if (idx == n) {
			int s = 1, b = 0;
			boolean flag = false;
			for (int i = 0; i < n; i++) {
				if (selected[i]) {
					flag = true;
					s *= arr[i][0];
					b += arr[i][1];
				}
			}
			if(flag)
				ans = Math.min(ans, Math.abs(s - b));
			return;
		}

		selected[idx] = true;
		subset(idx + 1);
		selected[idx] = false;
		subset(idx + 1);

	}

	public static void init() throws Exception {
		n = Integer.parseInt(input.readLine());
		arr = new int[n][2];
		selected = new boolean[n];
		for (int i = 0; i < n; i++) {
			token = new StringTokenizer(input.readLine());
			arr[i][0] = Integer.parseInt(token.nextToken());
			arr[i][1] = Integer.parseInt(token.nextToken());
		}
		ans = Integer.MAX_VALUE;
	}

	public static void main(String[] args) throws Exception {
		init();
		subset(0);
		System.out.println(ans);
	}
}
