import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n;
	static Map<Integer, Integer> map;

	static int max;
	static int currMax;
	static int start, end;

	public static void sol() {
		boolean flag = false;
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			int key = entry.getKey();
			int value = entry.getValue();

			if (value > 0) {
				currMax += value;
//				start = key;
//				max = Math.max(max, currMax);
				if (max < currMax) {
					max = currMax;
					start = key;
					flag = true;
				}
			} else if (value < 0) {
//				if (max == currMax) {
				if (flag && max == currMax) {
					end = key;
					flag = false;
				}
				currMax += value;
			}

		}
		output.append(max).append("\n").append(start).append(" ").append(end);
	}

	public static void init() throws Exception {
		n = Integer.parseInt(input.readLine());
		map = new TreeMap<Integer, Integer>();
		for (int i = 0; i < n; i++) {
			token = new StringTokenizer(input.readLine());
			int a = Integer.parseInt(token.nextToken());
			int b = Integer.parseInt(token.nextToken());
			map.put(a, map.getOrDefault(a, 0) + 1);
			map.put(b, map.getOrDefault(b, 0) - 1);
		}
		max = Integer.MIN_VALUE;
		currMax = 0;
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
		System.out.println(output);
	}

}