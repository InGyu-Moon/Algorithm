import java.io.*;
import java.util.*;
public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n;
	static int[] arr;
	static List<Integer> list;

	public static void sol() {
		for (int i = 0; i < n; i++) {
			int idx = binary(arr[i]);
			if (idx >= list.size())
				list.add(arr[i]);
			else
				list.set(idx, arr[i]);
		}
		System.out.println(list.size());
	}

	public static int binary(int num) {
		int start = 0;
		int end = list.size() - 1;
		while (start <= end) {
			int mid = (start + end) / 2;
			if (list.get(mid) < num) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		return start;
	}

	public static void init() throws Exception {
		n = Integer.parseInt(input.readLine());
		arr = new int[n];
		token = new StringTokenizer(input.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(token.nextToken());
		}
		list = new ArrayList<>();
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
	}
}
