import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int T;
	static int n;
	static int x;
	static int[][] map;
	static int count;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			n = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());

			map = new int[n][n];
			count = 0;

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (i == 0) { // 행이 0일때 탐색
						rowsearch(i, j);
					}
					if (j == 0) { // 열이 0일때 탐색
						colSearch(i, j);
					}
				}
			}
			System.out.println("#" + t + " " + count);
		}
	}

	public static void rowsearch(int r, int c) {
		int length = 1;
		int height = map[r][c];

		if (r == 0) {
			for (int i = 1; i < n; i++) {
				if (height - map[i][c] == 0) {
					length++;
				}
				else if (height - map[i][c] == 1) {
					if (n - i < x) { 
						return;
					}
					else {
						for (int j = 1; j < x; j++) {
							if (height - map[++i][c] != 1)
								return;
						}
					}
					height = map[i][c]; 
					length = 0;
				}
				else if (height - map[i][c] == -1) { 
					if (length >= x) {
						height = map[i][c];
						length = 1;
					}
					else
						return;
				}
				else 
					return;
			}
			count++;
		}	
	}

	public static void colSearch(int r, int c) {
		int length = 1;
		int height = map[r][c];

		if (c == 0) {
			for (int i = 1; i < n; i++) 
				if (height - map[r][i] == 0) {
					length++;
				}
				else if (height - map[r][i] == 1) { 
					if (n - i < x) {
						return;
					}
					else {
						for (int j = 1; j < x; j++) {
							if (height - map[r][++i] != 1)
								return;
						}
					}
					height = map[r][i]; 
					length = 0; 
				}
				else if (height - map[r][i] == -1) {
					if (length >= x) {
						height = map[r][i];
						length = 1;
					}
					else
						return;
				}
				else 
					return;
		}
		count++;
	}
}