import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static long n, m;
	static long[] time;

	public static long getNum(long num) {
		long cnt = 0;
		for (int i = 0; i < m; i++) {
			cnt += (num / time[i]);
		}
		return cnt + m;
	}

	public static void sol() {
		if (n <= m) {
			output.append(n);
			return;
		}

		long start = 0;
		long end = n * 30;
		long result = 0;
		while (start <= end) {
			long mid = (start + end) / 2;
			long num = getNum(mid);
			if (num < n) {
				start = mid + 1;
			} else {
				result = mid;
				end = mid - 1;
			}
		}
		long idx = n - getNum(result - 1);
		long cnt = 0;
		for (int i = 0; i < m; i++) {
			if (result % time[i] == 0) {
				cnt++;
				if (cnt == idx) {
					output.append(i + 1);
					return;
				}
			}
		}
	}

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		m = Integer.parseInt(token.nextToken());
		time = new long[(int)m];
		token = new StringTokenizer(input.readLine());
		for (int i = 0; i < m; i++) {
			time[i] = Integer.parseInt(token.nextToken());
		}
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
		System.out.println(output);
	}
}