import java.io.*;
import java.util.*;

public class Solution {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int UP = 0;
	static int RIGHT = 1;
	static int DOWN = 2;
	static int LEFT = 3;
	static int ALL = 4;

	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	static int dir;

	static int n, m;

	static char[][] arr;
	static boolean[][][][] visited;

	static int num;
	static int y, x;
	static boolean flag;

	static Deque<Pair> que;

	public static boolean move(Pair top) {
		y = top.y;
		x = top.x;
		dir = top.dir;
		num = top.num;
		visited = top.visited;
		char ch = arr[y][x];
		// 종료 조건
		if (ch == '@') {
			return true;
		}
		// 메모리 처리
		if ('0' <= ch && ch <= '9') {
			num = ch - '0';
		}
		// 이동 방향 처리
		else if (ch == '<' || ch == '>' || ch == '^' || ch == 'v') {
			if (ch == '<')
				dir = LEFT;
			else if (ch == '>')
				dir = RIGHT;
			else if (ch == '^')
				dir = UP;
			else if (ch == 'v')
				dir = DOWN;
		}
		// 메모리 기반 방향 처리 ( _ , | )
		else if (ch == '_' || ch == '|') {
			if (ch == '_' && num == 0) {
				dir = RIGHT;
			} else if (ch == '_' && num != 0) {
				dir = LEFT;
			} else if (ch == '|' && num == 0) {
				dir = DOWN;
			} else if (ch == '|' && num != 0) {
				dir = UP;
			}
		}
		// 랜덤 방향 처리 ( ? )
		else if (ch == '?') {
			dir = ALL;
		}
		// 메모리 값 변경 ( + , - )
		else if (ch == '+' || ch == '-') {
			if (ch == '+')
				num++;
			else if (ch == '-')
				num--;
		}
		// 이동
//		y += dy[dir];
//		x += dx[dir];
//		y %= n;
//		x %= m;

		// 메모리
		num = (num + 16) % 16;

		if (dir == ALL) {
			for (int i = 0; i < 4; i++) {
				int tempY = y + dy[i];
				int tempX = x + dx[i];
				tempY = (tempY + n) % n;
				tempX = (tempX + m) % m;
				if (!visited[tempY][tempX][i][num]) {
					visited[tempY][tempX][i][num] = true;
					que.add(new Pair(tempY, tempX, i, num, visited));
				}
			}
		} else {
			int tempY = y + dy[dir];
			int tempX = x + dx[dir];
			tempY = (tempY + n) % n;
			tempX = (tempX + m) % m;
			if (!visited[tempY][tempX][dir][num]) {
				visited[tempY][tempX][dir][num] = true;
				que.add(new Pair(tempY, tempX, dir, num, visited));
			}
		}
		return false;
	}

	public static void sol() {
		visited[0][0][RIGHT][0] = true;
		que.add(new Pair(0, 0, RIGHT, 0, visited));
		while (!que.isEmpty()) {
			Pair top = que.poll();
			// 이동 후 종료 조건 만족시 종료
			if (move(top)) {
				output.append("YES\n");
				return;
			}
		}
		output.append("NO\n");

	}

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		m = Integer.parseInt(token.nextToken());

		arr = new char[n][m];
		visited = new boolean[n][m][4][16];
		que = new ArrayDeque<Pair>();

		for (int i = 0; i < n; i++) {
			String str = input.readLine();
			for (int j = 0; j < m; j++) {
				arr[i][j] = str.charAt(j);
			}
		}
		// 기타 변수 초기화
		num = 0;
		dir = RIGHT;
		y = x = 0;
		flag = false;
	}

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(input.readLine());
		for (int t = 1; t <= T; t++) {
			output.append("#").append(t).append(" ");
			init();
			sol();
		}
		System.out.println(output);
	}

	static class Pair {
		int y;
		int x;
		int dir;
		int num;
		boolean[][][][] visited;

		public Pair(int y, int x, int dir, int num, boolean[][][][] visited) {
			this.y = y;
			this.x = x;
			this.dir = dir;
			this.num = num;
			this.visited = visited;
		}

	}
}