import java.io.*;
import java.util.*;

public class Main {

	static final int FIRE = 0;
	static final int USER = 1;

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	static Deque<int[]> que, fire;

	static int r, c;
	static char[][] arr;

	static int[][][] map;

	static int ans;

	public static void sol() {
		while (!fire.isEmpty()) {
			int[] top = fire.poll();
			int y = top[0];
			int x = top[1];
			for (int i = 0; i < 4; i++) {
				int ny = y + dy[i];
				int nx = x + dx[i];
				if (ny < 0 || nx < 0 || ny >= r || nx >= c)
					continue;
				if (arr[ny][nx] == '#' || map[FIRE][ny][nx] >= 0)
					continue;
				map[FIRE][ny][nx] = map[FIRE][y][x] + 1;
				fire.add(new int[] { ny, nx });
			}
		}
		while (!que.isEmpty()) {
			int[] top = que.poll();
			int y = top[0];
			int x = top[1];
			for (int i = 0; i < 4; i++) {
				int ny = y + dy[i];
				int nx = x + dx[i];
//				System.out.println("ny,nx -> " + ny + ", " + nx);
				if (ny < 0 || nx < 0 || ny >= r || nx >= c) {
					ans = map[USER][y][x] + 1;
					return;
				}
				if (arr[ny][nx] == '#' || map[USER][ny][nx] >= 0)
					continue;
				map[USER][ny][nx] = map[USER][y][x] + 1;
//				System.out.println((map[FIRE][ny][nx] != -1) + "    " + (map[FIRE][ny][nx] <= map[USER][ny][nx]));

				if (map[FIRE][ny][nx] != -1 && (map[FIRE][ny][nx] <= map[USER][ny][nx])) {
//					System.out.println("무야호");
					continue;
				}
				que.add(new int[] { ny, nx });
//				System.out.println("map[FIRE][ny][nx]: " + map[FIRE][ny][nx]);
//				System.out.println("map[USER][ny][nx]: " + map[USER][ny][nx]);
//				if (ny == 2 && nx == 1) {
//					System.out.println("-----------------------");
//					System.out.println(map[FIRE][ny][nx] != -1);
//					System.out.println(map[FIRE][ny][nx] <= map[USER][ny][nx]);
//				}
			}

		}
	}

	public static void init() throws Exception {
		que = new ArrayDeque<>();
		fire = new ArrayDeque<>();
		token = new StringTokenizer(input.readLine());
		r = Integer.parseInt(token.nextToken());
		c = Integer.parseInt(token.nextToken());
		arr = new char[r][c];
		map = new int[2][r][c];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < r; j++) {
				for (int k = 0; k < c; k++) {
					map[i][j][k] = -1;
				}
			}
		}
		for (int i = 0; i < r; i++) {
			String temp = input.readLine();
			for (int j = 0; j < c; j++) {
				arr[i][j] = temp.charAt(j);
				if (arr[i][j] == 'J') {
					que.add(new int[] { i, j });
					map[USER][i][j] = 0;
				} else if (arr[i][j] == 'F') {
					fire.add(new int[] { i, j });
					map[FIRE][i][j] = 0;
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
		output.append(ans == 0 ? "IMPOSSIBLE" : ans);
		System.out.println(output);
//		for (int i = 0; i < r; i++) {
//			for (int j = 0; j < c; j++) {
//				System.out.print(map[FIRE][i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println("===============================");
//		for (int i = 0; i < r; i++) {
//			for (int j = 0; j < c; j++) {
//				System.out.print(map[USER][i][j] + " ");
//			}
//			System.out.println();
//		}
	}
}