import java.io.*;
import java.util.*;

public class Solution {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static boolean flag;
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	static int x1, x2, y1, y2;

	static int[][] visited;

	static Deque<Pair> que;

	static int ans1, ans2;

	public static void sol1() {
		que = new ArrayDeque<>();
		visited = new int[201][201];
		que.add(new Pair(y1, x1, true));
		while (!que.isEmpty()) {
			Pair front = que.poll();
			int temp = 0;
			if (front.flag)
				temp++;

			for (int i = temp; i < 4; i += 2) {
				int ny = front.first + dy[i];
				int nx = front.second + dx[i];
				if (ny < 0 || nx < 0 || nx > 200 || ny > 200)
					continue;
				if (visited[ny][nx] != 0)
					continue;
				visited[ny][nx] = visited[front.first][front.second] + 1;
				if (ny == y2 && nx == x2) {
					ans1 = visited[ny][nx];
					return;
				}
				que.add(new Pair(ny, nx, !front.flag));
			}
		}
	}

	public static void sol2() {
		que = new ArrayDeque<>();
		visited = new int[201][201];
		que.add(new Pair(y1, x1, false));
		while (!que.isEmpty()) {
			Pair front = que.poll();
			int temp = 0;
			if (front.flag)
				temp++;

			for (int i = temp; i < 4; i += 2) {
				int ny = front.first + dy[i];
				int nx = front.second + dx[i];
				if (ny < 0 || nx < 0 || nx > 200 || ny > 200)
					continue;
				if (visited[ny][nx] != 0)
					continue;
				visited[ny][nx] = visited[front.first][front.second] + 1;
				if (ny == y2 && nx == x2) {
					ans2 = visited[ny][nx];
					return;
				}
				que.add(new Pair(ny, nx, !front.flag));
			}
		}
	}

	public static void init() throws IOException {
		token = new StringTokenizer(input.readLine());
		x1 = Integer.parseInt(token.nextToken()) + 100;
		y1 = Integer.parseInt(token.nextToken()) + 100;
		x2 = Integer.parseInt(token.nextToken()) + 100;
		y2 = Integer.parseInt(token.nextToken()) + 100;

		ans1 = Integer.MAX_VALUE;
		ans2 = Integer.MAX_VALUE;
	}

	public static void main(String[] args) throws IOException {

		int T = Integer.parseInt(input.readLine());
		for (int t = 1; t <= T; t++) {
			init();
			if (x1 == x2 && y1 == y2) {
				ans1 = ans2 = 0;
			} else {
				sol1();
				sol2();
			}
			output.append("#").append(t).append(" ").append(Math.min(ans1, ans2)).append("\n");
		}
		System.out.println(output);
	}

	static class Pair {
		int first;
		int second;
		boolean flag;

		public Pair(int first, int second, boolean flag) {
			this.first = first;
			this.second = second;
			this.flag = flag;
		}

	}

}