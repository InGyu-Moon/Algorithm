import java.io.*;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer token;
	static StringBuilder output = new StringBuilder();
	static int n, m;
	static boolean[] visited = new boolean[9];
	static int[] selected = new int[8];

	private static void comb(int nthChoice, int curr) {
		// 기저
		if (nthChoice >= m) {
			for (int i = 0; i < m; i++) {
				output.append(selected[i]).append(" ");
			}
			output.append("\n");
			return;
		}
		// 재귀
		for (int i = curr; i <= n; i++) {
			selected[nthChoice] = i;			
			comb(nthChoice + 1, i + 1);
		}
	}

	public static void main(String[] args) throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		m = Integer.parseInt(token.nextToken());
		comb(0, 1);
		System.out.println(output);
	}
}
