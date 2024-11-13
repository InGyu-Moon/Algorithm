import java.io.BufferedReader;
import java.io.*;
import java.util.*;

public class Solution {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	static int n;
	static int[][] arr;

	static Deque<Pair> que;
	static int[][] visited;
	static int INF = Integer.MAX_VALUE;

	public static void sol() {
		que.add(new Pair(0, 0));
		visited[0][0] = arr[0][0];
		while (!que.isEmpty()) {
			Pair top = que.poll();
			int y = top.y;
			int x = top.x;
			for (int i = 0; i < 4; i++) {
				int ny = y + dy[i];
				int nx = x + dx[i];
				if (nx < 0 || ny < 0 || nx >= n || ny >= n)
					continue;
				if (visited[ny][nx] > visited[y][x] + arr[ny][nx]) {
					visited[ny][nx] = visited[y][x] + arr[ny][nx];
					que.add(new Pair(ny, nx));
				}

			}
		}
	}

	public static void init() throws Exception {
		n = Integer.parseInt(input.readLine());
		arr = new int[n][n];

		for (int i = 0; i < n; i++) {
			String temp = input.readLine();
			for (int j = 0; j < n; j++) {
				arr[i][j] = temp.charAt(j) - '0';
			}
		}

		que = new ArrayDeque<Pair>();
		visited = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				visited[i][j] = INF;
			}
		}

	}

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(input.readLine());
		for (int t = 1; t <= T; t++) {
			init();
			sol();
			output.append("#").append(t).append(" ").append(visited[n - 1][n - 1]).append("\n");
		}
		System.out.println(output);
	}

	static class Pair {
		int y;
		int x;

		public Pair(int y, int x) {
			this.y = y;
			this.x = x;
		}

	}

}