import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int v, e;
	static Edge[] edges;

	static int[] parents;

	static int ans;

	public static void sol() {
		for (Edge edge : edges) {
			int from = edge.from;
			int to = edge.to;
			int w = edge.w;

			if (union(from, to)) {
				ans += w;
			} else {
				continue;
			}
		}
	}

	public static int findSet(int a) {
		if (parents[a] == a)
			return a;
		return parents[a] = findSet(parents[a]);
	}

	public static boolean union(int a, int b) {
		int rootA = findSet(a);
		int rootB = findSet(b);
		if (rootA == rootB)
			return false;
		parents[rootB] = rootA;
		return true;
	}

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		v = Integer.parseInt(token.nextToken());
		e = Integer.parseInt(token.nextToken());
		edges = new Edge[e];
		for (int i = 0; i < e; i++) {
			token = new StringTokenizer(input.readLine());
			int from = Integer.parseInt(token.nextToken());
			int to = Integer.parseInt(token.nextToken());
			int w = Integer.parseInt(token.nextToken());
			edges[i] = new Edge(from, to, w);
		}
		Arrays.sort(edges, (a, b) -> {
			return Integer.compare(a.w, b.w);
		});

		parents = new int[v + 1];
		for (int i = 1; i <= v; i++) {
			parents[i] = i;
		}
		ans = 0;
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