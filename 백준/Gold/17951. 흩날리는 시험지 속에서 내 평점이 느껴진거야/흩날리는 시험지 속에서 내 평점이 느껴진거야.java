import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n, k;
	static int[] arr;
	static int ans, sum;

	static int start, end, mid;

	public static void sol() {
		start = 0;
		end = sum;
		while (start <= end) {
			mid = (start + end) / 2;

			int cnt = 0, temp = 0;

			for (int i = 0; i < n; i++) {
				temp += arr[i];
				if (temp >= mid) {
					cnt++;
					temp = 0;
				}
			}

			if (cnt >= k) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
	}

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		k = Integer.parseInt(token.nextToken());

		arr = new int[n];
		token = new StringTokenizer(input.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(token.nextToken());
			sum += arr[i];
		}
		ans = Integer.MAX_VALUE;
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
		System.out.println(end);
	}
}