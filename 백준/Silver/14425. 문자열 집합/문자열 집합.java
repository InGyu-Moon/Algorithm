import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n, m;
	static List<String> list, check;

	static int ans;

	public static void sol() {
		for (String str : check) {
			int low = 0;
			int high = n - 1;
			while (low <= high) {
				int mid = (low + high) / 2;
				if (list.get(mid).equals(str)) {
					ans++;
					break;
				}

				if (list.get(mid).compareTo(str) < 0) {
					low = mid + 1;
				} else {
					high = mid - 1;
				}
			}
		}
	}

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		m = Integer.parseInt(token.nextToken());
		list = new ArrayList<>();
		check = new ArrayList<>();
		ans = 0;
		for (int i = 0; i < n; i++) {
			list.add(input.readLine());
		}
		for (int i = 0; i < m; i++) {
			check.add(input.readLine());
		}
		Collections.sort(list);
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
		System.out.println(ans);
	}

}