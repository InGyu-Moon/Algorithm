import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n;
	static long ans;
	static int[] arr;

	private static int binarySearch(int target, int s, int type) {
		int e = n;
		int m = 0;

		while (e > s) {
			m = (s + e) / 2;

			if (type == 0 && arr[m] + target >= 0)
				e = m;
			else if (type == 1 && arr[m] + target > 0)
				e = m;
			else
				s = m + 1;
		}

		return s;
	}

	public static int binaryUpper(int sum, int idx) {
		int start = idx;
		int end = n - 1;
		while (start <= end) {
			int mid = (start + end) / 2;
			if (sum + arr[mid] <= 0) {
				start = mid + 1;
			} else if (sum + arr[mid] > 0) {
				end = mid;
			}
		}

		return start;
	}

	public static int binaryLower(int sum, int idx) {
		int start = idx;
		int end = n - 1;
		while (start <= end) {
			int mid = (start + end) / 2;
			if (sum + arr[mid] < 0) {
				start = mid + 1;
			} else if (sum + arr[mid] >= 0) {
				end = mid;
			}
		}

		return start;
	}

	public static void sol() {
		for (int i = 0; i < n - 2; i++) {
			for (int j = i + 1; j < n - 1; j++) {
				int sum = arr[i] + arr[j];
				int lower = binarySearch(sum, j + 1,0);
				int upper = binarySearch(sum, j + 1,1);
				ans += upper - lower;
			}
		}
		System.out.println(ans);
	}

	public static void init() throws Exception {
		n = Integer.parseInt(input.readLine());
		arr = new int[n];
		token = new StringTokenizer(input.readLine());

		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(token.nextToken());
		}
		Arrays.sort(arr);
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
	}

}