import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int[] dy = { 0, 0, 1, -1 };
	static int[] dx = { 1, -1, 0, 0 };

//	static PriorityQueue<Pos> que;
	static Queue<Pos> que;
	static int n, m;
	static int[][][] visited;
	static int[][] arr;

	static int ans;
	static boolean flag;

	public static void sol() {

		while (!que.isEmpty()) {
			Pos top = que.poll();

			if (top.y == n && top.x == m) {
				flag = true;
				if (top.isBroken) {
					ans = Math.min(visited[n][m][1], ans);
				} else {
					ans = Math.min(visited[n][m][0], ans);
				}
			}

			for (int i = 0; i < 4; i++) {
				int ny = top.y + dy[i];
				int nx = top.x + dx[i];

				if (nx < 1 || ny < 1 || nx > m || ny > n)
					continue;

//				if (visited[ny][nx][0] != 0 && visited[ny][nx][1] != 0)
//					continue;

				if (arr[ny][nx] == 1) {// 벽이면
					if (top.isBroken || visited[ny][nx][1] != 0) // 이미 부셨으면 , 방문한적 있으면
						continue;
					visited[ny][nx][1] = visited[top.y][top.x][0] + 1;
					que.add(new Pos(ny, nx, true));
				}
				else if (arr[ny][nx] == 0) { // 벽이 아니면
					if (top.isBroken) { // 부순적이 있음
						if (visited[ny][nx][1] != 0) // 현재 부시고 진행중 이니깐 부신곳 방문 확인
							continue;
						visited[ny][nx][1] = visited[top.y][top.x][1] + 1;
					} else { // 부순적 없음
						if (visited[ny][nx][0] != 0)
							continue;
						visited[ny][nx][0] = visited[top.y][top.x][0] + 1;
					}
					que.add(new Pos(ny, nx, top.isBroken));
				}
			}
		}
	}

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		m = Integer.parseInt(token.nextToken());
		arr = new int[n + 1][m + 1];
		visited = new int[n + 1][m + 1][2];
		que = new LinkedList<Pos>();
		que.add(new Pos(1, 1, false));
		ans = 987654321;
		visited[1][1][0] = 1;
		for (int i = 1; i <= n; i++) {
			String str = input.readLine();
			for (int j = 1; j <= m; j++) {
				arr[i][j] = str.charAt(j - 1) - '0';
			}
		}
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
		if (!flag)
			System.out.println(-1);
		else {
			System.out.println(ans);
		}

	}

	static class Pos {
		int y;
		int x;
		boolean isBroken;

		public Pos(int y, int x, boolean isBroken) {
			this.y = y;
			this.x = x;
			this.isBroken = isBroken;
		}

	}
}