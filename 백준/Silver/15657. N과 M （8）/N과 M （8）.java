import java.io.*;
import java.util.*;

class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n, m;
	static int[] arr;
	static boolean[] visited;
	static int[] selected;

	public static void permutation(int depth, int curr) {
		// 기저조건
		if (depth == m) {
			for (int i = 0; i < m; i++) {
				output.append(selected[i]).append(" ");
			}
			output.append("\n");
			return;
		}
		// 재귀조건
		for (int i = curr; i < n; i++) {
//			if (visited[i])
//				continue;
//			visited[i] = true;
			selected[depth] = arr[i];
			permutation(depth + 1, i);
//			visited[i] = false;
		}

	}

	public static void main(String args[]) throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		m = Integer.parseInt(token.nextToken());
		arr = new int[n];
		token = new StringTokenizer(input.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(token.nextToken());
		}
		Arrays.sort(arr);
		visited = new boolean[n];
		selected = new int[m];
		permutation(0,0);
		System.out.println(output);
	}
}