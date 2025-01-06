import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n;
	static int[][] game;

	static int ans;

	static boolean[] visited;
	static int[] selected;
	static boolean[] base;

	public static int play() {
		int score = 0;
		int out = 0;
		int innings = 1;
		int currPlayer = 1;
		while (innings <= n) {
			// 새 이닝 시작
			// 베이스 초기화
			for (int i = 0; i < 4; i++) {
				base[i] = false;
			}
			out = 0; // 아웃 수 초기화
			while (out < 3) {
				int player = selected[currPlayer];
				if (game[innings][player] == 0) {
					out++;
				} else { // hit 값이 1~4 (출루 하는 경우)
					score += moveAndGetScore(game[innings][player]);
				}
				currPlayer++; // 다음타자
				if (currPlayer == 10) // 10번 타자는 없다
					currPlayer = 1;
			}
			innings++; // 다음 이닝
		}
		return score;
	}

	public static int moveAndGetScore(int hit) {
		int score = 0;
		if (hit == 4) {
			score++;
			for (int i = 1; i <= 3; i++) { // 1루~3루 확인
				if (base[i]) {
					score++;
					base[i] = false;
				}
			}
		} else if (hit == 3) { // 3루타
			for (int i = 1; i <= 3; i++) { // 3루까지
				if (base[i]) { // 주자가 있으면
					score++;
					base[i] = false;
				}
			}
			base[3] = true;// 타자 진루
		} else if (hit == 2) { // 2루타
			for (int i = 2; i <= 3; i++) { // 2루, 3루는 득점
				if (base[i]) { // 주자가 있으면
					score++;
					base[i] = false;
				}
			}
			// 1루는 진루
			if (base[1]) {
				base[3] = true;
				base[1] = false;
			}
			base[2] = true;// 타자 진루

		} else if (hit == 1) { // 1루타
			if (base[3]) { // 3루는 득점
				score++;
				base[3] = false;
			}
			for (int i = 2; i >= 1; i--) { // 1루, 2루는 진루 (2루부터 확인)
				if (base[i]) {
					base[i] = false;
					base[i + 1] = true;
				}
			}
			base[1] = true; // 타자 진루
		}
		return score;
	}

	public static void dfs(int depth) {
		if (depth == 10) {
			ans = Math.max(ans, play());
			return;
		}
		if (depth == 4) {
			selected[depth] = 1;
			dfs(depth + 1);
		}
		for (int i = 1; i <= 9; i++) {
			if (visited[i] || i == 1) {
				continue;
			}
			visited[i] = true;
			selected[depth] = i;
			dfs(depth + 1);
			visited[i] = false;
		}
	}

	public static void init() throws Exception {
		n = Integer.parseInt(input.readLine());
		game = new int[n + 1][10];
		for (int i = 1; i <= n; i++) {
			token = new StringTokenizer(input.readLine());
			for (int j = 1; j <= 9; j++) {
				game[i][j] = Integer.parseInt(token.nextToken());
			}
		}
		visited = new boolean[10];
		selected = new int[10];
		ans = Integer.MIN_VALUE;
		base = new boolean[4];
	}

	public static void main(String[] args) throws Exception {
		init();
		dfs(1);
		System.out.println(ans);
	}
}
