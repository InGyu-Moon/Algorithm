import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n, m;
	static int power, len;
	static int[] tree;
	static List<String> list;
	static String[] inputArr;

	public static int findIdx() {
		int parent = 1;
		int left = parent * 2, right = parent * 2 + 1;
		while (parent < len) {
			if (tree[parent] == tree[left]) {
				parent = left;
			} else {
				parent = right;
			}
			left = parent * 2;
			right = parent * 2 + 1;
		}
		return parent - len + 1;
	}

	public static void updateTree(int idx) {

		int parent = idx / 2;
		int left, right;

		while (parent > 0) {
			left = parent * 2;
			right = parent * 2 + 1;
			tree[parent] = Math.min(tree[left], tree[right]);
			parent /= 2;
		}

	}

	public static void initTree() {
		for (int i = len * 2 - 1; i > 1; i -= 2) {
			int parent = i / 2;
			tree[parent] = Math.min(tree[i], tree[i - 1]);
		}
	}

	public static void sol() {
		for (String str : inputArr) {
			token = new StringTokenizer(str);
			String in = token.nextToken();
			if (in.equals("1")) {
				// update 수행
				int i = Integer.parseInt(token.nextToken());
				int v = Integer.parseInt(token.nextToken());
				tree[len + i - 1] = v;
				updateTree(len + i - 1);
			} else if (in.equals("2")) {
				// 최소값의 index를 output 에 추가
				int ans = findIdx();
				output.append(ans).append("\n");
			}
		}
	}

	public static void init() throws Exception {
		n = Integer.parseInt(input.readLine());
		power = 0;
		while ((1 << power) < n) {
			power++;
		}
		len = (int) Math.pow(2, power);
		tree = new int[len * 2];
		token = new StringTokenizer(input.readLine());
		for (int i = 0; i < n; i++) {
			tree[len + i] = Integer.parseInt(token.nextToken());
		}
		for (int i = 0; i < len - n; i++) {
			tree[len + n + i] = Integer.MAX_VALUE;
		}
		list = new ArrayList<>();
		m = Integer.parseInt(input.readLine());
		inputArr = new String[m];
		for (int i = 0; i < m; i++) {
			inputArr[i] = input.readLine();
		}
		initTree();
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
		System.out.println(output);
	}

}