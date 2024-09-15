import java.io.*;
import java.util.*;

/**
 * https://chanhuiseok.github.io/posts/baek-22/
 */
public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n, m;
	static int[] arr;
	static int cnt = 0;
	static int low, high;

	static int temp;
	static boolean flag;

	public static void check(int mid) {
		int sum = 0;
		cnt = 0;
		for (int i = 0; i < n; i++) {
			sum += arr[i];
			if (sum > mid) {
				cnt++;
				sum = arr[i];
			}
		}
		if (sum != 0)
			cnt++;
	}

	public static int sol() {
		int mid;

		while (low <= high) {
			mid = (low + high) / 2;
			check(mid);

			if (cnt > m) {
				low = mid + 1;
			} else if (m >= cnt) {
				high = mid - 1;
			}
		}
		return low;
	}

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		m = Integer.parseInt(token.nextToken());
		arr = new int[n];
		token = new StringTokenizer(input.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(token.nextToken());
			if (low < arr[i])
				low = arr[i];
			high += arr[i];
		}
	}

	public static void main(String[] args) throws Exception {
		init();
		System.out.println(sol());
	}

}