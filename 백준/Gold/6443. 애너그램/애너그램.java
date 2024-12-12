import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static String str;
	static char[] chars, selected;
	static boolean[] visited;

//	static Set<String> set;
	static List<String> set;

	public static void dfs(int depth) {
		if (depth == str.length()) {
			set.add(String.valueOf(selected));
			return;
		}

		char prev = '\0';
		for (int i = 0; i < str.length(); i++) {
			if (visited[i] || chars[i] == prev)
				continue;
			visited[i] = true;
			selected[depth] = chars[i];
			prev = chars[i];
			dfs(depth + 1);
			visited[i] = false;
		}

	}

	public static void init() throws Exception {
//		set = new TreeSet<>();
		set = new ArrayList<>();
		str = input.readLine();
		chars = new char[str.length()];
		selected = new char[str.length()];
		visited = new boolean[str.length()];
		for (int i = 0; i < str.length(); i++) {
			chars[i] = str.charAt(i);
		}
		Arrays.sort(chars);
	}

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(input.readLine());
		for (int t = 0; t < T; t++) {
			init();
			dfs(0);
			for (String str : set) {
				output.append(str).append("\n");
			}
		}
		System.out.println(output);
	}
}