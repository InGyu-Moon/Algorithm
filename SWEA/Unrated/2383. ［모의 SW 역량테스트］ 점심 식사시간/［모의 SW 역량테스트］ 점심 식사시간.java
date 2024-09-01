
import java.io.*;
import java.util.*;

public class Solution {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int N, idx, ans;
	static Stair[] stair;
	static int[][] map;

	static boolean[] selected;
	static LinkedList<Point> ppl;

	static void sol(int cnt) {
		if (cnt == idx) {
			ppl = new LinkedList<>();
			int a = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == 1) {
						if (selected[a++]) {
							int dir = Math.abs(stair[0].r - i) + Math.abs(stair[0].c - j);
							ppl.add(new Point((dir + 1), 0, stair[0].len));
						} else {
							int dir = Math.abs(stair[1].r - i) + Math.abs(stair[1].c - j);
							ppl.add(new Point((dir + 1), 1, stair[1].len));
						}
					}
				}
			}

			calculate(idx);
			return;
		}

		selected[cnt] = true;
		sol(cnt + 1);
		selected[cnt] = false;
		sol(cnt + 1);
	}

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(input.readLine());
		for (int tc = 1; tc <= T; tc++) {

			N = Integer.parseInt(input.readLine());
			map = new int[N][N];
			stair = new Stair[2];

			idx = 0;
			ans = 100000;
			int s = 0;
			for (int i = 0; i < N; i++) {
				token = new StringTokenizer(input.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(token.nextToken());
					if (map[i][j] > 1) {
						stair[s++] = new Stair(i, j, map[i][j], 0);
					} else if (map[i][j] == 1) {
						idx++;
					}
				}
			}

			selected = new boolean[idx];
			sol(0);
			output.append("#").append(tc).append(" ").append(ans).append("\n");
		}
        System.out.println(output);
	}

	static void calculate(int idx) {
		int cnt = 0;
		while (!ppl.isEmpty()) {
			cnt++;
			int a = 0;
			int b = 0;
			for (int i = 0; i < idx; i++) {
				Point p = ppl.get(i);
				if (p.dir == 0) {
					if (p.cnt == stair[p.n].len)
						continue;
					if ((p.cnt > 0) && (p.cnt < stair[p.n].len)) {
						p.cnt -= 1;
					}
					if (p.cnt == 0) {
						if (p.n == 0)
							a++;
						else
							b++;
						ppl.remove(p);
						i--;
						idx--;
					}
				}
			}

			for (int i = 0; i < idx; i++) {
				Point p = ppl.get(i);
				if (p.dir > 0) {
					p.dir -= 1;
				} else if ((p.dir == 0) && (p.cnt == stair[p.n].len) && (stair[p.n].cnt < 3)) {
					stair[p.n].cnt += 1;
					p.cnt -= 1;
				}
			}
			stair[0].cnt -= a;
			stair[1].cnt -= b;
		}

		if (cnt < ans)
			ans = cnt;
	}

	static class Stair {
		int r, c, len, cnt;

		Stair(int r, int c, int len, int cnt) {
			this.r = r;
			this.c = c;
			this.len = len;
			this.cnt = cnt;
		}
	}

	static class Point {
		int dir, n, cnt;

		Point(int dir, int n, int cnt) {
			this.dir = dir;
			this.n = n;
			this.cnt = cnt;
		}
	}
}
