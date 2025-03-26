import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static long n, p, q;
	static Map<Long, Long> map;

	public static long sol(long num) {
		if (num == 0)
			return 1;

		long a = num / p, b = num / q;
		if (!map.containsKey(a))
			map.put(a, sol(a));
		if (!map.containsKey(b))
			map.put(b, sol(b));
		map.put(num, map.get(a) + map.get(b));
		return map.get(num);
	}

	public static void init() throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Long.parseLong(token.nextToken());
		p = Long.parseLong(token.nextToken());
		q = Long.parseLong(token.nextToken());
		map = new HashMap<>();
		map.put((long) 0, (long) 1);
	}

	public static void main(String[] args) throws Exception {
		init();
		System.out.println(sol(n));
	}

}
