import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n, m, l;
	static int[] cuttingPoint, times;

	static int start, end, mid;

	public static int check() {
		int cnt = 0, curr = 0, len = 0;
		while (curr < m) {
			int diff = cuttingPoint[curr] - len;
			if (mid <= diff) {
				len = cuttingPoint[curr];
				cnt++;
			}
			curr++;
			if (curr == m && mid > l - len) {
				cnt--;
			}
		}
		return cnt;
	}

	public static void sol() {
		for (int i = 0; i < n; i++) {
			start = 0;
			end = l;
			while (start <= end) {
				mid = (start + end) / 2;
				int cnt = check();
				if (cnt >= times[i]) {
					start = mid + 1;
				} else {
					end = mid - 1;
				}
			}
			output.append(end).append("\n");
		}
	}

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		m = Integer.parseInt(token.nextToken());
		l = Integer.parseInt(token.nextToken());
		cuttingPoint = new int[m];
		times = new int[n];
		for (int i = 0; i < m; i++) {
			cuttingPoint[i] = Integer.parseInt(input.readLine());
		}
		for (int i = 0; i < n; i++) {
			times[i] = Integer.parseInt(input.readLine());
		}
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
		System.out.println(output);
	}
}