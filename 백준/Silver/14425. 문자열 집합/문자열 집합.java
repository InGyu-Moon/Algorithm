import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n, m, ans;
	static List<String> strList;

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		m = Integer.parseInt(token.nextToken());
		ans = 0;
		strList = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			strList.add(input.readLine());
		}
		for (int i = 0; i < m; i++) {
			String temp = input.readLine();
			for (String str : strList) {
				if (str.equals(temp))
					ans++;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		init();
		System.out.println(ans);
	}

}