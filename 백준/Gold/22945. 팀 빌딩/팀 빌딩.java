import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n;
	static int[] arr;

	static Deque<Integer> deq;
	static int ans;

	public static void sol() {
		while (deq.size() >= 2) {
			int start = deq.peekFirst();
			int end = deq.peekLast();
			int size = deq.size();
			int temp = Math.min(start, end) * (size - 2);
			ans = Math.max(ans, temp);

			if (start > end)
				deq.pollLast();
			else
				deq.pollFirst();
		}
	}

	public static void init() throws IOException {
		deq = new ArrayDeque<Integer>();
		ans = Integer.MIN_VALUE;
		n = Integer.parseInt(input.readLine());
		token = new StringTokenizer(input.readLine());
		for (int i = 0; i < n; i++) {
			int temp = Integer.parseInt(token.nextToken());
			deq.offerLast(temp);
		}
	}

	public static void main(String[] args) throws IOException {
		init();
		sol();
		System.out.println(ans);
	}

}