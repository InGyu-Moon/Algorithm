
import java.io.*;
import java.util.*;

public class Solution {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n, m;
	static int[][] arr;
	static int k;
	static int home;

	public static int search(int y, int x, int k) {
		int cnt = 0;
		for (int i = -k; i <= k; i++) {
			for (int j = -k; j <= k; j++) {
				int d = Math.abs(i) + Math.abs(j);
				if (d >= k) // 거리가 k를 넘어가면 continue;
					continue;
				int ny = y + i;
				int nx = x + j;
				if (ny < 0 || nx < 0 || ny >= n || nx >= n)
					continue;
				if (arr[y + i][x + j] == 1) // 해당 위치가 집이면 cnt++;
					cnt++;
			}
		}
		return cnt;
	}

	public static int sol() {
		int maxVal = 0;
		k = n + 1;
		while (k > 0) {
			int cost = (2 * k) * (k - 1) + 1 - (m * home);
			if (cost <= 0) {
				// 부등식이 만족을 하면 -> 집위치를 중심으로 탐색 시작
				int temp = maxVal;

				for (int i = 0; i < n; i++) {
					for (int j = 0; j < n; j++) {
						int result = search(i, j, k);
						int cost2 = (2 * k) * (k - 1) + 1 - (m * result);
						if (cost2 > 0)
							continue;
						maxVal = maxVal > result ? maxVal : result; // maxVal 최신화
					}
				}

				if (temp != maxVal) // 변경사항이 있었으면 종료
					break;
			}
			// 부등식이 만족을 안하면 -> cost 가 0보다 크다
			// -> 무조건 손해 -> 서비스 지역 축소 -> k 감소
			k--;
		}
		return maxVal;
	}

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		m = Integer.parseInt(token.nextToken());
		arr = new int[n][n];
		home = 0;
		for (int i = 0; i < n; i++) {
			token = new StringTokenizer(input.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(token.nextToken());
				if (arr[i][j] == 1)
					home++;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		int TC = Integer.parseInt(input.readLine());
		for (int t = 1; t <= TC; t++) {
			init();
			output.append("#").append(t).append(" ").append(sol()).append("\n");
		}
		System.out.println(output);
	}
}
