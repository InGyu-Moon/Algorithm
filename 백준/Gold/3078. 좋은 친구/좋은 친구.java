
import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n, k; 
	static long ans;
	static Map<Integer, Deque<Integer>> map;

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		k = Integer.parseInt(token.nextToken());

		map = new HashMap<>();

		for (int i = 1; i <= 20; i++) {
			map.put(i, new ArrayDeque<>());
		}

		for (int i = 1; i <= n; i++) {
			String str = input.readLine();
			int size = str.length();
			Deque<Integer> que = map.get(size);
			while (!que.isEmpty()) {
				int first = que.peek();
				if (i - first > k) {
					que.pop();
				} else {
					ans += que.size();
					break;
				}
			}
			map.get(size).add(i);
		}
		System.out.println(ans);
	}

	public static void main(String[] args) throws Exception {
		init();
	}

}
