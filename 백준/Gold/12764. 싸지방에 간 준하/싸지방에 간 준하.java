import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n;
	static int[] arr;
	static boolean[] computer;
	static int curr;

	static PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> {
		return Integer.compare(a.first, b.first);
	}); // 시작시간, 종료 시간

	static PriorityQueue<Pair> com = new PriorityQueue<Pair>((a, b) -> {
		return Integer.compare(a.second, b.second);
	}); // 컴퓨터번호, 종료 시간

	public static void sol() {

		while (!pq.isEmpty()) {
			if (com.isEmpty()) {
				Pair temp = pq.poll();
				com.add(new Pair(1, temp.second)); // 컴퓨터번호, 종료 시간
				arr[com.peek().first]++;
				computer[1] = true;
			} else {
				Pair top = pq.peek();
				Pair currCom = com.peek();
				if (top.first < currCom.second) {
					for (int i = 1; i < n + 1; i++) {
						if (!computer[i]) {
							com.add(new Pair(i, top.second)); // 사용안하는 컴퓨터중 가장 빠른 번호 부여
							computer[i] = true; // 사용중으로 변경
							arr[i]++; // 사용횟수 증가
							break;
						}
					}
					pq.poll();
				} else {
					computer[com.peek().first] = false; // 컴퓨터 사용 종료로 변경
					com.poll();
				}
			}
		}

		for (int i = 1; i < n + 1; i++) {
			if (arr[i] == 0) {
				output.insert(0, i - 1 + "\n");
				break;
			} else {
				output.append(arr[i]).append(" ");
			}
			if (i == n) {
				output.insert(0, i + "\n");
			}
		}
	}

	public static void init() throws Exception {
		n = Integer.parseInt(input.readLine());
		arr = new int[n + 1];
		computer = new boolean[n + 1];
		for (int i = 0; i < n; i++) {
			token = new StringTokenizer(input.readLine());
			int s = Integer.parseInt(token.nextToken());
			int e = Integer.parseInt(token.nextToken());
			pq.add(new Pair(s, e));
		}
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
		System.out.println(output);
	}

	static class Pair {
		int first;
		int second;

		public Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}

	}

}