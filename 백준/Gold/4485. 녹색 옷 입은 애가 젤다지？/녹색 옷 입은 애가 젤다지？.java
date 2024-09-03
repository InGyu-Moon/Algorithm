import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int[][] arr;
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };
	static int n;
	static int d[][];
	static boolean visited[][];

	private static void sol() {
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
		pq.add(new int[] { 0, 0, arr[0][0] });
		while (!pq.isEmpty()) {
			int[] front = pq.poll();
			int x = front[0], y = front[1], cost = front[2];
			if (x == n - 1 && y == n - 1) {
				output.append(d[x][y]).append("\n");
				break;
			}
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx < 0 || ny < 0 || nx >= n || ny >= n)
					continue;
				if (d[nx][ny] > cost + arr[nx][ny]) {
					d[nx][ny] = cost + arr[nx][ny];
					pq.add(new int[] { nx, ny, d[nx][ny] });
				}
			}
		}
	}

	private static void init() throws Exception {
		arr = new int[n][n];
		d = new int[n][n];
		visited = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			token = new StringTokenizer(input.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(token.nextToken());
				d[i][j] = 987654321;
			}
		}
	}

	public static void main(String[] args) throws Exception {

		int cnt = 1;
		while (true) {
			n = Integer.parseInt(input.readLine());
			if (n == 0)
				break;
			output.append("Problem ").append(cnt).append(": ");
			init();
			sol();
			cnt++;
		}
		System.out.println(output);

	}
}