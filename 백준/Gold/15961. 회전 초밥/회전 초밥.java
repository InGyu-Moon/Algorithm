import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n, d, k, c;

	static List<Integer> list;
	static int[] cnt;

	static int ans, temp;

	public static void sol() {
		temp = 0;
		Deque<Integer> deq = new ArrayDeque<Integer>();
		for (int i = 0; i < k; i++) {
			int num = list.get(i);
			deq.offerLast(num);
			if (cnt[num] == 0) // 처음 먹는 초밥이면 ans 1증가
				temp++;
			cnt[num]++;
		}

		if (cnt[c] == 0) // 쿠폰 초밥을 아직 안먹었으면
			temp++;
		// ans 초기화
		ans = Math.max(ans, temp);
		if (cnt[c] == 0) // 증가한 temp 다시 --
			temp--;
		if (ans >= k + 1)
			return;

		// =================== deque 기본 설정 ==========================

		for (int i = 0; i < n; i++) {
			int start = deq.pollFirst();
			cnt[start]--; // poll 하면 --

			if (cnt[start] == 0) // poll 하면서 한번도 안먹게 되면 temp--
				temp--;

			int end = list.get(i + k);
			deq.offerLast(end);
			cnt[end]++; // offer 하면 ++

			if (cnt[end] == 1) // 처음 먹는 초밥이면 temp++
				temp++;

			if (cnt[c] == 0) // 쿠폰 초밥을 아직 안먹었으면
				temp++;
			// ans 초기화
			ans = Math.max(ans, temp);
			if (cnt[c] == 0) // 증가한 temp 다시 --
				temp--;

			if (ans >= k + 1)
				return;
		}

	}

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		d = Integer.parseInt(token.nextToken());
		k = Integer.parseInt(token.nextToken());
		c = Integer.parseInt(token.nextToken());

		cnt = new int[d + 1];

		list = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			int temp = Integer.parseInt(input.readLine());
			list.add(temp);
		}
		for (int i = 0; i < k; i++) {
			list.add(list.get(i));
		}

	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
		System.out.println(ans);
	}
}