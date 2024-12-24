import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n, m;
	static int len, size;
	static int level, start, end, idx;
	static int before, curr;
	static long[] tree;
	static Deque<String> query1;
	static PriorityQueue<Pair> pq;
	static long[] ans;

	public static void sol() {
		before = 0;
		while (!pq.isEmpty()) {
			Pair pair = pq.poll();
			curr = pair.level;
			for (int i = 0; i < curr - before; i++) {
				// 1번 쿼리 실행
				token = new StringTokenizer(query1.poll());
				token.nextToken();
				int a = Integer.parseInt(token.nextToken());
				int b = Integer.parseInt(token.nextToken());
				tree[len + a - 1] = b;
				updateTree(len + a - 1);
			}
			before = curr;

			// 2번 쿼리 실행
			ans[pair.idx] = getSum(len + pair.start - 1, len + pair.end - 1);
		}
		// 출력
		for (long num : ans) {
			output.append(num).append("\n");
		}
	}

	public static long getSum(int left, int right) {
		long sum = 0;
		while (left <= right) {
			if (left % 2 == 1) {
				sum += tree[left];
				left++;
			}
			if (right % 2 == 0) {
				sum += tree[right];
				right--;
			}
			left /= 2;
			right /= 2;
		}
		return sum;
	}

	public static void updateTree(int num) {
		int parent = num / 2;
		while (parent > 0) {
			tree[parent] = tree[parent * 2] + tree[parent * 2 + 1];
			parent /= 2;
		}
	}

	public static void initTree() {
		for (int i = len - 1; i >= 1; i--) {
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}

	public static void init() throws Exception {
		n = Integer.parseInt(input.readLine());
		len = 1 << (int) (Math.log(n) / Math.log(2));
		if (len < n)
			len *= 2;
		size = len * 2;
		tree = new long[size];
		token = new StringTokenizer(input.readLine());
		for (int i = 0; i < n; i++) {
			tree[len + i] = Integer.parseInt(token.nextToken());
		}
		m = Integer.parseInt(input.readLine());
		query1 = new ArrayDeque<>();
		pq = new PriorityQueue<>((p1, p2) -> {
			return Integer.compare(p1.level, p2.level);
		});
		idx = 0;
		for (int i = 0; i < m; i++) {
			String str = input.readLine();
			if (str.startsWith("1")) {
				query1.add(str);
			} else {
				token = new StringTokenizer(str);
				token.nextToken();
				level = Integer.parseInt(token.nextToken());
				start = Integer.parseInt(token.nextToken());
				end = Integer.parseInt(token.nextToken());
				pq.add(new Pair(level, start, end, idx++));
			}
		}
		ans = new long[idx];
		initTree();
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
		System.out.println(output);
	}

	static class Pair {
		int level;
		int start;
		int end;
		int idx;

		public Pair(int level, int start, int end, int idx) {
			this.level = level;
			this.start = start;
			this.end = end;
			this.idx = idx;
		}

	}
}