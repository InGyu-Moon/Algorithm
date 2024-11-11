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
	static int MEM = 16;

	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	static int n, m, dir;
	static char[][] arr;
	static boolean[][][][] visited;
	static Deque<Pair> que;
	static int mem;

	public static boolean move(Pair top) {
		char ch = arr[top.y][top.x];
		int num = top.mem;
		dir = top.dir;
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

		// 메모리
		num = (num + 16) % 16;

		if (dir == ALL) {
			for (int i = 0; i < 4; i++) {
				int y = top.y + dy[i];
				int x = top.x + dx[i];
				y = (y + n) % n;
				x = (x + m) % m;
				if (visited[y][x][num][i])
					continue;
				visited[y][x][num][i] = true;
				que.add(new Pair(y, x, num, i));
			}
		} else {
			int y = top.y + dy[dir];
			int x = top.x + dx[dir];
			y = (y + n) % n;
			x = (x + m) % m;
			if (!visited[y][x][num][dir]) {
				visited[y][x][num][dir] = true;
				que.add(new Pair(y, x, num, dir));
			}
		}
		return false;
	}

	public static void sol() {
		que.add(new Pair(0, 0, 0, RIGHT));
		visited[0][0][0][RIGHT] = true;
		while (!que.isEmpty()) {
			Pair top = que.poll();
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
		visited = new boolean[n][m][16][4];
		que = new ArrayDeque<Pair>();

		for (int i = 0; i < n; i++) {
			String str = input.readLine();
			for (int j = 0; j < m; j++) {
				arr[i][j] = str.charAt(j);
			}
		}

		// 기타 변수 초기화
		dir = RIGHT;
		mem = 0;

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
		int mem;
		int dir;

		public Pair(int y, int x, int mem, int dir) {
			this.y = y;
			this.x = x;
			this.mem = mem;
			this.dir = dir;
		}

	}
}