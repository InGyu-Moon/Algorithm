import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n, m;
	static HashMap<String, List<Pair>> map;
	static Set<String> set;
	static int ans;

	public static void getFiles(String folder) {
		List<Pair> list = map.get(folder);
		if (list == null)
			return;
		for (int i = 0; i < list.size(); i++) {
			Pair pair = list.get(i);
			if (pair.type == 0) {
				set.add(pair.child);
				ans++;
			} else if (pair.type == 1) {
				getFiles(pair.child);
			}
		}
	}

	public static void print() throws Exception {
		int T = Integer.parseInt(input.readLine());
		for (int t = 0; t < T; t++) {
			set = new HashSet<String>();
			ans = 0;
			String str = input.readLine();
			String folder = getName(str);
			getFiles(folder);
			output.append(set.size()).append(" ").append(ans).append("\n");
		}
	}

	public static void moveFunc(String A, String B) {
		// A 하위를 B 하위로 이동 A -> B
		List<Pair> list = map.get(getName(A));
		if (list == null)
			return;
		List<Pair> destList = map.get(getName(B));
		if (destList == null) {
			map.put(getName(B), new ArrayList<>());
			destList = map.get(getName(B));
		}
		for (int i = 0; i < list.size(); i++) {
			Pair pair = list.get(i);
			if (pair.type == 0) {
				String file = pair.child;
				map.get(getName(B)).add(new Pair(file, 0));
				list.remove(i); 
				i--;
			} else if (pair.type == 1) {
				map.get(getName(B)).add(new Pair(pair.child, 1));
				map.get(getName(A)).remove(i);
				i--;
			}
		}

		List<Pair> temp = map.get(getName(B));
		Collections.sort(temp, (a, b) -> a.child.compareTo(b.child));
		for (int j = 0; j < temp.size() - 1; j++) {
			if (temp.get(j).child.equals(temp.get(j + 1).child)) {
				temp.remove(j);
				j--;
			}
		}
	}

	public static String getName(String path) {
		int startAt = 0;
		for (int i = path.length() - 1; i >= 0; i--) {
			if (i == 0) {
				startAt = 0;
				break;
			} else if (path.charAt(i) == '/') {
				startAt = i + 1;
				break;
			}
		}
		return path.substring(startAt);
	}

	public static void move() throws Exception {
		int T = Integer.parseInt(input.readLine());
		for (int t = 0; t < T; t++) {
			token = new StringTokenizer(input.readLine());
			String A = token.nextToken();
			String B = token.nextToken();
			moveFunc(A, B);
		}
	}

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		m = Integer.parseInt(token.nextToken());
		map = new HashMap<>();
		for (int i = 0; i < n + m; i++) {
			token = new StringTokenizer(input.readLine());
			String parent = token.nextToken();
			String child = token.nextToken();
			int type = Integer.parseInt(token.nextToken());
			if (map.get(parent) == null) {
				map.put(parent, new ArrayList<>());
			}
			map.get(parent).add(new Pair(child, type));
		}
	}

	public static void main(String[] args) throws Exception {
		init();
		move();
		print();
		System.out.println(output);
	}

	static class Pair {
		String child;
		int type;
		public Pair(String child, int type) {
			this.child = child;
			this.type = type;
		}
	}
}