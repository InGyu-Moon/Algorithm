import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n, m;
	static int[] d;
	static Deque<Integer> que;
	static Map<Integer, List<Integer>> map;
	static List<Integer> result;

	public static void sol() {
		for (int i = 1; i <= n; i++) {
			if (d[i] == 0)
				que.add(i);
		}
		while (!que.isEmpty()) {
			int top = que.poll();
			result.add(top);
			List<Integer> list = map.getOrDefault(top, new ArrayList<>());
			for (Integer i : list) {
				d[i]--;
				if (d[i] == 0)
					que.add(i);
			}
		}
		// 결과
		for (Integer i : result) {
			output.append(i).append(" ");
		}
	}

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		m = Integer.parseInt(token.nextToken());
		d = new int[n + 1];
		que = new ArrayDeque<>();
		map = new HashMap<Integer, List<Integer>>();
		result = new ArrayList<Integer>();

		for (int i = 0; i < m; i++) {
			token = new StringTokenizer(input.readLine());
			int a = Integer.parseInt(token.nextToken());
			int b = Integer.parseInt(token.nextToken());
			d[b]++;
//			map.getOrDefault(a, new ArrayList<Integer>()).add(b);
			if (map.get(a) == null) {
				map.put(a, new ArrayList<>());
			}
			map.get(a).add(b);
		}
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
		System.out.println(output);
	}

}