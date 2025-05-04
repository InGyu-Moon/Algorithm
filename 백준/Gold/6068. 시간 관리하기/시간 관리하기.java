import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n;
	static PriorityQueue<int[]> pq;

	public static void sol() {
		int curr = pq.peek()[1];
		while (!pq.isEmpty()) {
			int[] top = pq.poll();
			int cost = top[0];
			int time = top[1];
//			System.out.println("cost: " + cost);
//			System.out.println("time: " + time);
			if (curr > time) {
				curr = time - cost;
			} else if (curr <= time) {
				curr = curr - cost;
			}
//			System.out.println("=== curr: " + curr);
		}
		if (curr < 0)
			curr = -1;
		System.out.println(curr);
	}

	public static void init() throws Exception {
		pq = new PriorityQueue<>((a, b) -> {
			if (a[1] == b[1])
				return Integer.compare(a[0], b[0]);
			return -Integer.compare(a[1], b[1]);
		});
		n = Integer.parseInt(input.readLine());
		for (int i = 0; i < n; i++) {
			token = new StringTokenizer(input.readLine());
			int a = Integer.parseInt(token.nextToken());
			int b = Integer.parseInt(token.nextToken());
			pq.add(new int[] { a, b });
		}
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
	}
}