import java.io.*;
import java.util.*;

public class Main {

	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int r, c, t;
	static int[][] arr, temp;
	static int top, down;

	static List<int[]> list;

	public static void sol() {
		for (int i = 0; i < t; i++) {
			move();
			clean();
		}
		int ans = 0;
		for (int i = 1; i <= r; i++) {
			for (int j = 1; j <= c; j++) {
				ans += arr[i][j];
			}
		}
		System.out.println(ans + 2);
	}

	public static void clean() {
		for (int i = top - 1; i > 1; i--) {
			arr[i][1] = arr[i - 1][1];
		}
		for (int i = 1; i < c; i++) {
			arr[1][i] = arr[1][i + 1];
		}
		for (int i = 1; i < top; i++) {
			arr[i][c] = arr[i + 1][c];
		}
		for (int i = c; i > 2; i--) {
			arr[top][i] = arr[top][i - 1];
		}
		arr[top][2] = 0;

		for (int i = down + 1; i < r; i++) {
			arr[i][1] = arr[i + 1][1];
		}
		for (int i = 1; i < c; i++) {
			arr[r][i] = arr[r][i + 1];
		}
		for (int i = r; i > down; i--) {
			arr[i][c] = arr[i - 1][c];
		}
		for (int i = c; i > 2; i--) {
			arr[down][i] = arr[down][i - 1];
		}
		arr[down][2] = 0;
	}

	public static void move() {
		temp = new int[r + 1][c + 1];
		list = new ArrayList<>();
		for (int i = 1; i <= r; i++) {
			for (int j = 1; j <= c; j++) {
				if (arr[i][j] > 0)
					list.add(new int[] { i, j });
			}
		}
		for (int[] pos : list) {
			int add = arr[pos[0]][pos[1]] / 5;
			int idx = 0;
			for (int i = 0; i < 4; i++) {
				int ny = pos[0] + dy[i];
				int nx = pos[1] + dx[i];
				if (ny <= 0 || nx <= 0 || ny >= r + 1 || nx >= c + 1)
					continue;
				if (arr[ny][nx] == -1)
					continue;
				temp[ny][nx] += add;
				idx++;
			}
			arr[pos[0]][pos[1]] -= add * idx;
		}
		// 합산
		for (int i = 1; i <= r; i++) {
			for (int j = 1; j <= c; j++) {
				arr[i][j] += temp[i][j];
			}
		}

		// 출력
//		for (int i = 1; i <= r; i++) {
//			for (int j = 1; j <= c; j++) {
//				System.out.print(arr[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println("========================");
	}

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		r = Integer.parseInt(token.nextToken());
		c = Integer.parseInt(token.nextToken());
		t = Integer.parseInt(token.nextToken());
		arr = new int[r + 1][c + 1];
		for (int i = 1; i <= r; i++) {
			token = new StringTokenizer(input.readLine());
			for (int j = 1; j <= c; j++) {
				arr[i][j] = Integer.parseInt(token.nextToken());
			}
		}
		for (int i = 1; i <= r; i++) {
			if (arr[i][1] == -1) {
				top = i;
				down = i + 1;
				break;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
	}
}