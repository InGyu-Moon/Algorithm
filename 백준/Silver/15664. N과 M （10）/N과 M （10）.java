import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n, m;
	static int[] arr;
	static int[] selected;
	static boolean[] visited;

	public static void sol(int depth, int curr) {
		if (depth == m) {
			for (int i = 0; i < m; i++) {
				output.append(selected[i]).append(" ");
			}
			output.append("\n");
			return;
		}
		int temp = -1;
		for (int i = curr; i < n; i++) {
			if (visited[i] || temp == arr[i])
				continue;
			temp = arr[i];
			visited[i] = true;
			selected[depth] = arr[i];
			sol(depth + 1, i + 1);
			visited[i] = false;
		}
	}

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		m = Integer.parseInt(token.nextToken());
		arr = new int[n];
		selected = new int[m];
		visited = new boolean[n];
		token = new StringTokenizer(input.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(token.nextToken());
		}
		Arrays.sort(arr);
	}

	public static void main(String[] args) throws Exception {
		init();
		sol(0, 0);
		System.out.println(output);
	}
}