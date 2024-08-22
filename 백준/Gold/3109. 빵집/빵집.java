import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;
	static int r, c;
	static char[][] arr;
	static boolean[][] visited;
	static int[] next = { -1, 0, 1 };
	static boolean flag; // 정답을 찾으면 true로
	static int ans;

	private static void sol(int nth, int a, int b) {
		// 기저
		if (nth == c - 1) {
			flag = true;
			ans++;
			return;
		}
		if (flag)
			return;
		// 재귀
		for (int i = 0; i < 3; i++) {
			if (flag) {
				return;
			}
			int na = a + next[i];
			int nb = b + 1;
			if (na < 0 || na == r || visited[na][nb]) // visited(방문,X) 또는 범위 밖이면 continue
				continue;
			visited[na][nb] = true;
			sol(nth + 1, na, nb);
			if (flag) {
				return;
			}
//			visited[na][nb] = false;
		}
	}

	public static void main(String[] args) throws Exception {
		token = new StringTokenizer(input.readLine());
		r = Integer.parseInt(token.nextToken());
		c = Integer.parseInt(token.nextToken());

		arr = new char[r][c];
		visited = new boolean[r][c];

		for (int i = 0; i < r; i++) {
			String temp = input.readLine();
			for (int j = 0; j < c; j++) {
				arr[i][j] = temp.charAt(j);
				if (temp.charAt(j) == 'x')
					visited[i][j] = true;
			}
		}
		for (int i = 0; i < r; i++) {
			flag = false;
			sol(0, i, 0);
		}
		System.out.println(ans);

	}
}