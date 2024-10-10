import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n;
	static int[][] arr;
	static List<Integer> list;

	public static void sol() {
		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				if (i == k)
					continue;
				for (int j = 1; j <= n; j++) {
					if (i == j)
						continue;
					arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
				}
			}
		}

		// 최소값 계산
		for (int i = 1; i <= n; i++) {
			int sum = 0;
			for (int j = 1; j <= n; j++) {
				if (i == j)
					continue;
				sum += arr[i][j];
			}
			list.add(sum);
		}
		Collections.sort(list);
	}

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		arr = new int[n + 1][n + 1];
		list = new ArrayList<Integer>();
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				int temp = Integer.parseInt(token.nextToken());
				if (temp == 0) {
					arr[i][j] = 87654321;
				} else {
					arr[i][j] = 1;
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(input.readLine());
		for (int t = 1; t <= T; t++) {
			init();
			sol();
			output.append("#").append(t).append(" ").append(list.get(0)).append("\n");
		}
		System.out.println(output);
	}
}