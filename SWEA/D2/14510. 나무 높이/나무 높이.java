import java.io.*;
import java.util.*;

public class Solution {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int max;
	static int n;
	static int[] arr;
	static int days = 0;

	public static void sol() {
		int one = 0, two = 0;
		for (int i = 0; i < n; i++) {
			int temp = max - arr[i];
			two += temp / 2;
			one += temp % 2;
		}

		if (two == one) {
			days = one * 2;
			return;
		}

		if (one > two) {
			days = one * 2 - 1;
		} else if (two > one) {
			if (one > 0) {
				days += one * 2;
				two -= one;
			}
			int temp = two / 3;
			days += temp * 4;
			two %= 3;
			if (two == 0)
				return;
			else if (two == 1) {
				days += 2;
				return;
			} else if (two == 2) {
				days += 3;
				return;
			}
		}
		return;
	}

	public static void init() throws Exception {
		n = Integer.parseInt(input.readLine());
		token = new StringTokenizer(input.readLine());
		days = 0;
		arr = new int[n];
		max = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(token.nextToken());
			max = Math.max(max, arr[i]);
		}
	}

	public static void main(String[] args) throws Exception {
		// 가장 첫 줄에는 테스트 케이스의 총 수가 주어진다. 그 다음 줄부터 각 테스트 케이스가 주어지며,
		// 각 테스트 케이스는 2줄로 구성된다. 각 테스트 케이스의 첫째 줄에는 나무의 개수 N이 주어진다.
		// 다음 줄에는 나무들의 높이가 N개의 자연수로 주어진다
		int TC = Integer.parseInt(input.readLine());
		for (int t = 1; t <= TC; t++) {
			init();
			sol();
			output.append("#").append(t).append(" ").append(days).append("\n");
		}
		System.out.println(output);
	}
}