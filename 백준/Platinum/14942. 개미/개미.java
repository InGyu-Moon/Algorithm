import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n;
	static int[] ant;
	static int[] ans;
	static HashMap<Integer, List<Pair>> map;

	static int[] depth;
	static boolean[] visited;

	public static void generateDepth(int num) {
		List<Pair> list = map.get(num);
		for (Pair pair : list) {
			if (visited[pair.to])
				continue;
			depth[pair.to] = depth[num] + 1;
			visited[pair.to] = true;
			generateDepth(pair.to);
		}
	}

	public static int move(int num, int power) {
		Pair top = map.get(num).get(0);
		int curr = num;
		while (true) {
			int next = top.to;
			int d = top.d;
			power = power - d;
			if (power < 0) {
				return curr;
			}
			if (next == 1) {
				return next;
			}
			top = map.get(next).get(0);
			curr = next;
		}
	}

	public static void sol() {

		for (int i = 1; i <= n; i++) {
			Collections.sort(map.get(i), (a, b) -> {
				return Integer.compare(depth[a.to], depth[b.to]);
			});
		}

		ans[1] = 1;
		for (int i = 2; i <= n; i++) {
			ans[i] = move(i, ant[i]);
		}
	}

	public static void init() throws Exception {
		n = Integer.parseInt(input.readLine());
		ant = new int[n + 1];
		ans = new int[n + 1];
		depth = new int[n + 1];
		visited = new boolean[n + 1];
		for (int i = 1; i <= n; i++) {
			ant[i] = Integer.parseInt(input.readLine());
		}
		map = new HashMap<Integer, List<Pair>>();
		for (int i = 0; i < n - 1; i++) {
			token = new StringTokenizer(input.readLine());
			int from = Integer.parseInt(token.nextToken());
			int to = Integer.parseInt(token.nextToken());
			int d = Integer.parseInt(token.nextToken());

			// 큰수에서 작은수로 가는 방향만 만들어도 될듯
			if (map.get(from) == null)
				map.put(from, new ArrayList<>());
			map.get(from).add(new Pair(to, d));

			if (map.get(to) == null)
				map.put(to, new ArrayList<>());
			map.get(to).add(new Pair(from, d));
		}
	}

	public static void main(String[] args) throws Exception {
		init();
		generateDepth(1);
		sol();
		for (int i = 1; i <= n; i++) {
			output.append(ans[i]).append("\n");
		}
		System.out.println(output);
	}

	static class Pair {
		int to;
		int d;

		public Pair(int to, int d) {
			this.to = to;
			this.d = d;
		}

	}

}