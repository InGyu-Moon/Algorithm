import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n;
	static int[] arr;

	static List<Integer> dp;

	public static int binarySearch(int num) {
		int low = 1;
		int high = dp.size();
		while (low < high) {
			int mid = (low + high) / 2;
			if (dp.get(mid - 1) < arr[num]) {
				low = mid + 1;
			} else {
				high = mid;
			}
		}
		return high;
	}

	public static void sol() {
		for (int i = 1; i <= n; i++) {
			int idx = binarySearch(i);
			if (dp.isEmpty() || dp.get(idx - 1) < arr[i]) {
				dp.add(arr[i]);
			} else {
				dp.remove(idx - 1);
				dp.add(idx - 1, arr[i]);
			}
		}
	}

	public static void init() throws Exception {
		n = Integer.parseInt(input.readLine());
		arr = new int[n + 1];
		token = new StringTokenizer(input.readLine());
		for (int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(token.nextToken());
		}
		dp = new ArrayList<>();
	}

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(input.readLine());
		for (int t = 1; t <= T; t++) {
			init();
			sol();
			output.append("#").append(t).append(" ").append(dp.size()).append("\n");
		}
		System.out.println(output);

	}
}