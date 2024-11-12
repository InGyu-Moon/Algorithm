import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n, m;

	static int[] selected;
	static boolean[] visited;

	public static void dfs(int depth, int next) {
		// 종료
		if (depth == m) {
			for (int num : selected) {
				output.append(num).append(" ");
			}
			output.append("\n");
			return;
		}
		// 반복
		for (int i = next; i < n; i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			selected[depth] = i + 1;
			dfs(depth + 1, i + 1);
			visited[i] = false;
		}
	}

	public static void init() throws IOException {
		// 입력
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		m = Integer.parseInt(token.nextToken());

		// 초기화
		selected = new int[m];
		visited = new boolean[n + 1];

	}

	public static void main(String[] args) throws IOException {
		init();
		dfs(0, 0);
		System.out.println(output);
	}

}