import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder ouput = new StringBuilder();
	static StringTokenizer token;

	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	static int n, m;
	static int[][] arr;
	static int y, x, dir;
	static int ans;

	public static void sol() {
		while (true) {
			if (arr[y][x] == 0) {
				ans++;
				arr[y][x] = -1;
			}
			boolean allClean = true;
			for (int i = 0; i < 4; i++) {
				int ny = y + dy[i];
				int nx = x + dx[i];
				if (arr[ny][nx] == 0 && allClean) {
					allClean = false;
				}
			}

			if (allClean) {
				int ny = y + dy[(dir+2)%4];
				int nx = x + dx[(dir+2)%4];
				if (arr[ny][nx] == 1) {
					System.out.println(ans);
					return;
				}
				y = ny;
				x = nx;
			} else {

				for (int i = 0; i < 4; i++) {
					dir += 3;
					dir %= 4;
					int ny = y + dy[dir];
					int nx = x + dx[dir];
					if (arr[ny][nx] == 0) {
						y = ny;
						x = nx;
						break;
					}

				}
			}

		}
	}

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		m = Integer.parseInt(token.nextToken());

		token = new StringTokenizer(input.readLine());
		y = Integer.parseInt(token.nextToken());
		x = Integer.parseInt(token.nextToken());
		dir = Integer.parseInt(token.nextToken());

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