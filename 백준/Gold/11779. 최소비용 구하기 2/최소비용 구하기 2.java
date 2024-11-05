import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n, m;

	static PriorityQueue<Node> pq;
	static Map<Integer, List<Node>> map;
	static int[] d;

	static int start, end;
	static int INF = Integer.MAX_VALUE;

	static int ans;

	static int[] root;

	public static void sol() {
		d[start] = 0;
		pq.add(new Node(start, d[start]));
		while (!pq.isEmpty()) {
			Node front = pq.poll();
			if (d[front.to] < front.w)
				continue;
			for (Node node : map.get(front.to)) {
				if (d[node.to] > node.w + front.w) {
					root[node.to] = front.to;
					d[node.to] = node.w + front.w;
					pq.offer(new Node(node.to, d[node.to]));
				}
			}
		}

		List<Integer> list = new ArrayList<Integer>();
		int temp = end;

		while (true) {
			if (root[temp] == start) {
				list.add(root[temp]);
				break;
			}
			list.add(root[temp]);
			temp = root[temp];
		}

		// 출력
		output.append(d[end]).append("\n");
		output.append(list.size() + 1).append("\n");
		for (int i = list.size() - 1; i >= 0; i--) {
			output.append(list.get(i)).append(" ");
		}
		output.append(end);
	}

	public static void init() throws IOException {
		n = Integer.parseInt(input.readLine());
		m = Integer.parseInt(input.readLine());

		d = new int[n + 1];
		root = new int[n + 1];
		map = new HashMap<Integer, List<Node>>();
		pq = new PriorityQueue<Node>((a, b) -> {
			return Integer.compare(a.w, b.w);
		});

		for (int i = 1; i <= n; i++) {
			map.put(i, new ArrayList<Node>());
			d[i] = INF;
		}

		for (int i = 0; i < m; i++) {
			token = new StringTokenizer(input.readLine());
			int from = Integer.parseInt(token.nextToken());
			int to = Integer.parseInt(token.nextToken());
			int w = Integer.parseInt(token.nextToken());
			map.get(from).add(new Node(to, w));
		}
		token = new StringTokenizer(input.readLine());
		start = Integer.parseInt(token.nextToken());
		end = Integer.parseInt(token.nextToken());

	}

	public static void main(String[] args) throws IOException {
		init();
		sol();
		System.out.println(output);
	}

	static class Node {
		int to;
		int w;

		public Node(int to, int w) {
			this.to = to;
			this.w = w;
		}
	}

}