import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder outpu = new StringBuilder();
	static StringTokenizer token;

	static int n, m;
	static int[][] arr;

	static int currMax;
	static int max = Integer.MIN_VALUE;
//	static int leftMax, rightMax, upMax, downMax;

	static int[] tempArr;

	public static void getMaxSubArr() {
		int[] dp = new int[n];
		dp[0] = tempArr[0];
		for (int i = 1; i < n; i++) {
			dp[i] = Math.max(tempArr[i], tempArr[i] + dp[i - 1]);
		}
		Arrays.sort(dp);
		currMax = dp[n - 1];
	}

	public static void addToTempArr(int j) {
		for (int i = 0; i < n; i++) {
			tempArr[i] += arr[i][j];
		}
	}

	public static void sol() {
		for (int i = 0; i < m; i++) {
			tempArr = new int[n];
			for (int j = i; j < m; j++) {
				// temp 배열에 현재 index 배열 값 추가
				addToTempArr(j);
				// max sub arr 구하기
				getMaxSubArr();

				max = Math.max(max, currMax);
			}
		}
	}

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		m = Integer.parseInt(token.nextToken());
		arr = new int[n][m];
		for (int i = 0; i < n; i++) {
			token = new StringTokenizer(input.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(token.nextToken());
			}
		}
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
		System.out.println(max);
	}

}