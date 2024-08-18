import java.io.*;
import java.util.*;

public class Solution {

	private static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer token;
	private static StringBuilder output = new StringBuilder();

	private static int N, X, M;
	private static boolean flag;
	private static int selected[];
	private static int arr[][];

	private static boolean check() {
		for (int[] a : arr) {
			int start = a[0] - 1;
			int end = a[1] - 1;
			int maxSum = a[2];
			int sum = 0;
			for (int i = start; i <= end; i++) {
				sum += selected[i];
			}
			if (sum != maxSum) {
				return false;
			}
		}

		return true;
	}

	private static void sol(int index) {
		if (index < 0) {
			if (check())
				flag = true;
			return;
		}

		for (int i = X; i >= 0; i--) {
			selected[index] = i;
			sol(index - 1);
			if (flag)
				return;
		}
	}

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(input.readLine());
		for (int tc = 1; tc <= T; tc++) {
			token = new StringTokenizer(input.readLine());
			N = Integer.parseInt(token.nextToken());
			X = Integer.parseInt(token.nextToken());
			M = Integer.parseInt(token.nextToken());

			selected = new int[N];
			arr = new int[M][3];
			flag = false;

			for (int idx = 0; idx < M; idx++) {
				token = new StringTokenizer(input.readLine());
				int i = Integer.parseInt(token.nextToken());
				int r = Integer.parseInt(token.nextToken());
				int s = Integer.parseInt(token.nextToken());

				arr[idx][0] = i;
				arr[idx][1] = r;
				arr[idx][2] = s;

			}

			sol(N - 1);

			output.append("#").append(tc).append(" ");
			if (flag) {
				for (int i = 0; i < N; i++) {
					output.append(selected[i]);
					output.append(" ");
				}
			} else {
				output.append(-1);
			}
			output.append("\n");
		}
		System.out.println(output);
	}

}