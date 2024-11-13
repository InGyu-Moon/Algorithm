import java.io.*;
import java.util.*;

public class Solution {

	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	static int UP = 0;
	static int RIGHT = 1;
	static int DOWN = 2;
	static int LEFT = 3;

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static String str;
	static int dir;
	static char[] arr;

	static int x, y, ans;

	public static void sol() {
		for (int k = 0; k < 4; k++) {
			for (int i = 0; i < arr.length; i++) {
				char ch = arr[i];
				if (ch == 'S') {
					x = x + dx[dir];
					y = y + dy[dir];
				} else if (ch == 'L') {
					dir--;
					dir = (dir + 4) % 4;
				} else if (ch == 'R') {
					dir++;
					dir = (dir + 4) % 4;
				}
				// ans 최신화
				int temp = x * x + y * y;
				ans = Math.max(ans, temp);
			}
		}
	}

	public static void init() throws Exception {
		str = input.readLine();
		arr = new char[str.length()];
		for (int i = 0; i < str.length(); i++) {
			arr[i] = str.charAt(i);
		}
		// 초기화
		ans = 0;
		x = y = 0;
		dir = 1;
	}

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(input.readLine());
		for (int t = 1; t <= T; t++) {
			init();
			sol();
			output.append("#").append(t).append(" ").append(x == 0 && y == 0 ? ans : "oo").append("\n");
		}
		System.out.println(output);
	}

}