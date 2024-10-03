import java.io.*;
import java.util.*;

public class Solution {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int[][] arr;
	static int k;
	static Pair[] pair;
	static boolean[] check;
	static int ans;

	public static void move(int idx, int dir) {
		if (dir == 1) {
			for (int i = 7; i >= 0; i--) {
				arr[idx][i + 1] = arr[idx][i];
			}
			arr[idx][0] = arr[idx][8];
		} else {
			arr[idx][8] = arr[idx][0];
			for (int i = 0; i <= 7; i++) {
				arr[idx][i] = arr[idx][i + 1];
			}
		}
	}

	public static void sol() {
		for (Pair p : pair) {
			check = new boolean[5];
			check[p.num] = true;
			// 오른쪽 확인
			int idx = p.num;
			while (idx != 4 && arr[idx][2] != arr[idx + 1][6]) {
				check[idx + 1] = true;
				idx++;
			}
			// 왼쪽 확인	
			idx = p.num;
			while (idx != 1 && arr[idx][6] != arr[idx - 1][2]) {
				check[idx - 1] = true;
				idx--;
			}
			// 오른쪽 돌리기
			int flag = p.dir;
			for (int i = p.num; i <= 4; i++) {
				if (!check[i])
					break;
				move(i, flag);
				flag *= -1;
			}
			// 왼쪽 돌리기
			flag = p.dir;
			for (int i = p.num - 1; i >= 1; i--) {
				flag *= -1;
				if (!check[i])
					break;
				move(i, flag);
			}
		}
	}

	public static void init() throws Exception {
		arr = new int[5][9];
		k = Integer.parseInt(input.readLine());
		pair = new Pair[k];
		
		ans = 0;
		for (int i = 1; i <= 4; i++) {
			token = new StringTokenizer(input.readLine());
			for (int j = 0; j < 8; j++) {
				arr[i][j] = Integer.parseInt(token.nextToken());
			}
		}

		for (int i = 0; i < k; i++) {
			token = new StringTokenizer(input.readLine());
			pair[i] = new Pair(Integer.parseInt(token.nextToken()), Integer.parseInt(token.nextToken()));
		}
	}

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(input.readLine());
		for (int t = 1; t <= T; t++) {
			init();
			sol();
			for (int i = 1; i <= 4; i++) {
				if (arr[i][0] == 1) {
					ans += (int) Math.pow(2, i - 1);
				}
			}
			output.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.println(output);
	}

	static class Pair {
		int num;
		int dir;

		public Pair(int num, int dir) {
			super();
			this.num = num;
			this.dir = dir;
		}

	}
}