import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int[][] arr;
	static List<Pair> list;
	static boolean solved;

	public static boolean checkRow(int row, int val) {
		for (int i = 0; i < 9; i++) {
			if (arr[row][i] == val)
				return false;
		}
		return true;
	}

	public static boolean checkCol(int col, int val) {
		for (int i = 0; i < 9; i++) {
			if (arr[i][col] == val)
				return false;
		}
		return true;
	}

	public static boolean checkSquare(int y, int x, int val) {
		int row = y / 3 * 3;
		int col = x / 3 * 3;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (arr[row + i][col + j] == val)
					return false;
			}
		}
		return true;
	}

	public static void dfs(int depth) {
		// 종료 조건
		if (solved) {
			return;
		}
		if (list.size() == depth) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					output.append(arr[i][j]);
				}
				output.append("\n");
			}
			solved = true;
			return;
		}

		// 반복 조건
		int y = list.get(depth).y;
		int x = list.get(depth).x;
		for (int val = 1; val <= 9; val++) {
			if (arr[y][x] != 0)
				continue;
			// 스도쿠 확인 로직
			if (!checkRow(y, val) || !checkCol(x, val) || !checkSquare(y, x, val))
				continue;

			arr[y][x] = val;
			dfs(depth + 1);
			arr[y][x] = 0;
		}
	}

	public static void init() throws Exception {
		list = new ArrayList<>();
		arr = new int[9][9];
		solved = false;
		for (int i = 0; i < 9; i++) {
			String temp = input.readLine();
			for (int j = 0; j < 9; j++) {
				arr[i][j] = temp.charAt(j) - '0';
				if (arr[i][j] == 0)
					list.add(new Pair(i, j));
			}
		}
	}

	public static void main(String[] args) throws Exception {
		init();
		dfs(0);
		System.out.println(output);
	}

	static class Pair {
		int y;
		int x;

		public Pair(int y, int x) {
			this.y = y;
			this.x = x;
		}

	}
}