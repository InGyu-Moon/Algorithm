import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static char[][] arr;
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	static int r, c;
	static Queue<Pair> que;

	static Queue<Pair> sol;

	static int rS = -1, rE = -1, cS = -1, cE = -1;

	public static boolean check(Pair top) {
		int cnt = 0;
		for (int i = 0; i < 4; i++) {
			int ny = top.first + dy[i];
			int nx = top.second + dx[i];
			if (ny < 0 || nx < 0 || ny >= r || nx >= c)
				cnt++;
			else if (arr[ny][nx] == '.')
				cnt++;
		}
		if (cnt >= 3) {
			return true;
		} else {
			return false;
		}
	}

	public static void sol() {
		while (!que.isEmpty()) {
			Pair top = que.poll();
			if (check(top)) {
				sol.add(top);
			}
		}
		while (!sol.isEmpty()) {
			Pair top = sol.poll();
			arr[top.first][top.second] = '.';
		}
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (arr[i][j] == 'X') {
					rS = i;
					break;
				}
			}
			if (rS == i)
				break;
		}
		for (int i = r - 1; i >= 0; i--) {
			for (int j = 0; j < c; j++) {
				if (arr[i][j] == 'X') {
					rE = i;
					break;
				}
			}
			if (rE == i)
				break;
		}

		for (int j = 0; j < c; j++) {
			for (int i = 0; i < r; i++) {
				if (arr[i][j] == 'X') {
					cS = j;
					break;
				}
			}
			if (cS == j)
				break;
		}

		for (int j = c - 1; j >= 0; j--) {
			for (int i = 0; i < r; i++) {
				if (arr[i][j] == 'X') {
					cE = j;
					break;
				}
			}
			if (cE == j)
				break;
		}

		// 정답 출력
		for (int i = rS; i <= rE; i++) {
			for (int j = cS; j <= cE; j++) {
				output.append(arr[i][j]);
			}
			output.append("\n");
		}

//		for (int i = 0; i < r; i++) {
//			for (int j = 0; j < c; j++) {
//				output.append(arr[i][j]);
//			}
//			output.append("\n");
//		}
	}

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		r = Integer.parseInt(token.nextToken());
		c = Integer.parseInt(token.nextToken());
		arr = new char[r][c];
		que = new LinkedList<>();
		sol = new LinkedList<>();
		for (int i = 0; i < r; i++) {
			String temp = input.readLine();
			for (int j = 0; j < c; j++) {
				arr[i][j] = temp.charAt(j);
				if (arr[i][j] == 'X') {
					que.add(new Pair(i, j));
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
		System.out.println(output);
	}

	static class Pair {
		int first;
		int second;

		public Pair(int first, int second) {
			super();
			this.first = first;
			this.second = second;
		}

	}

}