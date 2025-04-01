import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer token;

    static int T, m;
    static int[] arr;
    static PriorityQueue<Integer> left, right;

    public static void sol() {
        List<Integer> temp = new ArrayList<>();
        // 최대 힙 (내림차순)
        left = new PriorityQueue<>(Comparator.reverseOrder());
        // 최소 힙 (오름차순)
        right = new PriorityQueue<>();

        // 모든 m개의 숫자에 대해 처리 (0-indexed)
        for (int i = 0; i < m; i++) {
            int n = arr[i];
            // 두 힙의 크기가 같으면 left(최대 힙)에, 그렇지 않으면 right(최소 힙)에 추가
            if (left.size() == right.size())
                left.add(n);
            else
                right.add(n);
            
            // 두 힙이 모두 비어있지 않다면, 올바른 중앙값 순서를 위해 교환
            if (!right.isEmpty() && left.peek() > right.peek()){
                int a = left.poll();
                int b = right.poll();
                left.add(b);
                right.add(a);
            }
            
            // 홀수 개의 숫자를 읽었을 때 (즉, i가 짝수일 때) 중앙값 저장
            if (i % 2 == 0) {
                temp.add(left.peek());
            }
        }
        
        // 중앙값의 개수 출력
        output.append(temp.size()).append("\n");
        // 중앙값들을 한 줄에 10개씩 출력
        for (int i = 0; i < temp.size(); i++) {
            output.append(temp.get(i));
            if ((i + 1) % 10 == 0 || i == temp.size() - 1) {
                output.append("\n");
            } else {
                output.append(" ");
            }
        }
    }

    public static void init() throws Exception {
        m = Integer.parseInt(input.readLine());
        arr = new int[m];
        int idx = 0;
        while (idx < m) {
            token = new StringTokenizer(input.readLine());
            while (token.hasMoreTokens()) {
                arr[idx++] = Integer.parseInt(token.nextToken());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        T = Integer.parseInt(input.readLine());
        for (int t = 0; t < T; t++) {
            init();
            sol();
        }
        System.out.print(output);
    }
}