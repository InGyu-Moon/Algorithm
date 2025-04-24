import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n, m, k;
	static Map<Integer, List<Integer>> map;
	static int[] build, preCount;

	public static void sol() {

	}

	public static void init() throws Exception {
		map = new HashMap<>();
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		m = Integer.parseInt(token.nextToken());
		k = Integer.parseInt(token.nextToken());
		build = new int[n + 1];
		preCount = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			map.put(i, new ArrayList<>());
		}
		for (int i = 0; i < m; i++) {
			token = new StringTokenizer(input.readLine());
			int a = Integer.parseInt(token.nextToken());
			int b = Integer.parseInt(token.nextToken());
//			if (map.get(a) == null) // a를 건설해야 b를 건설할 수 있음
//				map.put(a, new ArrayList<>());
			map.get(a).add(b);
			preCount[b]++;
		}
		for (int i = 0; i < k; i++) {
			token = new StringTokenizer(input.readLine());
			int type = Integer.parseInt(token.nextToken());
			int num = Integer.parseInt(token.nextToken());
			if (type == 1) {
				if (preCount[num] > 0) {
					output.append("Lier!");
					return;
				}
				build[num]++;
				if (build[num] == 1) {
					for (int next : map.get(num)) {
						preCount[next]--;
					}
				}
			} else if (type == 2) {
				if (build[num] == 0) {
					output.append("Lier!");
					return;
				}
				build[num]--;
				if (build[num] == 0) {
					for (int next : map.get(num)) {
						preCount[next]++;
					}
				}
			}
		}
		output.append("King-God-Emperor");
	}

	public static void main(String[] args) throws Exception {
		init();
		System.out.println(output);
	}
}