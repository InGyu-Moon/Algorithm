import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int[] dy = { 0, -1, 0, 1 };
	static int[] dx = { 1, 0, -1, 0 };

	static int INF = 987654321;

	public static int n, m;
	public static int[][] arr;
	public static int[][] visited;
	static int ans = Integer.MAX_VALUE;

	public static Deque<int[]> que = new ArrayDeque<>();

	public static void sol() {
		que.add(new int[] { 1, 1 });
		while (!que.isEmpty()) {
			int[] top = que.poll();
			int y = top[0];
			int x = top[1];
			for (int i = 0; i < 4; i++) {
				int ny = y + dy[i];
				int nx = x + dx[i];
				if (ny == 0 || nx == 0 || ny == n + 1 || nx == m + 1)
					continue;
				if (visited[y][x] + arr[ny][nx] >= visited[ny][nx])
					continue;
				visited[ny][nx] = visited[y][x] + arr[ny][nx];
//				que.add(new int[] { ny, nx });
				if(arr[ny][nx]==0) {
					que.addFirst(new int[] { ny, nx });
				}else {
					que.addLast(new int[] { ny, nx });
				}
			}
		}

	}

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		m = Integer.parseInt(token.nextToken());
		n = Integer.parseInt(token.nextToken());
		arr = new int[n + 1][m + 1];
		visited = new int[n + 1][m + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				visited[i][j] = INF;
			}
		}
		for (int i = 1; i <= n; i++) {
			String temp = input.readLine();
			for (int j = 1; j <= m; j++) {
				arr[i][j] = temp.charAt(j-1) - '0';
			}
		}
		// 초기화
		visited[1][1] = 0;
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
		System.out.println(visited[n][m]);
	}
}
