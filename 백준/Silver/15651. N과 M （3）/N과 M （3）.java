import java.io.*;
import java.util.*;

class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n, m;
	static boolean[] visited;
	static int[] selected;

	public static void permutation(int depth) {
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
			selected[depth] = i + 1;
			permutation(depth + 1);
		}

	}

	public static void main(String args[]) throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		m = Integer.parseInt(token.nextToken());
		visited = new boolean[n];
		selected = new int[m];
		permutation(0);
		System.out.println(output);
	}
}