import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n, k, cnt;
	static int[] arr;

	static List<Integer> hole = new ArrayList<Integer>(); // 콘센트

	static Map<Integer, Deque<Integer>> map = new HashMap<Integer, Deque<Integer>>();

	static List<int[]> idx = new ArrayList<>();

	public static void sol() {
		for (int i = 0; i < k; i++) {
			if (hole.contains(arr[i])) { // 이미 꼽혀있으면 continue;
				map.get(arr[i]).poll();
				continue;
			}
			if (hole.size() < n) { // 구멍이 남아 있으면 그냥 추가
				hole.add(arr[i]);
				map.get(arr[i]).poll();
			} else if (hole.size() == n) {
				for (int num : hole) { // hole(멀티탭)에 꼽혀있는 애들중
					// 구멍의 queue 가 비어있지 않으면 (다음에 또나옴)
					if (!map.get(num).isEmpty()) {
						idx.add(new int[] { num, map.get(num).peek() });
					} else { // 비어있으면 1순위
						idx.add(new int[] { num, Integer.MAX_VALUE });
					}
				}
				Collections.sort(idx, (a, b) -> { // 사용하려면 많이 남은 친구(위치 값이 큰 친구순으로 정렬)
					return -Integer.compare(a[1], b[1]);
				});
				int target = idx.get(0)[0];
				for (int j = 0; j < hole.size(); j++) {
					if (hole.get(j) == target) {
						hole.remove(j);
						hole.add(arr[i]);
						map.get(arr[i]).poll();
						break;
					}
				}
				cnt++;
				idx.clear();
			}
		}
	}

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		k = Integer.parseInt(token.nextToken());
		arr = new int[k + 1];
		token = new StringTokenizer(input.readLine());
		for (int i = 0; i < k; i++) {
			arr[i] = Integer.parseInt(token.nextToken());

			if (map.get(arr[i]) == null)
				map.put(arr[i], new LinkedList<Integer>());

			map.get(arr[i]).add(i);
		}
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
		System.out.println(cnt);
	}
}