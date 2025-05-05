import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	static Deque<int[]> que = new ArrayDeque<>();
	static int n, m;
	static int[][] arr;
	static boolean[][] visited;
	static int year;

//	public static void sol() {
//		year = 0;
//		while (true) {
//			afterYear();
//			year++;
//			if (checkIce() > 1) {
//				System.out.println(year);
//				return;
//			}
//		}
//	}
	public static void sol() {
		year = 0;
		while (true) {
			int iceCount = checkIce();
			if (iceCount == 0) {
				System.out.println(0);
				return;
			}
			if (iceCount > 1) {
				System.out.println(year);
				return;
			}
			afterYear();
			year++;
		}
	}

//	public static int checkIce() {
//		int num = 0;
//		visited = new boolean[n][m];
//		que.add(new int[] { 0, 0 });
//		visited[0][0] = true;
//		while (!que.isEmpty()) {
//			int[] top = que.poll();
//			for (int i = 0; i < 4; i++) {
//				int ny = top[0] + dy[i];
//				int nx = top[1] + dx[i];
//				if (ny < 0 || nx < 0 || ny >= n || nx >= m)
//					continue;
//				if (visited[ny][nx])
//					continue;
//				if (arr[ny][nx] != 0) {
//					num++;
//					dfs(ny, nx);
//				} else {
//					visited[ny][nx] = true;
//					que.add(new int[] { ny, nx });
//				}
//			}
//		}
//		return num;
//	}

	public static int checkIce() {
		visited = new boolean[n][m];
		int count = 0;

		for (int y = 0; y < n; y++) {
			for (int x = 0; x < m; x++) {
				if (!visited[y][x] && arr[y][x] > 0) {
					dfs(y, x);
					count++;
				}
			}
		}
		return count;
	}

	public static void dfs(int y, int x) {
		visited[y][x] = true;
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if (ny < 0 || nx < 0 || ny >= n || nx >= m)
				continue;
			if (visited[ny][nx])
				continue;
			if (arr[ny][nx] == 0)
				continue;
			dfs(ny, nx);
		}
	}

//	public static void afterYear() {
//		visited = new boolean[n][m];
//		que.add(new int[] { 0, 0 });
//		visited[0][0] = true;
//		while (!que.isEmpty()) {
//			int[] top = que.poll();
//			for (int i = 0; i < 4; i++) {
//				int ny = top[0] + dy[i];
//				int nx = top[1] + dx[i];
//				if (ny < 0 || nx < 0 || ny >= n || nx >= m)
//					continue;
//				if (visited[ny][nx])
//					continue;
//				if (arr[ny][nx] != 0)
//					arr[ny][nx]--;
//				visited[ny][nx] = true;
//				que.add(new int[] { ny, nx });
//			}
//		}
//	}

	public static void afterYear() {
		int[][] melt = new int[n][m];

		for (int y = 0; y < n; y++) {
			for (int x = 0; x < m; x++) {
				if (arr[y][x] > 0) {
					int sea = 0;
					for (int d = 0; d < 4; d++) {
						int ny = y + dy[d];
						int nx = x + dx[d];
						if (ny < 0 || nx < 0 || ny >= n || nx >= m)
							continue;
						if (arr[ny][nx] == 0)
							sea++;
					}
					melt[y][x] = sea;
				}
			}
		}

		// 녹이기 (한 번에)
		for (int y = 0; y < n; y++) {
			for (int x = 0; x < m; x++) {
				arr[y][x] = Math.max(arr[y][x] - melt[y][x], 0);
			}
		}
	}

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		m = Integer.parseInt(token.nextToken());
		arr = new int[n][m];
		for (int i = 0; i < n; i++) {
			token = new StringTokenizer(input.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(token.nextToken());
			}
		}
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
	}
}