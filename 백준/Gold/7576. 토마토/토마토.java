import java.io.*;
import java.util.*;

public class Main {

	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n, m;
	static int[][] arr;
	static Deque<int[]> que;
	static int[][] visited;

	public static void sol() {
		while (!que.isEmpty()) {
			int[] top = que.poll();
			int y = top[0];
			int x = top[1];
			for (int i = 0; i < 4; i++) {
				int ny = y + dy[i];
				int nx = x + dx[i];
				if (ny < 0 || nx < 0 || ny >= n || nx >= m)
					continue;
				if (visited[ny][nx] != 0 || arr[ny][nx] == -1)
					continue;
				que.add(new int[] { ny, nx });
				visited[ny][nx] = visited[y][x] + 1;
			}
		}
		int ans = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (visited[i][j] == 0) {
					System.out.println(-1);
					return;
				}
				ans = Math.max(ans, visited[i][j]);
			}
		}
		System.out.println(ans - 1);
	}

	public static void init() throws Exception {
		que = new ArrayDeque<>();
		token = new StringTokenizer(input.readLine());
		m = Integer.parseInt(token.nextToken());
		n = Integer.parseInt(token.nextToken());
		arr = new int[n][m];
		visited = new int[n][m];
		for (int i = 0; i < n; i++) {
			token = new StringTokenizer(input.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(token.nextToken());
				if (arr[i][j] == 1) {
					que.add(new int[] { i, j });
					visited[i][j] = 1;
				} else if (arr[i][j] == -1) {
					visited[i][j] = -1;
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
	}
}