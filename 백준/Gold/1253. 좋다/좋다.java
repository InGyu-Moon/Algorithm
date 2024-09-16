import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n;
	static long[] arr;

	static int left, right;
	static int cnt = 0;

	public static boolean check(int idx) {
		left = 0;
		right = n - 1;
		while (left < right) {

			if (left == idx) {
				left++;
				continue;
			}
			if (right == idx) {
				right--;
				continue;
			}

			long sum = arr[left] + arr[right];

			if (sum < arr[idx]) {
				left++;
			} else if (sum > arr[idx]) {
				right--;
			} else {
				return true;
			}
		}
		return false;
	}

	public static int sol() {
		for (int i = 0; i < n; i++) {
			if (check(i))
				cnt++;
		}
		return cnt;
	}

	public static void init() throws Exception {
		n = Integer.parseInt(input.readLine());
		arr = new long[n];
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