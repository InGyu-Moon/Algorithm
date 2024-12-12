import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int[] dy = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] dx = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static int n, m, k;
	static int[][] arr, energy;

	static PriorityQueue<Pair> tree;
	static List<Pair> list, summer;

	public static void addTree() {
		// list에 있는 나무를 tree에 넣어줌
		for (Pair pair : list) {
			tree.add(new Pair(pair.y, pair.x, pair.age));
		}
		list.clear();
	}

	public static void spring() {
		// 나무가 자신의 나이만큼 양분을 먹고, 나이가 1 증가한다
		// 나이가 어린 나무부터 양분을 먹는다
		// 땅에 양분이 부족해 자신의 나이만큼 양분을 먹을 수 없는 나무는 죽는다
		list = new ArrayList<>();
		summer = new ArrayList<>();
		while (!tree.isEmpty()) {
			Pair pair = tree.poll();
			int y = pair.y;
			int x = pair.x;
			int age = pair.age;
			arr[y][x] -= age;
			if (arr[y][x] >= 0) { // 양분 섭취 O
				age++;
				list.add(new Pair(y, x, age));
			} else { // 양분 섭취 X
				arr[y][x] += age; // 뻇던 양분 복구
				// 여름에 양분이 될 나무들
				summer.add(new Pair(y, x, age));
			}
		}
		addTree();
	}

	public static void summer() {
		// 봄에 죽은 나무가 양분으로 변하게 된다
		// 죽은 나무마다 나이를 2로 나눈 값
		for (Pair pair : summer) {
			int y = pair.y;
			int x = pair.x;
			int age = pair.age;
			age /= 2;
			arr[y][x] += age;
		}
		summer.clear();
	}

	public static void fall() {
		// 나이가 5의 배수인 나무가 번식한다
		// 인접한 8개의 칸에 나이가 1인 나무가 생긴다
		list = new ArrayList<>();
		for (Pair pair : tree) {
			int y = pair.y;
			int x = pair.x;
			int age = pair.age;
			if (age % 5 == 0) {
				for (int i = 0; i < 8; i++) {
					int ny = y + dy[i];
					int nx = x + dx[i];
					if (ny < 0 || nx < 0 || ny >= n || nx >= n)
						continue;
					list.add(new Pair(ny, nx, 1));
				}
			}
		}
		addTree();
	}

	public static void winter() {
		// 땅에 양분을 추가한다
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				arr[i][j] += energy[i][j];
			}
		}
	}

	public static void sol() {
		while (k-- > 0) {
			spring();
			summer();
			fall();
			winter();
		}
	}

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		m = Integer.parseInt(token.nextToken());
		k = Integer.parseInt(token.nextToken());
		arr = new int[n][n];
		energy = new int[n][n];
		tree = new PriorityQueue<>((a, b) -> {
			return Integer.compare(a.age, b.age);
		});
		for (int i = 0; i < n; i++) {
			token = new StringTokenizer(input.readLine());
			for (int j = 0; j < n; j++) {
				energy[i][j] = Integer.parseInt(token.nextToken());
				arr[i][j] = 5;
			}
		}
		for (int i = 0; i < m; i++) {
			token = new StringTokenizer(input.readLine());
			// y, x 변경했음
			int y = Integer.parseInt(token.nextToken()) - 1;
			int x = Integer.parseInt(token.nextToken()) - 1;
			int z = Integer.parseInt(token.nextToken());
			tree.add(new Pair(y, x, z));
		}
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
		System.out.println(tree.size());
	}

	static class Pair {
		int y;
		int x;
		int age;

		public Pair(int y, int x, int age) {
			this.y = y;
			this.x = x;
			this.age = age;
		}

	}
}