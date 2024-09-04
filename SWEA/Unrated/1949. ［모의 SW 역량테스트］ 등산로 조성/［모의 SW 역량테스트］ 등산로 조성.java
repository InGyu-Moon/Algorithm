import java.io.*;
import java.util.*;

public class Solution {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer token;

    static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { 1, -1, 0, 0 };

    static int n, k;
    static int[][] arr;
    static List<int[]> list;
    static boolean[][] visited;
    static boolean isDigged;
    static int maxVal;

    private static void dfs(int y, int x, int len) {
//        maxVal = maxVal > len ? maxVal : len;
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (ny < 0 || nx < 0 || ny >= n || nx >= n)
                continue;
            if (visited[ny][nx])
                continue;
            
            visited[ny][nx] = true;
            
            if (arr[ny][nx] >= arr[y][x]) { // 갈곳이 같거나 더 높은경우
                if (isDigged) {  // 이미 땅을 팠으면
                    maxVal = maxVal > len ? maxVal : len;
                    visited[ny][nx] = false; 
                    continue;
                }
                int diff = arr[ny][nx] - arr[y][x];
                if (k > diff) {
                    int temp = arr[ny][nx];
                    arr[ny][nx] = arr[y][x] - 1;
                    isDigged = true;
                    dfs(ny, nx, len + 1);
                    isDigged = false;
                    arr[ny][nx] = temp;
                    visited[ny][nx] = false;
                }else {
                    maxVal = maxVal > len ? maxVal : len;
                    visited[ny][nx] = false;
                    continue;
                }
            } else {
                dfs(ny, nx, len + 1);
                visited[ny][nx] = false;
            }
        }
    }

    private static void sol() {
        for (int[] intArr : list) {
            visited = new boolean[n][n];
            int y = intArr[0];
            int x = intArr[1];
            visited[y][x] = true;
            dfs(y, x, 1);
        }
    }

    private static void init() throws Exception {
        token = new StringTokenizer(input.readLine());
        n = Integer.parseInt(token.nextToken());
        k = Integer.parseInt(token.nextToken());
        int max = 0;
        isDigged = false;
        arr = new int[n][n];
//        visited = new boolean[n][n];
        list = new ArrayList<>();
        maxVal = 0;
        // 입력
        for (int i = 0; i < n; i++) {
            token = new StringTokenizer(input.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(token.nextToken());
                max = max > arr[i][j] ? max : arr[i][j]; // 최대값 구하기
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == max)
                    list.add(new int[] { i, j });
            }
        }

    }

    public static void main(String[] args) throws Exception {
        int TC = Integer.parseInt(input.readLine());
        for (int t = 1; t <= TC; t++) {
            init();
            sol();
            output.append("#").append(t).append(" ").append(maxVal).append("\n");
        }
        System.out.println(output);
    }
}