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
	static int cnt, ans;

	static Deque<int[]> que = new ArrayDeque<>();

	public static void bfs() {
		boolean[][] visited = new boolean[n][m];
		visited[0][0] = true;
		que.add(new int[] { 0, 0 });
		cnt = 0;
		while (!que.isEmpty()) {
			int[] top = que.poll();
			int y = top[0];
			int x = top[1];
			for (int i = 0; i < 4; i++) {
				int ny = y + dy[i];
				int nx = x + dx[i];
				if (ny < 0 || nx < 0 || ny >= n || nx >= m || visited[ny][nx])
					continue;
				if (arr[ny][nx] == 1) {
					arr[ny][nx] = 2;
					cnt++;
				} else {
					que.add(new int[] { ny, nx });
				}
				visited[ny][nx] = true;
			}
		}
		// 치즈 삭제
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr[i][j] == 2)
					arr[i][j] = 0;
			}
		}
	}

	public static void sol() {
		int time = 0;
		while (true) {
			bfs();
			if (cnt == 0) {
				output.append(time).append("\n").append(ans);
				break;
			}
			ans = cnt;
			time++;
		}
		System.out.println(output);

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
