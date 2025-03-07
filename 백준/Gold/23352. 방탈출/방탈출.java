
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
	static boolean[][] visited;
	static int ans, curr;

	static List<int[]> list;
	static Deque<int[]> que;

	public static void sol() {
		int ans = Integer.MIN_VALUE;
		for (int[] pos : list) {
			// visited 배열 초기화
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					visited[i][j] = false;
				}
			}

			int start = arr[pos[0]][pos[1]];
			que.add(new int[] { pos[0], pos[1], 0 });
			visited[pos[0]][pos[1]] = true;

			while (!que.isEmpty()) {
				int[] top = que.poll();
				int y = top[0];
				int x = top[1];
				int idx = top[2];
				for (int i = 0; i < 4; i++) {
					int ny = y + dy[i];
					int nx = x + dx[i];
					if (ny < 0 || nx < 0 || ny >= n || nx >= m)
						continue;
					if (visited[ny][nx] || arr[ny][nx] == 0)
						continue;
					visited[ny][nx] = true;
					if (curr == idx) {
						ans = Math.max(ans, start + arr[ny][nx]);
					} else if (curr < idx) {
						curr = idx;
						ans = start + arr[ny][nx];
					}
					que.add(new int[] { ny, nx, idx + 1 });
				}
			}
		}
		System.out.println(ans);
	}

	public static void init() throws Exception {
		list = new ArrayList<>();
		que = new ArrayDeque<>();

		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		m = Integer.parseInt(token.nextToken());

		visited = new boolean[n][m];
		arr = new int[n][m];
		for (int i = 0; i < n; i++) {
			token = new StringTokenizer(input.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(token.nextToken());
				if (arr[i][j] != 0)
					list.add(new int[] { i, j });
			}
		}

	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
	}
}
