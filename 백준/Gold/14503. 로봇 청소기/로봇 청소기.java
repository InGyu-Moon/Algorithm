import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder ouput = new StringBuilder();
	static StringTokenizer token;

	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	static int n, m;
	static int[][] arr; // 0 -> 청소 x, 1 -> 벽
	static int y, x, dir;
	static int ans;

	public static void sol() {
		while (true) {
			// 현재 칸 청소
			if (arr[y][x] == 0) {
				ans++;
				arr[y][x] = -1;
			}
			// 주변 4칸 확인 (청소 안된칸 있나)
			boolean allClean = true;
			for (int i = 0; i < 4; i++) {
				int ny = y + dy[i];
				int nx = x + dx[i];
				if (arr[ny][nx] == 0 && allClean) { // 청소 안된칸
					allClean = false;
				}
			}

			if (allClean) { // 전부 청소가 되어있음
				// 바라보는 방향 뒤로 후진
				dir += 2;
				dir %= 4;
				int ny = y + dy[dir];
				int nx = x + dx[dir];
				if (arr[ny][nx] == 1) { // 벽이면 정지
					System.out.println(ans);
					return;
				}
				y = ny;
				x = nx;
				dir += 2;
				dir %= 4;
			} else { // 청소 안된 부분이 있음
				// 반시계로 돌면서 청소되지 않은 칸이 있으면 1칸 전진
				for (int i = 0; i < 4; i++) {
					dir += 3;
					dir %= 4;
					int ny = y + dy[dir];
					int nx = x + dx[dir];
					if (arr[ny][nx] == 0) { // 청소 안되어 있는 부분 찾음
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