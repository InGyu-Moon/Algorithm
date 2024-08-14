
import java.io.*;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;
	static int[][] arr = new int[1025][1025];
	static int[][] dp = new int[1025][1025];
	static int n, m;

	public static void main(String[] args) throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		m = Integer.parseInt(token.nextToken());

		// 배열 입력
		for (int i = 1; i <= n; i++) {
			token = new StringTokenizer(input.readLine());
			for (int j = 1; j <= n; j++) {
				arr[i][j] = Integer.parseInt(token.nextToken());
				dp[i][j] = dp[i - 1][j] + arr[i][j];
			}
		}
		
		// 좌표값 입력
		int x1, x2, y1, y2;
		for (int i = 0; i < m; i++) {
			token = new StringTokenizer(input.readLine());
			x1 = Integer.parseInt(token.nextToken());
			y1 = Integer.parseInt(token.nextToken());
			x2 = Integer.parseInt(token.nextToken());
			y2 = Integer.parseInt(token.nextToken());
			int sum = 0;
			for (int col = y1; col <= y2; col++) {
				sum += dp[x2][col];
				sum -= dp[x1 - 1][col];

			}
			output.append(sum).append("\n");
		}

		System.out.println(output);

	}
}
