import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n, k;

	static int[] w, v;

	static int[][] arr;

	public static void main(String[] args) throws Exception {
		token = new StringTokenizer(input.readLine());

		n = Integer.parseInt(token.nextToken()); // 물건의 수
		k = Integer.parseInt(token.nextToken()); // 버틸수 있는 무게

		arr = new int[n + 1][k + 1];
		w = new int[n + 1];
		v = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			token = new StringTokenizer(input.readLine());
			w[i] = Integer.parseInt(token.nextToken());
			v[i] = Integer.parseInt(token.nextToken());
		}

		for (int i = 1; i <= n; i++) { // 물건의 개수 만큼 돌리기
			for (int j = 1; j <= k; j++) { // 무게 0 부터 무게 k까지
				if (j - w[i] >= 0) {
					int a = arr[i - 1][j];
					int b = arr[i - 1][j - w[i]] + v[i];
					arr[i][j] = Math.max(a, b);
				} else {
					arr[i][j] = arr[i - 1][j];
				}
			}
		}

		System.out.println(arr[n][k]);

	}
}