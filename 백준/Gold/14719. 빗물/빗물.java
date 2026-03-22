import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int h, w;
	static int[] arr;
	static int sum = 0;

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		h = Integer.parseInt(token.nextToken()); // 세로
		w = Integer.parseInt(token.nextToken()); // 가로
		arr = new int[w];
		token = new StringTokenizer(input.readLine());
		for (int i = 0; i < w; i++) {
			arr[i] = Integer.parseInt(token.nextToken());
		}
	}

	public static void ans() {
		for (int i = 1; i < w - 1; i++) {
			int leftMax = 0, rightMax = 0;
			// left
			for (int j = 0; j < i; j++) {
				leftMax = Math.max(leftMax, arr[j]);
			}
			// right
			for (int j = i; j < w; j++) {
				rightMax = Math.max(rightMax, arr[j]);
			}
			int min = Math.min(leftMax, rightMax);
			if (min > arr[i])
				sum += min - arr[i];
		}
	}

	public static void main(String[] args) throws Exception {
		init();
		ans();
		System.out.println(sum);
	}
}
