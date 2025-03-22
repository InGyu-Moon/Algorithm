import java.io.*;
import java.util.*;

public class Main {

	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, 1, -1 };

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n, m, s;
	static int[][] arr;
	static Shark[] sharks;
	static int ans;

	public static void print() {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void sol() {
		for (int i = 1; i <= m; i++) {
			getFish(i);
			moveShark();
		}
	}

	public static void getFish(int col) {
		for (int i = 1; i <= n; i++) {
			if (arr[i][col] != 0) {
				sharks[arr[i][col]].flag = false;
				ans += sharks[arr[i][col]].size;
				arr[i][col] = 0;
				break;
			}
		}
	}

	public static void moveShark() {
	    // 새로 만들 임시 맵
	    int[][] newArr = new int[n + 1][m + 1];

	    for (Shark shark : sharks) {
	        if (!shark.flag) continue;
	        move(shark);

	        int y = shark.y;
	        int x = shark.x;

	        if (newArr[y][x] == 0) {
	            newArr[y][x] = shark.idx;
	        } else {
	            Shark other = sharks[newArr[y][x]];
	            if (other.size < shark.size) {
	                other.flag = false;
	                newArr[y][x] = shark.idx;
	            } else {
	                shark.flag = false;
	            }
	        }
	    }

	    // arr 갱신
	    arr = newArr;
	}

	public static void move(Shark shark) {
	    int dist = shark.v;
	    int dir = shark.dir;

	    if (dir == 0 || dir == 1) {
	        int moveLen = dist % (2 * (n - 1));
	        for (int i = 0; i < moveLen; i++) {
	            if (shark.y == 1 && dir == 0) dir = 1;
	            else if (shark.y == n && dir == 1) dir = 0;
	            shark.y += dy[dir];
	        }
	    } else {
	        int moveLen = dist % (2 * (m - 1));
	        for (int i = 0; i < moveLen; i++) {
	            if (shark.x == 1 && dir == 3) dir = 2;
	            else if (shark.x == m && dir == 2) dir = 3;
	            shark.x += dx[dir];
	        }
	    }

	    shark.dir = dir;
	}

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		m = Integer.parseInt(token.nextToken());
		s = Integer.parseInt(token.nextToken());
		arr = new int[n + 2][m + 2];
		sharks = new Shark[s + 1];
		sharks[0] = new Shark(0, false, 0, 0, 0, 0, 0);
		int idx = 1;
		for (int i = 0; i < s; i++) {
			token = new StringTokenizer(input.readLine());
			int y = Integer.parseInt(token.nextToken());
			int x = Integer.parseInt(token.nextToken());
			int v = Integer.parseInt(token.nextToken());
			int dir = Integer.parseInt(token.nextToken()) - 1;
			int size = Integer.parseInt(token.nextToken());
			sharks[i + 1] = new Shark(idx, true, y, x, v, dir, size);
			arr[y][x] = idx++;
		}
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
		System.out.println(ans);
	}

	static class Shark {
		int idx;
		boolean flag;
		int y;
		int x;
		int v;
		int dir;
		int size;

		public Shark(int idx, boolean flag, int y, int x, int v, int dir, int size) {
			this.idx = idx;
			this.flag = flag;
			this.y = y;
			this.x = x;
			this.v = v;
			this.dir = dir;
			this.size = size;
		}
	}
}