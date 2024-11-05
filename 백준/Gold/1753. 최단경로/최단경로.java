import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int V, E;
	static int start;

	static int[] d;
	static Map<Integer, List<Edge>> edges;
	static PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> {
		return Integer.compare(a.w, b.w);
	});

	private static void sol() {
		pq.offer(new Edge(start, d[start]));
		while (!pq.isEmpty()) {
			Edge front = pq.poll();
			if (front.w > d[front.v])
				continue;

			for (Edge edge : edges.get(front.v)) { // why?
				if (d[edge.v] > front.w + edge.w) {
					d[edge.v] = front.w + edge.w;
					pq.offer(new Edge(edge.v, d[edge.v]));
				}
			}
		}

	}

	private static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		V = Integer.parseInt(token.nextToken());
		E = Integer.parseInt(token.nextToken());
		start = Integer.parseInt(input.readLine());
		edges = new HashMap<>();
		d = new int[V + 1];
		for (int i = 1; i <= V; i++) {
			edges.put(i, new ArrayList<Edge>());
			d[i] = Integer.MAX_VALUE;
		}
		d[start] = 0;
		// 간선 정보 입력
		for (int i = 0; i < E; i++) {
			token = new StringTokenizer(input.readLine());
			int u = Integer.parseInt(token.nextToken());
			int v = Integer.parseInt(token.nextToken());
			int w = Integer.parseInt(token.nextToken());
			edges.get(u).add(new Edge(v, w));
		}

	}

	public static void main(String[] args) throws Exception {
		init();
		sol();

		for (int i = 1; i <= V; i++) {
			if (d[i] == Integer.MAX_VALUE)
				output.append("INF\n");
			else {
				output.append(d[i]).append("\n");
			}
		}
		System.out.println(output);

	}

	static class Edge {
		int v, w;

		public Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}

	}
}