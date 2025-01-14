

import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int[] d;
	static int n, m;
	static Map<Integer, List<int[]>> map;
	static PriorityQueue<Integer> pq;

	public static void sol() {
		pq.add(1);
		while (!pq.isEmpty()) {
			int top = pq.poll();
//			System.out.println("top: " + top);
			for (int[] arr : map.get(top)) {
				int to = arr[0];
				int val = arr[1];
//				System.out.println("to,val : " + to + ", " + val);
//				System.out.println("d[to], d[top]+val : " + d[to] + ", " + (d[top] + val));
				if (d[to] > d[top] + val) {
//					System.out.println("d[to] > d[top] + val");
					d[to] = d[top] + val;
//					System.out.println("d[to]: " + d[to]);
//					System.out.println("d[top]: " + d[top]);
//					System.out.println("val: " + val);
					pq.add(to);
				}
			}
		}
	}

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		m = Integer.parseInt(token.nextToken());
		d = new int[n + 1];
		map = new HashMap<>();
		pq = new PriorityQueue<>((a, b) -> {
			return Integer.compare(d[a], d[b]);
		});
		for (int i = 1; i <= n; i++) {
			map.put(i, new ArrayList<>());
			d[i] = Integer.MAX_VALUE / 2;
		}
		d[1] = 0;
		for (int i = 0; i < m; i++) {
			token = new StringTokenizer(input.readLine());
			int a = Integer.parseInt(token.nextToken());
			int b = Integer.parseInt(token.nextToken());
			int c = Integer.parseInt(token.nextToken());
			map.get(a).add(new int[] { b, c });
			map.get(b).add(new int[] { a, c });
		}
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
		System.out.println(d[n]);
	}
}
