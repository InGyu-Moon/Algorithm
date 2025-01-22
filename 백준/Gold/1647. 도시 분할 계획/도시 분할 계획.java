import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n, m;

	static int ans;

	static PriorityQueue<int[]> pq;

	static int[] root;

	public static void make() {
		for (int i = 1; i <= n; i++) {
			root[i] = i;
		}
	}

	public static int find(int a) {
		if (root[a] != a) {
			return root[a] = find(root[a]);
		}
		return a;
	}

	public static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if (rootA == rootB) {
			return false;
		}
		root[rootB] = find(rootA);
		return true;
	}

	public static void sol() {
		int idx = 1;
		make();
		while (true) {
			int[] top = pq.poll();
			int a = top[0];
			int b = top[1];
			int val = top[2];
			if (union(a, b)) {
				idx++;
				ans += val;
			}
			if (idx == n) {
				ans -= val;
				System.out.println(ans);
				return;
			}
		}
	}

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		m = Integer.parseInt(token.nextToken());

		root = new int[n + 1];
		pq = new PriorityQueue<int[]>((a, b) -> {
			return Integer.compare(a[2], b[2]);
		});

		for (int i = 0; i < m; i++) {
			token = new StringTokenizer(input.readLine());
			int from = Integer.parseInt(token.nextToken());
			int to = Integer.parseInt(token.nextToken());
			int val = Integer.parseInt(token.nextToken());
			pq.add(new int[] { from, to, val });
		}
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
	}
}