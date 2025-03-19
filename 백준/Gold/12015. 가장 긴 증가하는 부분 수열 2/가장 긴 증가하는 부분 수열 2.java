import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n;
	static int[] arr;

	static int idx = 0;
	static int[] list;

	public static int binary(int num) {
		int start = 0;
		int end = idx;
		while (start <= end) {
			int mid = (start + end) / 2;
			if (list[mid] < num) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		return start;
	}

	public static void sol() {
		for (int i = 0; i < n; i++) {
			int num = binary(arr[i]);
			list[num] = arr[i];
			if (num > idx) {
				idx++;
			}
		}
		System.out.println(idx);
	}

	public static void init() throws Exception {
		n = Integer.parseInt(input.readLine());
		arr = new int[n];
		token = new StringTokenizer(input.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(token.nextToken());
		}
		list = new int[n + 1];
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
	}
}