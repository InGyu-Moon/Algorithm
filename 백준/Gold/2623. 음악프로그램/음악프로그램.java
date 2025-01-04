import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n, m;
	static int[] singers;

	static Map<Integer, List<Integer>> map;
	static Deque<Integer> que;

	public static void sol() {
		// 사이클 있는지 확인

		for (int i = 1; i <= n; i++) {
			if (singers[i] == 0) {
				que.add(i);
				output.append(i).append("\n");
			}
		}
		while (!que.isEmpty()) {
			int top = que.poll();
			List<Integer> list = map.getOrDefault(top, new ArrayList<>());
			for (int next : list) {
				singers[next]--;
				if (singers[next] == 0) {
					que.add(next);
					output.append(next).append("\n");
				}
			}
		}
		if (check()) {
			System.out.println(output);
		} else {
			System.out.println(0);
		}

	}

	public static boolean check() {
		for (int i = 1; i <= n; i++) {
			if (singers[i] != 0)
				return false;
		}
		return true;
	}

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		m = Integer.parseInt(token.nextToken());
		singers = new int[n + 1];
		map = new HashMap<>();
		que = new ArrayDeque<>();
		for (int i = 0; i < m; i++) {
			token = new StringTokenizer(input.readLine());
			int cnt = Integer.parseInt(token.nextToken());
			int[] tempArr = new int[cnt];
			for (int j = 0; j < cnt; j++) {
				int num = Integer.parseInt(token.nextToken());
				tempArr[j] = num;
			}

			for (int j = 0; j < cnt - 1; j++) {
				int from = tempArr[j];
				int to = tempArr[j + 1];
//				map.getOrDefault(from, new ArrayList<>()).add(to);
				if (map.get(from) == null) {
					map.put(from, new ArrayList<>());
				}
				map.get(from).add(to);
				singers[to]++;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
	}
}