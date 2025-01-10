
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer token;

    static int n, m;
    static int[] out, in, ans;
    static List<int[]>[] map;
    static PriorityQueue<Integer> que;

    public static void sol() {
        // 초기 작업
        for (int i = 1; i <= n; i++) {
            if (out[i] == 0) {
                for (int[] arr : map[i]) {
                    int y = arr[0];
                    int k = arr[1];
                    if (ans[y] == 0) {
                        que.add(y);
                    }
                    ans[y] += k;
                }
            }
        }

        while (!que.isEmpty()) {
            int top = que.poll();
            if (in[top] == 0)
                continue;

            for (int[] arr : map[top]) {
                int y = arr[0];
                int k = arr[1];
                if (ans[y] == 0) {
                    que.add(y);
                }
                ans[y] += k * ans[top];
            }
            ans[top] = 0;
        }

        for (int idx = 1; idx <= n; idx++) {
            if (in[idx] == 0) {
                output.append(idx).append(" ").append(ans[idx]).append("\n");
            }
        }
    }

    public static void init() throws Exception {
        n = Integer.parseInt(input.readLine());
        m = Integer.parseInt(input.readLine());
        out = new int[n + 1];
        in = new int[n + 1];
        ans = new int[n + 1];
        map = new ArrayList[n + 1];
        que = new PriorityQueue<>((p1, p2) -> Integer.compare(in[p2], in[p1]));

        for (int i = 1; i <= n; i++) {
            map[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            token = new StringTokenizer(input.readLine());
            int x = Integer.parseInt(token.nextToken());
            int y = Integer.parseInt(token.nextToken());
            int k = Integer.parseInt(token.nextToken());
            out[y]++;
            in[x]++;
            map[x].add(new int[] { y, k });
        }
    }

    public static void main(String[] args) throws Exception {
        init();
        sol();
        System.out.println(output);
    }
}