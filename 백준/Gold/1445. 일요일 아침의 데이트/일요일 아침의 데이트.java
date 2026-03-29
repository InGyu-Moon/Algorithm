import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int INF = 987654321;

	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	static int n, m;
	static char[][] map;

	static int[][] val, trash;

	static PriorityQueue<Node> pq = new PriorityQueue<>((Node a, Node b) -> {
		if (a.val == b.val)
			return Integer.compare(a.trash, b.trash);
		return Integer.compare(a.val, b.val);
	});

	public static void sol() {
		while (!pq.isEmpty()) {
			Node curr = pq.poll();
			// 다익스트라 최적화
			if (val[curr.y][curr.x] < curr.val)
				continue;
			if (val[curr.y][curr.x] == curr.val && trash[curr.y][curr.x] < curr.trash)
				continue;

			if (map[curr.y][curr.x] == 'F') {
				output.append(curr.val).append(" ").append(curr.trash);
				return;
			}

			for (int i = 0; i < 4; i++) {
				int ny = curr.y + dy[i];
				int nx = curr.x + dx[i];
				int nextVal = curr.val;
				int nextTrash = curr.trash;
				if (ny < 0 || nx < 0 || ny == n || nx == m)
					continue;
				if (map[ny][nx] == 'S')
					continue;
				if (map[ny][nx] == 'F') {
					nextVal = curr.val;
					nextTrash = curr.trash;
				}
				if (map[ny][nx] == 'g') {
					nextVal = curr.val + 1;
					nextTrash = curr.trash;
				}
				if (map[ny][nx] == '.') {
					int cnt = curr.trash;
					if (check(ny, nx))
						cnt++;
					nextVal = curr.val;
					nextTrash = cnt;
				}

				if (nextVal < val[ny][nx] || (nextVal == val[ny][nx] && nextTrash < trash[ny][nx] )) {
					val[ny][nx] = nextVal;
					trash[ny][nx] = nextTrash;
					pq.add(new Node(ny, nx, nextVal, nextTrash));
				}

			}
		}
	}

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		m = Integer.parseInt(token.nextToken());
		map = new char[n + 1][m + 1];
		val = new int[n][m];
		trash = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				val[i][j] = trash[i][j] = INF;
			}
		}
		for (int i = 0; i < n; i++) {
			String temp = input.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = temp.charAt(j);
				if (map[i][j] == 'S') {
					pq.add(new Node(i, j, 0, 0));
					val[i][j] = trash[i][j] = 0;
				}
			}
		}

	}

	public static boolean check(int y, int x) {
		int cnt = 0;
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if (ny < 0 || nx < 0 || ny == n || nx == m)
				continue;
			if (map[ny][nx] == 'g')
				return true;
		}
		return false;
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
		System.out.println(output);
	}

	public static class Node {
		int y;
		int x;
		int val;
		int trash;

		public Node(int y, int x, int val, int trash) {
			this.y = y;
			this.x = x;
			this.val = val;
			this.trash = trash;
		}

	}
}
