import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n, val, sum;
	static int[][] arr;

	static int[] parents;

	static PriorityQueue<Node> pq;

	public static int find(int a) {
		if (parents[a] == a) {
			return a;
		}
		return parents[a] = find(parents[a]);
	}

	public static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if (parents[rootA] == parents[rootB]) {
			return false;
		}
		parents[rootA] = rootB;
		return true;

	}

	public static void sol() {
		int cnt = 0;
		while (!pq.isEmpty()) {
			Node front = pq.poll();
			if (union(front.from, front.to) && front.w > 0) {
				sum -= front.w;
				cnt++;
			}
		}
		if (cnt != n - 1)
			sum = -1;
//		System.out.println("cnt: " + cnt);

	}

	public static void init() throws Exception {
		n = Integer.parseInt(input.readLine());
		arr = new int[n + 1][n + 1];
		sum = 0;

		parents = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			parents[i] = i;
		}

		pq = new PriorityQueue<Node>((a, b) -> {
			return Integer.compare(a.w, b.w);
		});

		for (int i = 0; i < n; i++) {
			String temp = input.readLine();
			for (int j = 0; j < n; j++) {
				char c = temp.charAt(j);
				if ('a' <= c && c <= 'z') {
					val = c - 'a' + 1;
				} else if ('A' <= c && c <= 'Z') {
					val = c - 'A' + 27;
				} else if (c == '0') {
					val = 0;
				}
				arr[i][j] = val;
				sum += val;
				if (val > 0)
					pq.add(new Node(i, j, val));
			}
		}
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
		System.out.println(sum);
	}

	static class Node {
		int from;
		int to;
		int w;

		public Node(int from, int to, int w) {
			this.from = from;
			this.to = to;
			this.w = w;
		}

	}
}