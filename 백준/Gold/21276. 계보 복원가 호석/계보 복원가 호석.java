import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n, m;
	static String[] people;

	static Map<String, List<String>> map, ans;
	static Map<String, Integer> in, out;

	static PriorityQueue<String> pq;
	static List<String> root;

	public static void sol() {
		// 시조 찾기
		findRoot();
		topologySort();
		print();
	}

	public static void print() {
		Arrays.sort(people);
		for (int i = 0; i < n; i++) {
			String name = people[i];
			List<String> list = ans.get(name);
			if (list == null) {
				list = new ArrayList<>();
			} else {
				Collections.sort(list);
			}
			output.append(name).append(" ").append(list.size()).append(" ");
			for (int j = 0; j < list.size(); j++) {
				output.append(list.get(j)).append(" ");
			}
			output.append("\n");
		}
		System.out.println(output);
	}

	public static void topologySort() {
		// pq 삽입
		for (int i = 0; i < n; i++) {
			pq.add(people[i]);
		}

		while (!pq.isEmpty()) {
			String name = pq.poll();
			List<String> list = map.get(name);
			for (String baby : list) {
				out.put(baby, out.get(baby) - 1); // 자식의 out 값 1 감소
				if (out.get(baby).equals(0)) { // 연결된 자식들중 out 값이 0 이면 직계임
					if (ans.get(name) == null) {
						ans.put(name, new ArrayList<>());
					}
					ans.get(name).add(baby); // ans에 직계 자손 추가
				}
			}
		}
	}

	public static void findRoot() {
		root = new ArrayList<>();
		for (String name : people) {
			if (out.get(name) == 0) {
				root.add(name);
			}
		}
		Collections.sort(root);
		output.append(root.size()).append("\n");
		for (int i = 0; i < root.size(); i++) {
			output.append(root.get(i)).append(" ");
		}
	}

	public static void init() throws Exception {
		n = Integer.parseInt(input.readLine());
		people = new String[n];
		token = new StringTokenizer(input.readLine());
		for (int i = 0; i < n; i++) {
			people[i] = token.nextToken();
		}

		m = Integer.parseInt(input.readLine());
		map = new HashMap<>(); // 부모가 key 임
		in = new HashMap<>();
		out = new HashMap<>();
		ans = new HashMap<>();

		for (int i = 0; i < n; i++) {
			String name = people[i];
			in.put(name, 0);
			out.put(name, 0);
			map.put(name, new ArrayList<>());
			ans.put(name, new ArrayList<>());
		}

		for (int i = 0; i < m; i++) {
			token = new StringTokenizer(input.readLine());
			String x = token.nextToken();
			String y = token.nextToken();
			if (map.get(y) == null) {
				map.put(y, new ArrayList<>());
			}
			map.get(y).add(x);
			in.put(y, in.getOrDefault(y, 0) + 1);
			out.put(x, out.getOrDefault(x, 0) + 1);
		}

		pq = new PriorityQueue<>((str1, str2) -> { // 알파벳 순은 필요 없을듯
			if (in.get(str1) == in.get(str2)) {
				return str1.compareTo(str2);
			} else {
				return -Integer.compare(in.get(str1), in.get(str2));
			}
		});
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
	}

}