import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n;
	static int[] arr, add;
	static int ans = Integer.MIN_VALUE;

	public static boolean check(int val) {
		int low = 0, high = add.length - 1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (val < add[mid]) {
				high = mid - 1;
			} else if (val > add[mid]) {
				low = mid + 1;
			} else {
				return true;
			}
		}
		return false;
	}

	public static void sol() {
		for (int i = n - 1; i >= 0; i--) {
			for (int j = 0; j < i; j++) {
				if (check(arr[i] - arr[j])) {
					ans = arr[i];
					return;
				}
			}
		}
	}

	public static void init() throws Exception {
		n = Integer.parseInt(input.readLine());
		arr = new int[n];
		add = new int[n * n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(input.readLine());
		}
		Arrays.sort(arr);
		
		int idx = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				add[idx++] = arr[i] + arr[j];
			}
		}
		Arrays.sort(add);
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
		System.out.println(ans);
	}

}