import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int[] dy = { 0, 1, 0, -1 };
	static int[] dx = { 1, 0, -1, 0 };
	static int SNAKE = 3;
	static int APPLE = 7;
	static int NOTHING = 0;

	static int n, k, l;
	static int[][] map;
	static int dir;
	static int time = 0;

	static Deque<int[]> snake = new ArrayDeque<>();
	static char[] list = new char[10001];

	public static void init() throws Exception {
		n = Integer.parseInt(input.readLine());
		k = Integer.parseInt(input.readLine());
		map = new int[n + 2][n + 2];
		for (int i = 0; i < k; i++) {
			token = new StringTokenizer(input.readLine());
			int a = Integer.parseInt(token.nextToken());
			int b = Integer.parseInt(token.nextToken());
			map[a][b] = APPLE;
		}
		l = Integer.parseInt(input.readLine());
		for (int i = 0; i < l; i++) {
			token = new StringTokenizer(input.readLine());
			int x = Integer.parseInt(token.nextToken());
			char c = token.nextToken().charAt(0);
			list[x] = c;
		}
	}

	public static void ans() {
		snake.addFirst(new int[] { 1, 1 });
		map[1][1] = SNAKE;
		dir = 0;
		while (true) {

			time++;
//			System.out.println("time: " + time + ", dir: " + list[time]);

			// 1. 먼저 뱀은 몸길이를 늘려 머리를 다음칸에 위치시킨다.
			int[] top = snake.peekFirst();
			int ny = top[0] + dy[dir];
			int nx = top[1] + dx[dir];
			snake.addFirst(new int[] { ny, nx });
			// 2. 만약 벽이나 자기자신의 몸과 부딪히면 게임이 끝난다.
			if (ny == 0 || nx == 0 || ny == n + 1 || nx == n + 1 || map[ny][nx] == SNAKE)
				return;
			// 3. 만약 이동한 칸에 사과가 있다면, 그 칸에 있던 사과가 없어지고 꼬리는 움직이지 않는다.
			if (map[ny][nx] == APPLE) {
				map[ny][nx] = SNAKE;
			} else { // 4. 만약 이동한 칸에 사과가 없다면, 몸길이를 줄여서 꼬리가 위치한 칸을 비워준다. 즉, 몸길이는 변하지 않는다.
				int[] tail = snake.pollLast();
				map[ny][nx] = SNAKE;
				map[tail[0]][tail[1]] = NOTHING;
			}
			boolean flag = 10000 < time;
			if (!flag && list[time] == 'L') {
				dir--;
				dir += 4;
			}
			if (!flag && list[time] == 'D') {
				dir++;
			}
			dir %= 4;
//			print();
		}
	}

	public static void print() {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("-------------------------");
	}

	public static void main(String[] args) throws Exception {
		init();
		ans();
		System.out.println(time);
	}
}
