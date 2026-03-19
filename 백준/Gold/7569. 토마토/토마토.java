import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int[] dx = { 1, -1, 0, 0, 0, 0 };
	static int[] dy = { 0, 0, 1, -1, 0, 0 };
	static int[] dz = { 0, 0, 0, 0, 1, -1 };

	static int n, m, h;
	static int[][][] arr;
	static Queue<int[]> que = new ArrayDeque<>();

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		m = Integer.parseInt(token.nextToken());
		n = Integer.parseInt(token.nextToken());
		h = Integer.parseInt(token.nextToken());
		arr = new int[n][m][h];
		for (int k = 0; k < h; k++) {
			for (int i = 0; i < n; i++) {
				token = new StringTokenizer(input.readLine());
				for (int j = 0; j < m; j++) {
					int temp = Integer.parseInt(token.nextToken());
					arr[i][j][k] = temp;
					if (temp == 1) {
						que.add(new int[] { i, j, k });
					}
				}
			}
		}
	}

	public static void bfs() {
		while (!que.isEmpty()) {
			int[] top = que.poll();
			for (int i = 0; i < 6; i++) {
				int nx = top[0] + dx[i];
				int ny = top[1] + dy[i];
				int nz = top[2] + dz[i];
				if (nx < 0 || ny < 0 || nz < 0 || nx >= n || ny >= m || nz >= h)
					continue;
//				if (arr[nx][ny][nz] == -1 || arr[nx][ny][nz] <= 1)
				if (arr[nx][ny][nz] != 0)
					continue;
				arr[nx][ny][nz] = arr[top[0]][top[1]][top[2]] + 1;
				que.add(new int[] { nx, ny, nz });
			}
		}
	}

	public static void ans() {
		int max = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				for (int k = 0; k < h; k++) {
					int num = arr[i][j][k];
					if (num == 0) {
						output.append("-1");
						return;
					}
					if (num == -1)
						continue;
					if (max < num)
						max = num;
				}
			}
		}
		output.append(max-1);
	}

	public static void main(String[] args) throws Exception {
		init();
		bfs();
		ans();
		System.out.println(output);
	}
}
