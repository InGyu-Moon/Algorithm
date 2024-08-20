
import java.io.*;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;
	static int n, b;
	static int[] arr;
	static int ans;

	private static void sol(int idx, int sum) {
		if (ans < sum) // 이미 이전 합계의 값보다 커지면 종료
			return;
		if (idx == n) { // 끝까지 다 확인했음
			if (sum >= b) {
				if (ans > sum) // 이전 합 보다 현재 값이 더 작으면 최신화해줌
					ans = sum;
			}
			return;
		}

		// 포함 함
		sol(idx + 1, sum + arr[idx]);
		// 포함 안함
		sol(idx + 1, sum);

	}

	public static void main(String[] args) throws Exception {
		int testCase = Integer.parseInt(input.readLine());
		for (int tc = 0; tc < testCase; tc++) {
			token = new StringTokenizer(input.readLine());
			n = Integer.parseInt(token.nextToken());
			b = Integer.parseInt(token.nextToken());
			arr = new int[n];

			token = new StringTokenizer(input.readLine());
			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(token.nextToken());
			}
			ans = Integer.MAX_VALUE;
			sol(0, 0);

			output.append("#").append(tc + 1).append(" ").append(ans - b).append("\n");
		}
		System.out.println(output);
	}
}
