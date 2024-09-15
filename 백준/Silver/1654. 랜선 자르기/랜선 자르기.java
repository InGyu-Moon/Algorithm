import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int k, n;
	static long[] arr;

	public static long check(long mid) {
		int cnt = 0;
		for (int i = 0; i < k; i++) {
			cnt += arr[i] / mid;
		}
		return cnt;
	}

	public static long sol() {
		long low = 1, high = arr[k - 1];
		while (low <= high) {
			long mid = (low + high) / 2;
			if (check(mid) < n) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return low - 1;
	}

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		k = Integer.parseInt(token.nextToken());
		n = Integer.parseInt(token.nextToken());
		arr = new long[k];
		for (int i = 0; i < k; i++) {
			arr[i] = Integer.parseInt(input.readLine());
		}
		Arrays.sort(arr);
	}

	public static void main(String[] args) throws Exception {
		init();
		System.out.println(sol());
	}
}