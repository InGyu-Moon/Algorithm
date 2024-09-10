import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n, m;
	static int[] arr;
	static boolean[] visited;
	static int[] selected;

	public static void sol(int depth) {
		if (depth == m) {
			for (int i = 0; i < m; i++) {
				output.append(selected[i]).append(" ");
			}
			output.append("\n");
			return;
		}
		int temp = 0;
		for (int i = 0; i < n; i++) {
			if (visited[i] || temp == arr[i])
				continue;
			visited[i] = true;
			selected[depth] = temp = arr[i];
			sol(depth + 1);
			visited[i] = false;
		}
	}

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		m = Integer.parseInt(token.nextToken());
		token = new StringTokenizer(input.readLine());
		visited = new boolean[n];
		selected = new int[m];
		arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(token.nextToken());
		}
		Arrays.sort(arr);
	}

	public static void main(String[] args) throws Exception {
		init();
		sol(0);
		System.out.println(output);
	}
}
