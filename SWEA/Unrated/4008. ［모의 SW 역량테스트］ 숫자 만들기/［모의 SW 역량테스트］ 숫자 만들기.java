
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	private static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer token;
	private static StringBuilder output = new StringBuilder();

	private static int N;
	private static int[] operator = new int[4];
	private static int[] usedOperator;
	private static int[] numbers;

	private static int minResult;
	private static int maxResult;

	private static void makePermutation(int depth, int result) {
		// 기저
		if (depth == N - 1) {
			if (minResult > result) {
				minResult = result;
			}
			if (maxResult < result) {
				maxResult = result;
			}
			return;
		}
		// 재귀
		for (int i = 0; i < 4; i++) {
			if (usedOperator[i] < operator[i]) {
				usedOperator[i]++;

				int temp = 0;
				if (i == 0) {
					temp = result + numbers[depth + 1];
				} else if (i == 1) {
					temp = result - numbers[depth + 1];
				} else if (i == 2) {
					temp = result * numbers[depth + 1];
				} else if (i == 3) {
					temp = result / numbers[depth + 1];
				}

				makePermutation(depth + 1, temp);

				usedOperator[i]--;
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(input.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(input.readLine());
			numbers = new int[N];
			usedOperator = new int[4];

			token = new StringTokenizer(input.readLine());
			operator[0] = Integer.parseInt(token.nextToken());
			operator[1] = Integer.parseInt(token.nextToken());
			operator[2] = Integer.parseInt(token.nextToken());
			operator[3] = Integer.parseInt(token.nextToken());

			token = new StringTokenizer(input.readLine());
			for (int i = 0; i < N; i++) {
				numbers[i] = Integer.parseInt(token.nextToken());
			}

			// 초기화
			minResult = Integer.MAX_VALUE;
			maxResult = Integer.MIN_VALUE;

			makePermutation(0, numbers[0]);

			output.append("#").append(t).append(" ").append(maxResult - minResult).append("\n");
		}
		System.out.println(output);
	}

}
