import java.io.*;
import java.util.*;

public class Main {

	static int[] dy = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] dx = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static char[][] arr;
	static int n, m, k;

	static char[] chars, selected;
	static int ans;

	static Deque<Pair> que;

	static Map<String, Integer> map;

	public static void sol() throws Exception {
		for (int i = 0; i < k; i++) {
			String word = input.readLine();
			output.append(map.getOrDefault(word, 0)).append("\n");
		}
	}

	public static void make() {
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < m; c++) {
				que = new ArrayDeque<Pair>();
				que.add(new Pair(r, c, String.valueOf(arr[r][c])));
				while (!que.isEmpty()) {
					Pair front = que.poll();
					if (front.str.length() == 5)
						break;
					for (int i = 0; i < 8; i++) {
						int ny = front.y + dy[i];
						int nx = front.x + dx[i];
						ny = (ny + n) % n;
						nx = (nx + m) % m;
						String temp = front.str + String.valueOf(arr[ny][nx]);
						map.put(temp, map.getOrDefault(temp, 0) + 1);
						que.add(new Pair(ny, nx, temp));
//						if (true) {
//							System.out.println(
//									"str: " + temp + ", cnt: " + map.getOrDefault(temp, 0) + ", r: " + r + ", c: " + c);
//						}

					}
				}
			}
		}

	}

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		m = Integer.parseInt(token.nextToken());
		k = Integer.parseInt(token.nextToken());
		arr = new char[n][m];
		map = new HashMap<String, Integer>();
		for (int i = 0; i < n; i++) {
			String temp = input.readLine();
			for (int j = 0; j < m; j++) {
				arr[i][j] = temp.charAt(j);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		init();
		make();
		sol();
		System.out.println(output);
	}

	static class Pair {
		int y;
		int x;
		String str;

		public Pair(int y, int x, String str) {
			this.y = y;
			this.x = x;
			this.str = str;
		}

	}
}