import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n, m, k;

	static Sticker[] stickers;

	static boolean[][] visited;

	static int ans;

	public static void turn(int num) {
		// 스티커를 90도 돌리는 함수
		Sticker sticker = stickers[num];
		int n = sticker.n;
		int m = sticker.m;
		boolean[][] s = sticker.arr;
		boolean[][] temp = new boolean[m][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				temp[j][n - i - 1] = s[i][j];
			}
		}
		sticker.arr = temp;
		sticker.n = m;
		sticker.m = n;
	}

	public static boolean addSticker(int y, int x, int num) {
//		for (int dir = 0; dir < 4; dir++) {
		// 가능한지 확인
		boolean flag = false;
		for (int i = 0; i < stickers[num].n; i++) {
			if (flag)
				break;
			for (int j = 0; j < stickers[num].m; j++) {
				if (y + i >= n || x + j >= m || i >= stickers[num].n || j >= stickers[num].m) {
					flag = true;
					break;
				}
				if (stickers[num].arr[i][j] == true && visited[y + i][x + j] == true) {
					flag = true;
					break;
				}
			}
		}
		// 스티커 추가가 가능하면
		if (!flag) {
			for (int i = 0; i < stickers[num].n; i++) {
				for (int j = 0; j < stickers[num].m; j++) {
					if (stickers[num].arr[i][j] == true) {
						visited[y + i][x + j] = true;
					}
				}
			}
			return true;
		}
//			turn(num);
//		}
		return false;
	}

	public static void sol() {
		// k개의 스티커를 확인해보자
		for (int idx = 0; idx < k; idx++) {
			// 노트북을 다 돌면서 시작 가능한 위치 탐색
			boolean flag = false;
			for (int dir = 0; dir < 4; dir++) {
				if (flag)
					break;
				for (int i = 0; i < n; i++) {
					if (flag)
						break;
					for (int j = 0; j < m; j++) {
						if (addSticker(i, j, idx)) {
							flag = true;
							break;
						}
					}
				}
				turn(idx);
			}
		}
	}

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		m = Integer.parseInt(token.nextToken());
		k = Integer.parseInt(token.nextToken());

		visited = new boolean[n][m];
		stickers = new Sticker[k];

		// k 개의 스티커 입력
		for (int i = 0; i < k; i++) {
			token = new StringTokenizer(input.readLine());
			int sN = Integer.parseInt(token.nextToken());
			int sM = Integer.parseInt(token.nextToken());
			boolean[][] sticker = new boolean[sN][sM];
			for (int j = 0; j < sN; j++) {
				token = new StringTokenizer(input.readLine());
				for (int k = 0; k < sM; k++) {
					sticker[j][k] = Integer.parseInt(token.nextToken()) == 1 ? true : false;
					stickers[i] = new Sticker(sN, sM, sticker);
				}
			}
		}
		// 기타 변수 초기화
		ans = 0;
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (visited[i][j])
					ans++;
			}
		}
		System.out.println(ans);
	}

	static class Sticker {
		int n;
		int m;
		boolean[][] arr;

		public Sticker(int n, int m, boolean[][] arr) {
			super();
			this.n = n;
			this.m = m;
			this.arr = arr;
		}

	}

}