import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n;
	static String[] arr;
	static Node root;

	public static void insert() {
		root = new Node();
		for (int i = 0; i < n; i++) {
			Node pointer = root;
			String str = arr[i];
			int depth = 0;
			while (true) {
				if (pointer.child.get(str.charAt(depth)) == null) {
					pointer.child.put(str.charAt(depth), new Node());
					pointer = pointer.child.get(str.charAt(depth));
					depth++;
				} else {
					if (pointer.child.get(str.charAt(depth)).isEnd == true) {
						output.append("NO\n");
						return;
					}
					pointer = pointer.child.get(str.charAt(depth));
					depth++;
				}
				if (depth == str.length()) {
					pointer.isEnd = true;
					break;
				}
			}
		}
		output.append("YES\n");
	}

	public static void init() throws Exception {
		n = Integer.parseInt(input.readLine());
		arr = new String[n];
		for (int i = 0; i < n; i++) {
			arr[i] = input.readLine();
		}
		Arrays.sort(arr, (a, b) -> {
			return Integer.compare(a.length(), b.length());
		});
		insert();

	}

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(input.readLine());
		for (int t = 1; t <= T; t++) {
			init();
		}
		System.out.println(output);
	}

	static class Node {
		HashMap<Character, Node> child;
		boolean isEnd;

		public Node() {
			super();
			this.child = new HashMap<>();
			this.isEnd = false;
		}

	}

}