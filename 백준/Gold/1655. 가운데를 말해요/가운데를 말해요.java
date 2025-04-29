import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n;
	static List<Integer> list;
	static int a, b;

	public static void init() throws Exception {
		PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> right = new PriorityQueue<>();
		n = Integer.parseInt(input.readLine());

		for (int i = 0; i < n; i++) {
			int num = Integer.parseInt(input.readLine());

			if (left.isEmpty() || num <= left.peek()) {
				left.add(num);
			} else {
				right.add(num);
			}

			if (left.size() == right.size() + 2) {
				right.add(left.poll());
			} else if (left.size() + 1 == right.size()) {
				left.add(right.poll());
			}

			output.append(left.peek()).append("\n");
		}
	}

	public static void main(String[] args) throws Exception {
		init();
		System.out.println(output);
	}
}