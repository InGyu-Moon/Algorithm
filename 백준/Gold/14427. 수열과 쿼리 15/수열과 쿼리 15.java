import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer token;

    static int n, m;
    static int len;
    static int[] tree;
    static String[] inputArr;

    // findIdx 함수에서 parent의 범위가 len을 넘어가지 않도록 수정
    public static int findIdx() {
        int parent = 1;
        int left = parent * 2, right = parent * 2 + 1;
        while (left < len * 2) { // left는 2배를 계속 증가하므로 len * 2보다 작은지 확인
            parent = (tree[left] == tree[parent]) ? left : right;
            left = parent * 2;
            right = parent * 2 + 1;
        }
        return parent - len + 1;
    }

    public static void updateTree(int idx) {
        int parent = idx / 2;
        int left, right;
        while (parent > 0) {
            left = parent * 2;
            right = parent * 2 + 1;
            tree[parent] = Math.min(tree[left], tree[right]);
            parent /= 2;
        }
    }

    // 트리 초기화 함수
    public static void initTree() {
        for (int i = len * 2 - 1; i > 1; i -= 2) {
            int parent = i / 2;
            tree[parent] = Math.min(tree[i], tree[i - 1]);
        }
    }

    public static void sol() {
        for (String str : inputArr) {
            token = new StringTokenizer(str);
            String in = token.nextToken();
            if (in.equals("1")) {
                // update 수행
                int i = Integer.parseInt(token.nextToken());
                int v = Integer.parseInt(token.nextToken());
                tree[len + i - 1] = v;
                updateTree(len + i - 1);
            } else if (in.equals("2")) {
                // 최소값의 index를 output 에 추가
                int ans = findIdx();
                output.append(ans).append("\n");
            }
        }
    }

    public static void init() throws Exception {
        n = Integer.parseInt(input.readLine());
        // len을 2의 거듭제곱으로 올림
        len = 1 << (int)(Math.log(n) / Math.log(2));
        if (len < n) len *= 2; // n이 2의 거듭제곱이 아닌 경우에 대비

        tree = new int[len * 2];
        token = new StringTokenizer(input.readLine());
        for (int i = 0; i < n; i++) {
            tree[len + i] = Integer.parseInt(token.nextToken());
        }
        Arrays.fill(tree, len + n, len * 2, Integer.MAX_VALUE); // 빈 공간을 Integer.MAX_VALUE로 채움
        m = Integer.parseInt(input.readLine());
        inputArr = new String[m];
        for (int i = 0; i < m; i++) {
            inputArr[i] = input.readLine();
        }
        initTree();
    }

    public static void main(String[] args) throws Exception {
        init();
        sol();
        System.out.println(output);
    }
}