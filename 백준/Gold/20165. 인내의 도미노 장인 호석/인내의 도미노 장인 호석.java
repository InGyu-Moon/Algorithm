import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	static int[][] dirArr;

	static int n, m, r;
	static Pair[][] arr;
	static int y, x;
	static char dir;

	static int score;

	public static void print() {
		output.append(score).append("\n");
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (arr[i][j].isFalled)
					output.append("F ");
				else {
					output.append("S ");
				}
			}
			output.append("\n");
		}
		System.out.println(output);
	}

	public static void attack() {
		arr[y][x].isFalled = true;
		score++;
		int h = arr[y][x].height - 1;
		while (h > 0) {
			int ny = y + dy[dirArr[dir - 'A'][0]];
			int nx = x + dx[dirArr[dir - 'A'][0]];
			if (ny < 1 || nx < 1 || ny > n || nx > m)
				break;
			if (arr[ny][nx].isFalled) { // 이미 넘어진 구역
				h--;
			} else { // 안넘어진 구역
				arr[ny][nx].isFalled = true;
				score++;
				h = Math.max(h - 1, arr[ny][nx].height - 1);
			}
			y = ny;
			x = nx;
		}
	}

	public static void defense() {
		arr[y][x].isFalled = false;
	}

	public static void sol() throws Exception {
		for (int i = 0; i < r; i++) {
			// 공격
			token = new StringTokenizer(input.readLine());
			y = Integer.parseInt(token.nextToken());
			x = Integer.parseInt(token.nextToken());
			dir = token.nextToken().charAt(0);
			attack();

			// 수비
			token = new StringTokenizer(input.readLine());
			y = Integer.parseInt(token.nextToken());
			x = Integer.parseInt(token.nextToken());
			defense();
		}
	}

	public static void init() throws Exception {
		score = 0;
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		m = Integer.parseInt(token.nextToken());
		r = Integer.parseInt(token.nextToken());
		arr = new Pair[n + 1][m + 1];
		for (int i = 1; i <= n; i++) {
			token = new StringTokenizer(input.readLine());
			for (int j = 1; j <= m; j++) {
				arr[i][j] = new Pair(Integer.parseInt(token.nextToken()));
			}
		}

		dirArr = new int[26][2];
		dirArr['N' - 'A'][0] = 0;
		dirArr['E' - 'A'][0] = 1;
		dirArr['S' - 'A'][0] = 2;
		dirArr['W' - 'A'][0] = 3;
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
		print();
	}

	static class Pair {
		int height;
		boolean isFalled;

		public Pair(int height) {
			this.height = height;
			this.isFalled = false;
		}

	}
}