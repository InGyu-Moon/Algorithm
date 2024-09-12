import java.io.*;
import java.util.*;

/**
 * https://st-lab.tistory.com/277
 */
public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n, m;
	static int[] homes;

	public static int canInstall(int distance) {
		int count = 1;
		int lastLocation = homes[0];

		for (int i = 0; i < n; i++) {
			int location = homes[i];
			if (location - lastLocation >= distance) {
				count++;
				lastLocation = location;
			}
		}
		return count;
	}

	public static void sol() {
		int low = 1;
		int high = homes[n - 1] - homes[0] + 1;
		while (low < high) {
			int mid = (high + low) / 2;
			if (canInstall(mid) < m) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		System.out.println(low - 1);
	}

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		m = Integer.parseInt(token.nextToken());
		homes = new int[n];
		for (int i = 0; i < n; i++) {
			homes[i] = Integer.parseInt(input.readLine());
		}
		Arrays.sort(homes);
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
	}

}