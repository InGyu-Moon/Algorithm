import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n, l, r;
	static int[] arr;
	static boolean[] visited;
	static long ans = 0;

	public static void sol() {
		while (l < n && r < n) {
			if (visited[arr[r]]) { // visited[arr[r]] == true
				visited[arr[l]] = false;
				ans += r - l;
				l++;
			} else { // visited[arr[r]] == false
				visited[arr[r]] = true;
				r++;
			}
		}
		while (l < n) {
			ans += r - l;
			l++;
		}
	}

	public static void init() throws Exception {
		n = Integer.parseInt(input.readLine());
		token = new StringTokenizer(input.readLine());
		arr = new int[n];
		visited = new boolean[100001];
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