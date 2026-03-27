import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int INF = 987654321;

	static int v, e, start;

	public static List<Node>[] graph;
	public static int[] d;

	public static PriorityQueue<Node> pq = new PriorityQueue<>((Node a, Node b) -> {
		return Integer.compare(a.val, b.val);
	});

	public static void sol() {
		pq.add(new Node(start, 0));
		while (!pq.isEmpty()) {
			Node front = pq.poll();
			List<Node> list = graph[front.to];
			if (list == null || front.val > d[front.to])
				continue;
			for (Node node : list) {
				if (d[node.to] > d[front.to] + node.val) {
					d[node.to] = d[front.to] + node.val;
					pq.add(new Node(node.to, d[node.to]));
				}
			}
		}
		for (int i = 1; i <= v; i++) {
			if (d[i] >= INF)
				System.out.println("INF");
			else
				System.out.println(d[i]);
		}
	}

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		v = Integer.parseInt(token.nextToken());
		e = Integer.parseInt(token.nextToken());
		start = Integer.parseInt(input.readLine());
		graph = new ArrayList[v + 1];
		d = new int[v + 1];
		for (int i = 1; i <= v; i++) {
			d[i] = INF;
		}
		d[start] = 0;
		for (int i = 0; i < e; i++) {
			token = new StringTokenizer(input.readLine());
			int from = Integer.parseInt(token.nextToken());
			int to = Integer.parseInt(token.nextToken());
			int val = Integer.parseInt(token.nextToken());
			if (graph[from] == null)
				graph[from] = new ArrayList<>();
			graph[from].add(new Node(to, val));
		}
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
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
