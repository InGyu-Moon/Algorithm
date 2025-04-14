import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer token;

	static final long INF = Long.MAX_VALUE;

	static int n, m, k;
	static Map<Integer, List<long[]>> map;
	static PriorityQueue<long[]> pq;
	static List<Integer> list;

	static long[] d;

	public static void sol() {
		d = new long[n + 1];
		Arrays.fill(d, INF);

		pq = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));

		// 여러 면접장을 시작점으로 동시에 다익스트라 시작
		for (int start : list) {
			d[start] = 0;
			pq.add(new long[] { start, 0 });
		}

		while (!pq.isEmpty()) {
			long[] top = pq.poll();
			int cur = (int) top[0];
			long dist = top[1];

			if (d[cur] < dist) continue;

			if (!map.containsKey(cur)) continue;

			for (long[] next : map.get(cur)) {
				int to = (int) next[0];
				long weight = next[1];
				if (d[to] > d[cur] + weight) {
					d[to] = d[cur] + weight;
					pq.add(new long[] { to, d[to] });
				}
			}
		}

		// 최댓값 및 해당 노드 찾기
		long maxVal = -1;
		int idx = 0;
		for (int i = 1; i <= n; i++) {
			if (d[i] != INF && d[i] > maxVal) {
				maxVal = d[i];
				idx = i;
			}
		}

		System.out.println(idx);
		System.out.println(maxVal);
	}

	public static void init() throws Exception {
		map = new HashMap<>();
		list = new ArrayList<>();

		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		m = Integer.parseInt(token.nextToken());
		k = Integer.parseInt(token.nextToken());

		for (int i = 0; i < m; i++) {
			token = new StringTokenizer(input.readLine());
			int a = Integer.parseInt(token.nextToken());
			int b = Integer.parseInt(token.nextToken());
			int c = Integer.parseInt(token.nextToken());

			// 역방향 그래프 저장
			map.putIfAbsent(b, new ArrayList<>());
			map.get(b).add(new long[] { a, c });
		}

		token = new StringTokenizer(input.readLine());
		for (int i = 0; i < k; i++) {
			list.add(Integer.parseInt(token.nextToken()));
		}
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
	}
}