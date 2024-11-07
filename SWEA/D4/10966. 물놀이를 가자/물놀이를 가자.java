import java.io.*;
import java.util.*;

public class Solution {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	static int n, m;
	static boolean[][] arr;

	static int[][] visited;

	static Deque<Pair> que;

	public static void sol() {
		while (!que.isEmpty()) {
			Pair front = que.poll();
			for (int i = 0; i < 4; i++) {
				int ny = front.first + dy[i];
				int nx = front.second + dx[i];
				if (ny < 0 || nx < 0 || ny >= n || nx >= m)
					continue;
				if (arr[ny][nx])
					continue;
				arr[ny][nx] = true;
				visited[ny][nx] = visited[front.first][front.second] + 1;
				que.add(new Pair(ny, nx));
			}
		}
	}

	public static void init() throws IOException {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		m = Integer.parseInt(token.nextToken());

		arr = new boolean[n][m];
		visited = new int[n][m];
		que = new ArrayDeque<Pair>();

		for (int i = 0; i < n; i++) {
			String temp = input.readLine();
			for (int j = 0; j < m; j++) {
				char c = temp.charAt(j);
				if (c == 'W') {
					arr[i][j] = true;
					que.add(new Pair(i, j));
				} else if (c == 'L') {
					arr[i][j] = false;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(input.readLine());
		for (int t = 1; t <= T; t++) {
			init();
			sol();
			int sum = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					sum += visited[i][j];
				}
			}
			output.append("#").append(t).append(" ").append(sum).append("\n");
		}
		System.out.println(output);
	}

	static class Pair {
		int first;
		int second;

		public Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}

	}

}