import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int r, c, k;
	static int[][] arr;
	static int[] num;
	static int ans;
	static int y, x;

	static PriorityQueue<Pair> pq;

	public static void makeNewC(int j) {
		int idx = 1;
		while (!pq.isEmpty()) {
			Pair pair = pq.poll();
			if (idx > 100)
				return;
			arr[idx][j] = pair.val;
			arr[idx + 1][j] = pair.cnt;
			idx += 2;
		}
		y = Math.max(y, idx - 1);
		for (int k = idx; k <= 100; k++) {
			arr[k][j] = 0;
		}
	}

	public static void makeNewR(int i) {
		int idx = 1;
		while (!pq.isEmpty()) {
			Pair pair = pq.poll();
			if (idx > 100)
				return;
			arr[i][idx] = pair.val;
			arr[i][idx + 1] = pair.cnt;
			idx += 2;
		}
		x = Math.max(x, idx - 1);
		for (int k = idx; k <= 100; k++) {
			arr[i][k] = 0;
		}
	}

	public static void addNumToPQ() {
		pq.clear();
		for (int i = 1; i <= 100; i++) {
			if (num[i] == 0)
				continue;
			pq.add(new Pair(i, num[i]));
		}
	}

	public static void cal() {
		if (y >= x) {
			rCal(y, x);
		} else {
			cCal(y, x);
		}
	}

	public static void rCal(int row, int col) {
		for (int i = 1; i <= row; i++) {
			num = new int[101];
			for (int j = 1; j <= col; j++) {
				int val = arr[i][j];
				num[val]++;
			}
			addNumToPQ();
			makeNewR(i);
		}

	}

	public static void cCal(int row, int col) {
		for (int j = 1; j <= col; j++) {
			num = new int[101];
			for (int i = 1; i <= row; i++) {
				int val = arr[i][j];
				num[val]++;
			}
			addNumToPQ();
			makeNewC(j);
		}
	}

	public static void sol() {
		while (ans <= 100) {
			if (arr[r][c] == k) {
				output.append(ans);
				return;
			}
			cal();
			ans++;
		}
		output.append(-1);
	}

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		r = Integer.parseInt(token.nextToken());
		c = Integer.parseInt(token.nextToken());
		k = Integer.parseInt(token.nextToken());
		arr = new int[101][101];
		for (int i = 1; i <= 3; i++) {
			token = new StringTokenizer(input.readLine());
			for (int j = 1; j <= 3; j++) {
				arr[i][j] = Integer.parseInt(token.nextToken());
			}
		}
		ans = 0;
		y = x = 3;
		num = new int[101];
		pq = new PriorityQueue<Pair>((p1, p2) -> {
			if (p1.cnt == p2.cnt) {
				return Integer.compare(p1.val, p2.val);
			} else {
				return Integer.compare(p1.cnt, p2.cnt);
			}
		});

	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
		System.out.println(output);
	}

	static class Pair {
		int val;
		int cnt;

		public Pair(int val, int cnt) {
			this.val = val;
			this.cnt = cnt;
		}
	}
}