import java.io.*;
import java.util.*;

class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n, m;

	public static void permutation(int depth, boolean[] visited, int[] selected) {
		// 기저조건
		if (depth == m) {
			for (int i = 0; i < m; i++) {
				output.append(selected[i]).append(" ");
			}
			output.append("\n");
			return;
		}
		// 재귀조건
		for (int i = 0; i < n; i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			selected[depth] = i + 1;
			permutation(depth + 1, visited, selected);
			visited[i] = false;
		}
	}

	public static void main(String args[]) throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		m = Integer.parseInt(token.nextToken());
		permutation(0, new boolean[n], new int[m]);
		System.out.println(output);
	}
}