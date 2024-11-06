import java.io.*;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n, m;
	static int start, end;

	static PriorityQueue<Edge> pq;
	static List<Edge> list;

	static int[] parents;
	static int ans;

	public static int find(int a) {
		if (parents[a] == a) {
			return a;
		}
		return parents[a] = find(parents[a]);
	}

	public static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot == bRoot)
			return false;
		parents[aRoot] = bRoot;
		return true;

	}

	public static void sol() {
		while (!pq.isEmpty()) {
			Edge edge = pq.poll();
			if (union(edge.to, edge.from)) {
				if (find(start) == find(end)) {
					ans = edge.w;
					return;
				}
			}

		}
	}

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		m = Integer.parseInt(token.nextToken());
		token = new StringTokenizer(input.readLine());
		start = Integer.parseInt(token.nextToken());
		end = Integer.parseInt(token.nextToken());

		pq = new PriorityQueue<Edge>((a, b) -> {
			return -Integer.compare(a.w, b.w);
		});
		parents = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			parents[i] = i;
		}

		for (int i = 0; i < m; i++) {
			token = new StringTokenizer(input.readLine());
			int from = Integer.parseInt(token.nextToken());
			int to = Integer.parseInt(token.nextToken());
			int w = Integer.parseInt(token.nextToken());
			pq.add(new Edge(from, to, w));
		}

	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
		System.out.println(ans);
	}

	static class Edge {
		int from;
		int to;
		int w;

		public Edge(int from, int to, int w) {
			super();
			this.from = from;
			this.to = to;
			this.w = w;
		}

	}

}