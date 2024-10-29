import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n, k;

	static int[] arr, cnt;

	static int max;

	public static void sol() {

		int start = 0, end = 0, len = 0;
		while (true) {
			int num = arr[end];
			cnt[num]++;
			if (cnt[num] == k + 1) {
				max = Math.max(max, len);
				while (true) {
					len--;
					cnt[arr[start]]--;
					if (arr[start] == arr[end]) {
						start++;
						break;
					}
					start++;
				}
			}
			len++;
			end++;
			if (end == n)
				break;
		}
		max = Math.max(max, len);
	}

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		k = Integer.parseInt(token.nextToken());

		arr = new int[n];
		cnt = new int[100001];

		token = new StringTokenizer(input.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(token.nextToken());
		}

		max = Integer.MIN_VALUE;

	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
		System.out.println(max);
	}

}