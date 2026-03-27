import java.util.*;
import java.io.*;

public class Main {

	public static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	public static StringBuilder output = new StringBuilder();
	public static StringTokenizer token;

	public static int INF = 987654321;

	public static int n, m;
	public static Map<Integer, List<Node>> map = new HashMap<>();

	public static int start, end;

	public static int[] d;
	public static PriorityQueue<Node> pq = new PriorityQueue<>((Node a, Node b) -> {
		return Integer.compare(a.val, b.val);
	});

	public static void sol() {
		pq.add(new Node(start, d[start]));
		while (!pq.isEmpty()) {
			Node front = pq.poll();
			List<Node> list = map.get(front.to);
			if (list == null)
				continue;
			if (front.val > d[front.to])
				continue;
			for (Node node : list) {
				int to = node.to;
				int val = node.val;
				if (d[to] > d[front.to] + val) {
					d[to] = d[front.to] + val;
					pq.add(new Node(to, d[to]));
				}
			}
		}
	}

	public static void init() throws Exception {
		n = Integer.parseInt(input.readLine());
		m = Integer.parseInt(input.readLine());
		d = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			d[i] = INF;
		}
		for (int i = 0; i < m; i++) {
			token = new StringTokenizer(input.readLine());
			int start = Integer.parseInt(token.nextToken());
			int end = Integer.parseInt(token.nextToken());
			int val = Integer.parseInt(token.nextToken());
			if (map.get(start) == null)
				map.put(start, new ArrayList<>());
			map.get(start).add(new Node(end, val));
		}
		token = new StringTokenizer(input.readLine());
		start = Integer.parseInt(token.nextToken());
		end = Integer.parseInt(token.nextToken());
		d[start] = 0;
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
		System.out.println(d[end]);
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