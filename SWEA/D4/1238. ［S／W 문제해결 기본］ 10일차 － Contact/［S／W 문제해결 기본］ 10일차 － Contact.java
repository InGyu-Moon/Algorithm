import java.io.*;
import java.util.*;

public class Solution {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n, startNode;
	static List<Integer>[] lists;

	static int from, to;
	static int[] visited;
	static int ans, currMax;

	public static void main(String[] args) throws Exception {
		for (int tc = 1; tc <= 10; tc++) {
			token = new StringTokenizer(input.readLine());
			n = Integer.parseInt(token.nextToken());
			startNode = Integer.parseInt(token.nextToken());
			
//			lists = new ArrayList[101];
			lists = new ArrayList[101];
            for (int i = 0; i <= 100; i++) {
                lists[i] = new ArrayList<>();
            }
            
			visited = new int[101];
			visited[startNode] = 1;
			token = new StringTokenizer(input.readLine());
			for (int i = 0; i < n/2; i++) {
				from = Integer.parseInt(token.nextToken());
				to = Integer.parseInt(token.nextToken());
				lists[from].add(to);
			}
			Queue<Integer> que = new LinkedList<Integer>();
			que.add(startNode);
			while (!que.isEmpty()) {
				int top = que.poll();
				for (int a : lists[top]) {
					if (visited[a] != 0)
						continue;
					visited[a] = visited[top] + 1;
					que.add(a);
				}
			}
			for (int i = 1; i <= 100; i++) {
				if (currMax <= visited[i]) {
					currMax = visited[i];
					ans = i;
				}
			}
			output.append("#").append(tc).append(" ").append(ans).append("\n");
			// 초기화
			ans = 0;
			currMax = 0;
		}
		System.out.println(output);
	}
}
