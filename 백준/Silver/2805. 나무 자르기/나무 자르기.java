import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n, m;
	static int[] arr;

	public static long check(long mid) {

		long sum = 0;
		for (int i = 0; i < n; i++) {
			if (arr[i] - mid < 0)
				continue;
			sum += arr[i] - mid;
		}
		return sum;
	}

	public static long sol() {
		long low = 1, high = arr[n - 1];
		while (low <= high) {
			long mid = (low + high) / 2;
			if (check(mid) < m) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return low - 1;
	}

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		m = Integer.parseInt(token.nextToken());
		arr = new int[n];
		token = new StringTokenizer(input.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(token.nextToken());
		}
		Arrays.sort(arr);
	}

	public static void main(String[] args) throws Exception {
		init();
		System.out.println(sol());
	}
}