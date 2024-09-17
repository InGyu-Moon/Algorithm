import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n;
	static int[] arr, sorted;

	static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

	public static void sol() {
		int num = 0;
		for (int i = 0; i < n; i++) {
			if (!map.containsKey(sorted[i])) {
				map.put(sorted[i], num);
				num++;
			}
		}
		for (int i = 0; i < n; i++) {
			output.append(map.get(arr[i])).append(" ");
		}
	}

	public static void init() throws Exception {
		n = Integer.parseInt(input.readLine());
		arr = new int[n];
		sorted = new int[n];
		token = new StringTokenizer(input.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = sorted[i] = Integer.parseInt(token.nextToken());
		}
		Arrays.sort(sorted);
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
		System.out.println(output);
	}
}