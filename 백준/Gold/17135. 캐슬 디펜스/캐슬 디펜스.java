import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int[] dy = { 0, -1, 0 };
	static int[] dx = { -1, 0, 1 };

	static int n, m, d;
	static int[][] arr;

	static int[] selected = new int[3];
	static boolean[] visited;
	static List<int[]> list = new ArrayList<>();
	static int maxVal = 0;

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		m = Integer.parseInt(token.nextToken());
		d = Integer.parseInt(token.nextToken());
		arr = new int[n + 2][m + 2];
		visited = new boolean[m];
		for (int i = 0; i < n; i++) {
			token = new StringTokenizer(input.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(token.nextToken());
			}
		}
	}

	public static void dfs(int depth, int num) {
		if (depth == 3) {
			maxVal = Math.max(maxVal, game(selected));
			return;
		}

		for (int i = num; i < m; i++) {
			selected[depth] = i;
			dfs(depth + 1, i + 1);
		}
	}

	public static int game(int[] selected) {
		int ans = 0;
		int[][] map = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] = arr[i][j];
			}
		}

		for (int line = n; line >= 1; line--) {
			List<int[]> targets = new ArrayList<>();
			for (int i = 0; i < 3; i++) {
				boolean[][] visited = new boolean[n][m];
				int x = selected[i];
				boolean found = false;
				Queue<int[]> que = new ArrayDeque<>();
				que.add(new int[] { line, x, 0 });

				while (!found && !que.isEmpty()) {
					int[] top = que.poll();
					for (int j = 0; j < 3; j++) {
						int ny = top[0] + dy[j];
						int nx = top[1] + dx[j];
						int len = top[2] + 1;

						if (len > d || ny < 0 || nx < 0 || ny >= line || nx == m || visited[ny][nx])
							continue;

						visited[ny][nx] = true;

						if (map[ny][nx] == 1) {
							targets.add(new int[] { ny, nx });
							found = true;
							break;
						}
						que.add(new int[] { ny, nx, len });
					}
				}
			}

			for (int[] t : targets) {
				int y = t[0];
				int x = t[1];
				if (map[y][x] == 1) {
					map[y][x] = 0;
					ans++;
				}
			}
		}

		return ans;

	}

	public static void main(String[] args) throws Exception {
		init();
		dfs(0, 0);
		System.out.println(maxVal);
	}
}
