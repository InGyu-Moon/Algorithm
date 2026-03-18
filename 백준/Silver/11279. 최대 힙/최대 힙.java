import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n;
	static PriorityQueue<Integer> pq = new PriorityQueue<>(
				(a,b) -> {
					return -Integer.compare(a, b);
					}
				);

	public static void init() throws Exception {
		n = Integer.parseInt(input.readLine());
		for (int i = 0; i < n; i++) {
			int num = Integer.parseInt(input.readLine());
			if (num == 0) {
				if (pq.size() == 0)
					output.append("0\n");
				else
					output.append(pq.poll()).append("\n");

			} else {
				pq.add(num);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		init();
		System.out.println(output);
	}
}
