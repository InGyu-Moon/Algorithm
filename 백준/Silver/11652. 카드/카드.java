import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n;
	static long[] arr;
	static long ans;

	public static void sol() {
		int cnt = 1, maxCnt = -1;
		for (int i = 0; i < n; i++) {
			if (arr[i] == arr[i + 1]) {
				cnt++;
			} else {
				if(cnt>maxCnt) {
					maxCnt = cnt;
					ans = arr[i];
				}
				cnt = 1;
			}
		}
	}

	public static void init() throws Exception {
		n = Integer.parseInt(input.readLine());
		arr = new long[n + 1];
		for (int i = 0; i < n; i++) {
			arr[i] = Long.parseLong(input.readLine());
		}
		arr[n] = Long.MAX_VALUE;
		Arrays.sort(arr);
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
		System.out.println(ans);
	}
}