import java.io.*;
import java.util.*;

public class Main {

	// 1 ~ 8 이므로 0은 사용 X
	static int[] dy = { -100, 0, -1, -1, -1, 0, 1, 1, 1, };
	static int[] dx = { -100, -1, -1, 0, 1, 1, 1, 0, -1 };

	// 대각선 확인을 위한 dydx
	static int[] dyNear = { -1, -1, 1, 1 };
	static int[] dxNear = { -1, 1, 1, -1 };

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n, m;
	static Basket[][] map;
	static Query[] querys;
	static Deque<Cloud> clouds;
	static Deque<Cloud> newClouds;
	static Deque<Cloud> hasRainedList;

	static long ans;

	public static void sol() {
		makeCloud();
		for (Query query : querys) {
			moveCloud(query);
			makeRain();
			copyWater();
			makeNewCloud();
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				ans += map[i][j].water;
			}
		}
		System.out.println(ans);
	}

	public static void makeNewCloud() {
		clouds.clear(); // 혹시 몰라서 clear 함
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				Basket basket = map[i][j];

				if (basket.water >= 2) { // 바구니의 물이 2 이상이면
					if (basket.hasRained == true) { // 그런데 이미 비가 왔으면 (전턴에 구름이 있었음)
						basket.hasRained = false;
					} else { // 비가 안왔었으면 구름 생성
						basket.water -= 2; // 물감소
						clouds.add(new Cloud(i, j)); // 구름 생성
					}
				}
			}
		}
//		System.out.println();
//		for (Cloud cloud : clouds) {
//			System.out.println("cloud.r , cloud.c : " + cloud.r + ", " + cloud.c);
//		}
	}

	public static void copyWater() {

//		System.out.println("========================");
//		System.out.println(hasRainedList.size());

		while (!hasRainedList.isEmpty()) {
			Cloud cloud = hasRainedList.poll();
			int y = cloud.r;
			int x = cloud.c;
			int cnt = 0;
//			System.out.println("cloud: (" + cloud.r + ", " + cloud.c + ")");
			// 대각선 확인
			for (int i = 0; i < 4; i++) {
				int ny = y + dyNear[i];
				int nx = x + dxNear[i];
				if (ny < 0 || nx < 0 || ny >= n || nx >= n) {
					continue;
				}
				if (map[ny][nx].water == 0) {
					continue;
				}
				cnt++;
			}

//			System.out.println("cnt: " + cnt);
			map[y][x].water += cnt;
		}
	}

	public static void makeRain() {
		hasRainedList = new ArrayDeque<>();
		while (!clouds.isEmpty()) {
			Cloud cloud = clouds.poll();
//			System.out.println("cloud.r: " + cloud.r + ", cloud.c: " + cloud.c);
			map[cloud.r][cloud.c].water++;
			map[cloud.r][cloud.c].hasRained = true;
			hasRainedList.add(new Cloud(cloud.r, cloud.c));
		}
	}

	public static void moveCloud(Query query) {
		int d = query.d; // 방향
		int s = query.s; // 칸
		newClouds = new ArrayDeque<>();
		while (!clouds.isEmpty()) {
			Cloud cloud = clouds.poll();

//			int nr = (cloud.r - 1 + s * dy[d] + n + n) % n + 1;
//			int nc = (cloud.c - 1 + s * dx[d] + n + n) % n + 1;

			int nr = (cloud.r + s * dy[d]) % n;
			int nc = (cloud.c + s * dx[d]) % n;

			if (nr < 0)
				nr += n;
			if (nc < 0)
				nc += n;

			newClouds.add(new Cloud(nr, nc));
//			System.out.println("newCloud 생성");
//			System.out.println("cloud.r , cloud.c : " + cloud.r + ", " + cloud.c);
//			System.out.println("s, n : " + s + ", " + n);
//			System.out.println("dy[d], dx[d] : " + dy[d] + ", " + dx[d]);
//			System.out.println("nr, nc : " + nr + ", " + nc);
		}
		while (!newClouds.isEmpty()) {
			Cloud cloud = newClouds.poll();
			clouds.add(new Cloud(cloud.r, cloud.c));
		}
	}

	public static void makeCloud() {
		clouds.add(new Cloud(n - 1, 0));
		clouds.add(new Cloud(n - 1, 1));
		clouds.add(new Cloud(n - 2, 0));
		clouds.add(new Cloud(n - 2, 1));
	}

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		m = Integer.parseInt(token.nextToken());
		map = new Basket[n][n];
		querys = new Query[m];
		for (int i = 0; i < n; i++) {
			token = new StringTokenizer(input.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = new Basket(Integer.parseInt(token.nextToken()));
//				map[i][j].water = Integer.parseInt(token.nextToken());
			}
		}
		for (int i = 0; i < m; i++) {
			token = new StringTokenizer(input.readLine());
			querys[i] = new Query(Integer.parseInt(token.nextToken()), Integer.parseInt(token.nextToken()));
//			querys[i].d = Integer.parseInt(token.nextToken());
//			querys[i].s = Integer.parseInt(token.nextToken());
		}
		clouds = new ArrayDeque<>();
		ans = 0;

//		System.out.println("=====================================");
//		for (int i = 1; i <= n; i++) {
//			for (int j = 1; j <= n; j++) {
//				System.out.print(map[i][j].water + " ");
//			}
//			System.out.println();
//		}
//		System.out.println("=====================================");

	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
	}

	static public class Basket {
		int water;
		boolean hasRained;

		public Basket(int water) {
			this.water = water;
			hasRained = false;
		}
	}

	static public class Cloud {
		int r;
		int c;

		public Cloud(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static public class Query {
		int d;
		int s;

		public Query(int d, int s) {
			this.d = d;
			this.s = s;
		}
	}
}