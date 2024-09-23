import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static final int UP = 0;
	static final int RIGHT = 1;
	static final int DOWN = 2;
	static final int LEFT = 3;

	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	static int[][] arr;
	static boolean[][] visited;
	static Queue<Pair> que = new LinkedList<>();

	static int n, m;
	static int cnt;

	public static void bfs(int y, int x) {
		que.add(new Pair(y, x));
		visited[y][x] = true;
		while (!que.isEmpty()) {
			Pair top = que.poll();
			int dir = arr[top.y][top.x]; // top arr의 방향
			int currY = top.y + dy[dir];
			int currX = top.x + dx[dir];
			if (!visited[currY][currX]) {
				visited[currY][currX] = true;
				que.add(new Pair(currY, currX));
			}
			for (int i = 0; i < 4; i++) {
				int ny = top.y + dy[i];
				int nx = top.x + dx[i];
				if (ny < 0 || nx < 0 || ny >= n || nx >= m)
					continue;
				if (visited[ny][nx])
					continue;
				if (arr[ny][nx] == (i + 2) % 4) {
					visited[ny][nx] = true;
					que.add(new Pair(ny, nx));
				}
			}
		}
	}

	public static void sol() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (visited[i][j])
					continue;
				bfs(i, j);
				cnt++;
			}
		}
	}

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		m = Integer.parseInt(token.nextToken());
		arr = new int[n][m];
		visited = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			String temp = input.readLine();
			for (int j = 0; j < m; j++) {
				if (temp.charAt(j) == 'U')
					arr[i][j] = UP;
				else if (temp.charAt(j) == 'R')
					arr[i][j] = RIGHT;
				else if (temp.charAt(j) == 'D')
					arr[i][j] = DOWN;
				else if (temp.charAt(j) == 'L')
					arr[i][j] = LEFT;
			}
		}

	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
		System.out.println(cnt);
	}

	static class Pair {
		int y;
		int x;

		public Pair(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}
}
