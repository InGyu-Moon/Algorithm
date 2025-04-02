import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n, q, len, size;
	static int parent, left, right;
	static int x, y, a, b;
	static long sum;
	static long[] tree;
	static String[] queries;

	public static void getSum(int start, int end) {
		sum = 0;
		start = len + start - 1;
		end = len + end - 1;
		if (start > end) {
			int temp = start;
			start = end;
			end = temp;
		}
		while (start <= end) {
			if (start % 2 == 1) {
				sum += tree[start];
				start++;
			}
			if (end % 2 == 0) {
				sum += tree[end];
				end--;
			}
			start /= 2;
			end /= 2;
		}
	}

	public static void updateTree(int idx) {
		parent = idx / 2;
		while (parent > 0) {
			left = parent * 2;
			right = parent * 2 + 1;
			tree[parent] = tree[left] + tree[right];
			parent /= 2;
		}
	}

	public static void sol() {
		for (String query : queries) {
			token = new StringTokenizer(query);
			x = Integer.parseInt(token.nextToken());
			y = Integer.parseInt(token.nextToken());
			a = Integer.parseInt(token.nextToken());
			b = Integer.parseInt(token.nextToken());
			// x~y 까지의 합
			getSum(x, y);
			output.append(sum).append("\n");
			// tree[a] = b 로 수정
			tree[len + a - 1] = b;
			updateTree(len + a - 1);
		}
	}

	public static void initTree() {
		for (int i = size - 1; i > 1; i -= 2) {
			parent = i / 2;
			left = parent * 2;
			right = parent * 2 + 1;
			tree[parent] = tree[left] + tree[right];
		}
	}

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		q = Integer.parseInt(token.nextToken());

		len = 1 << (int) (Math.log(n) / Math.log(2));
		len = len < n ? len * 2 : len;
		size = len * 2;

		tree = new long[size];
		queries = new String[q];
		token = new StringTokenizer(input.readLine());

		for (int i = 0; i < n; i++) {
			tree[len + i] = Integer.parseInt(token.nextToken());
		}
		for (int i = 0; i < q; i++) {
			queries[i] = input.readLine();
		}
		initTree();
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
		System.out.println(output);
	}
}