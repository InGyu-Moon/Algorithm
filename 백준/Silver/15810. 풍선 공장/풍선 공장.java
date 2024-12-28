import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n, m;
	static long[] arr;
	static long max, start, end, mid;

	public static long check(long mid) {
		long ans = 0;
		for (long n : arr) {
			ans += mid / n;
		}
		return ans;
	}

	public static void sol() {
		start = 0;
		end = max * m;
		while (start <= end) {
			mid = (start + end) / 2;
			if (start == end) {
				break;
			}
			long num = check(mid);
			if (num >= m) {
				end = mid;
			} else {
				start = mid + 1;
			}
		}
	}

	public static void init() throws Exception {
		max = Integer.MIN_VALUE;

		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		m = Integer.parseInt(token.nextToken());
		arr = new long[n];
		token = new StringTokenizer(input.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(token.nextToken());
			max = Math.max(arr[i], max);
		}
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
		System.out.println(mid);
	}
}