import java.io.*;
import java.util.*;

public class Main {
	static final int NUM = 1000000;

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n;
	static int[][] inputArr;

	static int[] parents;
	static int[] cnt;

	public static void make() {
		cnt = new int[NUM + 1];
		parents = new int[NUM + 1];
		for (int i = 1; i <= NUM; i++) {
			cnt[i] = 1;
			parents[i] = i;
		}
	}

	public static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if (rootA == rootB)
			return;
		parents[rootA] = rootB;
		cnt[rootB] += cnt[rootA];
		cnt[rootA] = 0;

	}

	public static int find(int a) {
		if (parents[a] == a)
			return a;
		return parents[a] = find(parents[a]);
	}

	public static void sol() {
		for (int[] arr : inputArr) {
			if (arr[0] == 0) {
				union(arr[1], arr[2]);
			} else if (arr[0] == 1) {
				int root = find(arr[1]);
				output.append(cnt[root]).append("\n");
			}
		}
	}

	public static void init() throws Exception {
		n = Integer.parseInt(input.readLine());
		inputArr = new int[n][3];
		for (int i = 0; i < n; i++) {
			token = new StringTokenizer(input.readLine());
			char temp = token.nextToken().charAt(0);
			if (temp == 'I') {
				int a = Integer.parseInt(token.nextToken());
				int b = Integer.parseInt(token.nextToken());
				inputArr[i][0] = 0;
				inputArr[i][1] = a;
				inputArr[i][2] = b;
			} else if (temp == 'Q') {
				int a = Integer.parseInt(token.nextToken());
				inputArr[i][0] = 1;
				inputArr[i][1] = a;
			}
		}
		make();
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
		System.out.println(output);
	}
}