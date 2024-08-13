import java.io.*;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;
	static int n, k;
	static int[] tools = new int[8];
	static boolean[] visited = new boolean[8];
	static int curr = 500;
	static int ans; // 총 경우의 수

	private static void dfs(int nth) {
		// 기저상황
		if (curr < 500) {
			return;
		}
		if (curr >= 500 && nth >= n) {
			ans++;
			return;
		}
		// 재귀상황
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				visited[i] = true;
				curr -= k;			// 매일 k 만큼 감소
				curr += tools[i];	// 운동해서 증가
				dfs(nth + 1);
				curr += k;
				curr -= tools[i];
				visited[i] = false;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		k = Integer.parseInt(token.nextToken());

		token = new StringTokenizer(input.readLine());
		for (int i = 0; i < n; i++) {
			tools[i] = Integer.parseInt(token.nextToken());
		}

		dfs(0);
		System.out.println(ans);
	}
}
