import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int INF = 987654321;

	static int n, m;
	static List<Node>[] graph;
	static int[][] ans, d;

	public static void sol(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>((Node a, Node b) -> {
			return Integer.compare(a.val, b.val);
		});
		pq.add(new Node(start, d[start][start]));
		while (!pq.isEmpty()) {
			Node front = pq.poll();
			if (d[start][front.to] < front.val)
				continue;
			for (Node node : graph[front.to]) {
				if (d[start][node.to] > d[start][front.to] + node.val) {
					d[start][node.to] = d[start][front.to] + node.val;
					pq.add(new Node(node.to, d[start][node.to]));

					if (front.to == start)
						ans[start][node.to] = node.to;
					else
						ans[start][node.to] = ans[start][front.to];

				}
			}
		}
	}

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		m = Integer.parseInt(token.nextToken());
		graph = new ArrayList[n + 1];
		ans = new int[n + 1][n + 1];
		d = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < m; i++) {
			token = new StringTokenizer(input.readLine());
			int from = Integer.parseInt(token.nextToken());
			int to = Integer.parseInt(token.nextToken());
			int val = Integer.parseInt(token.nextToken());
			graph[from].add(new Node(to, val));
			graph[to].add(new Node(from, val));
		}
		// 초기화
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				d[i][j] = INF;
				if (i == j)
					d[i][j] = 0;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		init();
		for (int i = 1; i <= n; i++) {
			sol(i);
		}
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (i == j)
					output.append("- ");
				else
					output.append(ans[i][j]).append(" ");
			}
			output.append("\n");
		}
		System.out.println(output);
	}

	public static class Node {
		int to;
		int val;

		public Node(int to, int val) {
			this.to = to;
			this.val = val;
		}
	}
}
