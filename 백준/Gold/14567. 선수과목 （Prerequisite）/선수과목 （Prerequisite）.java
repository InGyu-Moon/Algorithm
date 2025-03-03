import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n, m;
	static int[] d, ans;
	static Map<Integer, List<Integer>> map;
	static Deque<int[]> que;

	public static void sol() {
		que = new ArrayDeque<>();
		for (int i = 1; i <= n; i++) {
			if (d[i] == 0)
				que.add(new int[] { i, 1 });
		}
		while (!que.isEmpty()) {
			int[] top = que.poll();
			ans[top[0]] = top[1];
			for (int num : map.get(top[0])) {
				d[num]--;
				if (d[num] == 0)
					que.add(new int[] { num, top[1] + 1 });
			}
		}
		for (int i = 1; i <= n; i++) {
			output.append(ans[i]).append(" ");
		}
	}

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		m = Integer.parseInt(token.nextToken());
		map = new HashMap<>();
		d = new int[n + 1];
		ans = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			map.put(i, new ArrayList<>());
		}
		for (int i = 0; i < m; i++) {
			token = new StringTokenizer(input.readLine());
			int a = Integer.parseInt(token.nextToken());
			int b = Integer.parseInt(token.nextToken());
			map.get(a).add(b);
			d[b]++;
		}

	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
		System.out.println(output);
	}
}