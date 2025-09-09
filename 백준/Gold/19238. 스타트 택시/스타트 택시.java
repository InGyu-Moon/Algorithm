import java.io.*;
import java.util.*;

public class Main {
//	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
//	static StringBuilder output = new StringBuilder();
//	static StringTokenizer token;

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	static int FUEL = 2;

	static int n, m, fuel;
	static int[][] map;
	static People[] people;
	static int idx;

	static Taxi taxi;
	static boolean[] bool;

	static Queue<int[]> que = new ArrayDeque<>();
	static PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
		if (a[FUEL] == b[FUEL]) {
			if (a[0] == b[0]) {
				return Integer.compare(a[1], b[1]);
			}
			return Integer.compare(a[0], b[0]);
		}
		return -Integer.compare(a[FUEL], b[FUEL]);
	});

	public static void sol() throws Exception {
		while (find()) {
//			System.out.println(taxi.fuel);
			pq.clear();
			if (!move()) {
				return;
			}
			que.clear();
//			System.out.println(taxi.fuel);
//			System.out.println("===");
		}
		for (int i = 0; i < m; i++) {
			if (!bool[i]) {
				taxi.fuel = -1;
				break;
			}
		}
	}

	public static boolean find() throws Exception {
		boolean[][] visited = new boolean[n][n];
		pq.add(new int[] { taxi.y, taxi.x, taxi.fuel });
		if (map[taxi.y][taxi.x] >= 2) {
			idx = map[taxi.y][taxi.x] - 2;
			map[taxi.y][taxi.x] = 0;
			bool[idx] = true;
			return true;
		}
//		visited[taxi.y][taxi.x] = true;
		while (!pq.isEmpty()) {
			int[] top = pq.poll();
			if (visited[top[0]][top[1]])
				continue;
			visited[top[0]][top[1]] = true; 
			if (map[top[0]][top[1]] >= 2) {
				idx = map[top[0]][top[1]] - 2;
				map[top[0]][top[1]] = 0;
				taxi.y = top[0];
				taxi.x = top[1];
				taxi.fuel = top[FUEL];
				bool[idx] = true;
				return true;
			}
			if (top[FUEL] == 0)
				continue;
			for (int i = 0; i < 4; i++) {
				int ny = top[0] + dy[i];
				int nx = top[1] + dx[i];
				if (ny < 0 || nx < 0 || ny >= n || nx >= n || map[ny][nx] == 1 || visited[ny][nx])
					continue;
				pq.add(new int[] { ny, nx, top[FUEL] - 1 });
			}
		}
		return false;
	}

	public static boolean move() throws Exception {
		// 목적지
		int y = people[idx].ey;
		int x = people[idx].ex;
		boolean[][] visited = new boolean[n][n];
		que.add(new int[] { taxi.y, taxi.x, taxi.fuel });
		while (!que.isEmpty()) {
			int[] top = que.poll();
			if (top[FUEL] == 0)
				break;
			for (int i = 0; i < 4; i++) {
				int ny = top[0] + dy[i];
				int nx = top[1] + dx[i];
				if (ny < 0 || nx < 0 || ny >= n || nx >= n || map[ny][nx] == 1 || visited[ny][nx])
					continue;
				visited[ny][nx] = true;
				if (ny == y && nx == x) {
					int diff = taxi.fuel - (top[FUEL] - 1);
					taxi.fuel += diff;
					taxi.y = y;
					taxi.x = x;
					return true;
				}
				que.add(new int[] { ny, nx, top[FUEL] - 1 });
			}
		}
		taxi.fuel = -1;
		return false;
	}

	public static void init() throws Exception {
		String str = input.readLine();
		token = new StringTokenizer(str);
		n = Integer.parseInt(token.nextToken());
		m = Integer.parseInt(token.nextToken());
		fuel = Integer.parseInt(token.nextToken());
		map = new int[n + 1][n + 1];
		for (int i = 0; i < n; i++) {
			token = new StringTokenizer(input.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(token.nextToken());
			}
		}
		token = new StringTokenizer(input.readLine());
		taxi = new Taxi(Integer.parseInt(token.nextToken()) - 1, Integer.parseInt(token.nextToken()) - 1, fuel);
		people = new People[m];
		bool = new boolean[m];
		for (int i = 0; i < m; i++) {
			token = new StringTokenizer(input.readLine());
			int a = Integer.parseInt(token.nextToken()) - 1;
			int b = Integer.parseInt(token.nextToken()) - 1;
			int c = Integer.parseInt(token.nextToken()) - 1;
			int d = Integer.parseInt(token.nextToken()) - 1;
			people[i] = new People(a, b, c, d);
			map[a][b] = i + 2; // 승객은 2 이상
		}
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
		System.out.println(taxi.fuel);
	}

	public static class People {
		int sy;
		int sx;
		int ey;
		int ex;

		public People(int sy, int sx, int ey, int ex) {
			this.sy = sy;
			this.sx = sx;
			this.ey = ey;
			this.ex = ex;
		}
	}

	public static class Taxi {
		int y;
		int x;
		int fuel;

		public Taxi(int y, int x, int fuel) {
			this.y = y;
			this.x = x;
			this.fuel = fuel;
		}

		public boolean checkFuel() {
			if (fuel < 0)
				return false;
			return true;
		}
	}
}
