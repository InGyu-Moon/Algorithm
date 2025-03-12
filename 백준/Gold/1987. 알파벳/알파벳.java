import java.io.*;
import java.util.*;

public class Main {

	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n, m;
	static char[][] arr;
	static int ans, curr;
	static boolean[] alpha;

	public static void dfs(int y, int x) {
		ans = Math.max(ans, curr);
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if (ny < 0 || nx < 0 || ny >= n || nx >= m)
				continue;
			if (alpha[arr[ny][nx] - 'A'])
				continue;
			alpha[arr[ny][nx] - 'A'] = true;
			curr++;
//			System.out.println("y,x: " + ny + ", " + nx + " ==> " + ans);
			dfs(ny, nx);
			curr--;
			alpha[arr[ny][nx] - 'A'] = false;
		}
	}

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		m = Integer.parseInt(token.nextToken());
		arr = new char[n][m];
		for (int i = 0; i < n; i++) {
			String temp = input.readLine();
			for (int j = 0; j < m; j++) {
				arr[i][j] = temp.charAt(j);
			}
		}
		alpha = new boolean['Z' - 'A' + 1];
		curr = 1;
		alpha[arr[0][0] - 'A'] = true;
	}

	public static void main(String[] args) throws Exception {
		init();
		dfs(0, 0);
		System.out.println(ans);
	}

}
