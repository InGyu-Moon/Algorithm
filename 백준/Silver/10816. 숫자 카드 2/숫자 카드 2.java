import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n, m;
	static int[] cards, target;

	public static int getLow(int idx) {
		int start = 0, end = n; // end 는 n-1이 아니라 n 이다.
		while (start < end) {
			int mid = (start + end) / 2;
			if (cards[mid] < target[idx]) {
				start = mid + 1;
			} else {
				end = mid;
			}
		}
		return start;
	}

	public static int getHigh(int idx) {
		int start = 0, end = n; // end 는 n-1이 아니라 n 이다.
		while (start < end) {
			int mid = (start + end) / 2;
			if (cards[mid] <= target[idx]) {
				start = mid + 1;
			} else {
				end = mid;
			}
		}
		return start;
	}

	public static void sol() {
		for (int i = 0; i < m; i++) {
//			System.out.println("target[i]: "+ target[i]+", getHigh(i): "+ getHigh(i)+", getLow(i): "+ getLow(i));
			output.append(getHigh(i) - getLow(i)).append(" ");
		}
	}

	public static void init() throws Exception {
		n = Integer.parseInt(input.readLine());
		cards = new int[n];
		token = new StringTokenizer(input.readLine());
		for (int i = 0; i < n; i++) {
			cards[i] = Integer.parseInt(token.nextToken());
		}
		m = Integer.parseInt(input.readLine());
		target = new int[m];
		token = new StringTokenizer(input.readLine());
		for (int i = 0; i < m; i++) {
			target[i] = Integer.parseInt(token.nextToken());
		}
		Arrays.sort(cards);
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
		System.out.println(output);
	}
}