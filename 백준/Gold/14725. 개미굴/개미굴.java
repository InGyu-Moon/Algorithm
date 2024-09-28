import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n;
	static List<List<String>> lists;
	static Node root = new Node();

	public static void dfs(int depth, Node node) {
		for (Map.Entry<String, Node> entry : node.child.entrySet()) {
			String key = entry.getKey();
			Node temp = entry.getValue();
			if (temp.visited)
				continue;
			temp.visited = true;
			for (int i = 0; i < depth; i++)
				output.append("--");
			output.append(key).append("\n");
			if (temp.isEnd)
				continue;
			dfs(depth + 1, temp);
		}
		return;
	}

	public static void sol() {
		// hashMap 에 저장하기
		Node curr;
		for (List<String> list : lists) {
			curr = root;
			int len = 0;
			for (String str : list) {
				if (!curr.child.containsKey(str)) {
					curr.child.put(str, new Node());
				}
				curr = curr.child.get(str);
				len++;
				if (len == list.size()) {
					curr.isEnd = true;
				}
			}
		}

	}

	public static void init() throws Exception {
		n = Integer.parseInt(input.readLine());
		lists = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			token = new StringTokenizer(input.readLine());
			int temp = Integer.parseInt(token.nextToken());
			lists.add(new ArrayList<String>());
			for (int j = 0; j < temp; j++) {
				lists.get(i).add(token.nextToken());
			}
		}
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
		dfs(0, root);
		System.out.println(output);
	}

	static class Node {
		Map<String, Node> child;
		boolean isEnd;
		boolean visited;

		public Node() {
			child = new TreeMap<>();

		}

		public Node(Map<String, Node> map) {
			this.child = map;
			this.isEnd = false;
		}

	}

}