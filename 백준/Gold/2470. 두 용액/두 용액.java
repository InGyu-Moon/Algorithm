import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;
	static int n, start, end;
	static int[] arr;
	static int ans = Integer.MAX_VALUE;
	static int x, y;

	static private void sol() {
		int sum;
		while (start < end) {
			sum = arr[start] + arr[end];

			if (Math.abs(sum) < Math.abs(ans)) {
				ans = sum;
				x = arr[start];
				y = arr[end];
			}

			if (sum > 0) {
				end--;
			} else if (sum < 0) {
				start++;
			} else {
				return;
			}

		}
	}

	public static void main(String[] args) throws Exception {
		n = Integer.parseInt(input.readLine());
		token = new StringTokenizer(input.readLine());
		arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(token.nextToken());
		}
		Arrays.sort(arr);
		start = 0;
		end = n - 1;
		sol();
		output.append(x).append(" ").append(y).append(" ");
		System.out.println(output);

	}

}