import java.io.*;
import java.util.StringTokenizer;

public class Solution {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int[] arr = new int[10];

	public static void sol() {
		int cnt = 1;
		while (true) {
			for (int i = 1; i <= 8; i++) {
				arr[i - 1] = arr[i];
			}
			if (cnt > 5) {
				cnt = 1;
			}
			if (cnt <= 5) {
				arr[8] = arr[0] - cnt;
				if (arr[8] <= 0) {
					arr[8] = 0;
					return;
				}
			}
			cnt++;
		}
	}

	public static void main(String[] args) throws Exception {
		for (int tc = 1; tc <= 10; tc++) {
			// 입력
			input.readLine();
			token = new StringTokenizer(input.readLine());
			for (int i = 1; i <= 8; i++) {
				arr[i] = Integer.parseInt(token.nextToken());
			}

			sol();
			output.append("#").append(tc).append(" ");
			for (int i = 1; i <= 8; i++) {
				output.append(arr[i]).append(" ");
			}
			output.append("\n");

		}

		System.out.println(output);
	}
}