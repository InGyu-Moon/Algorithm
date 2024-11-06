import java.io.*;
	import java.util.*;

	public class Main {

		/**
		 * https://www.acmicpc.net/problem/1238
		 */
		static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		static StringBuilder output = new StringBuilder();
		static StringTokenizer token;

		static int INF = Integer.MAX_VALUE;
		static int n, m, x;

		static int[][] d;
		static Map<Integer, List<Node>> map;
		static PriorityQueue<Node> pq;

		static int ans;

		public static void dijkstra(int num) {
			d[num][num] = 0;
			pq.add(new Node(num, 0));
			while (!pq.isEmpty()) {
				Node front = pq.poll();
				if (front.w > d[num][front.to])
					continue;
				for (Node node : map.get(front.to)) {
					if (d[num][node.to] > front.w + node.w) {
						d[num][node.to] = front.w + node.w;
						pq.add(new Node(node.to, d[num][node.to]));
					}
				}
			}
		}

		public static void sol() {

			for (int i = 1; i <= n; i++)
				Arrays.fill(d[i], INF);

			for (int i = 1; i <= n; i++) {
				dijkstra(i);
			}
			for(int i=1;i<=n;i++) {
				int sum = d[i][x] + d[x][i];
				ans = Math.max(ans, sum);
			}

		}

		public static void init() throws IOException {
			token = new StringTokenizer(input.readLine());
			n = Integer.parseInt(token.nextToken());
			m = Integer.parseInt(token.nextToken());
			x = Integer.parseInt(token.nextToken());

			d = new int[n + 1][n + 1];
			map = new HashMap<Integer, List<Node>>();
			pq = new PriorityQueue<Node>((a, b) -> {
				return Integer.compare(a.w, b.w);
			});

			for (int i = 1; i <= n; i++) {
				map.put(i, new ArrayList<Node>());
			}

			for (int i = 0; i < m; i++) {
				token = new StringTokenizer(input.readLine());
				int from = Integer.parseInt(token.nextToken());
				int to = Integer.parseInt(token.nextToken());
				int w = Integer.parseInt(token.nextToken());
				map.get(from).add(new Node(to, w));
			}

		}

		public static void main(String[] args) throws IOException {
			init();
			sol();
			System.out.println(ans);
		}

		static class Node {
			int to;
			int w;

			public Node(int to, int w) {
				super();
				this.to = to;
				this.w = w;
			}

		}

	}