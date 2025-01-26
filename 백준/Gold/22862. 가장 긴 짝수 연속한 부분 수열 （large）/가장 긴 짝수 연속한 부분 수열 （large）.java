import java.util.*;
import java.io.*;

public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n, k;
	static int[] arr;

	static int ans;

	public static void sol() {
		int start = 0, end = 0;
		int len = 0;
		while (end < n) {
			if (arr[end] % 2 == 0) { // 짝수
				len++;
				end++;
				ans = Math.max(ans, len);
			} else if (arr[end] % 2 == 1) { // 홀수
				if (k > 0) { // 삭제 가능
					end++;
					k--;
				} else if (k == 0) { // 삭제 불가
					if (arr[start] % 2 == 0) {
						len--;
						start++;
					} else if (arr[start] % 2 == 1) {
						start++;
						k++;
					}

				}
			}
		}
	}

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		k = Integer.parseInt(token.nextToken());
		arr = new int[n];
		token = new StringTokenizer(input.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(token.nextToken());
		}
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
		System.out.println(ans);
	}
}