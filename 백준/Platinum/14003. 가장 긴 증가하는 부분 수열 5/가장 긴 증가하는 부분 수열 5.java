import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n;
	static int[] arr;
	static int[] prev;             // 경로 추적용
	static List<Integer> lisIdx;   // LIS에 들어간 값의 인덱스들

	public static void sol() {
		prev = new int[n];
		Arrays.fill(prev, -1);
		lisIdx = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			int idx = binary(arr[i]);
			if (idx == lisIdx.size()) {
				lisIdx.add(i); // 새로 추가
			} else {
				lisIdx.set(idx, i); // 값 덮어쓰기 (최적화)
			}

			// 이전 인덱스 기록 (첫 원소가 아니면)
			if (idx > 0) {
				prev[i] = lisIdx.get(idx - 1);
			}
		}

		// LIS 길이 출력
		System.out.println(lisIdx.size());

		// 실제 LIS 경로 추적 (뒤에서부터)
		Stack<Integer> stack = new Stack<>();
		int now = lisIdx.get(lisIdx.size() - 1);
		while (now != -1) {
			stack.push(arr[now]);
			now = prev[now];
		}

		// 출력
		while (!stack.isEmpty()) {
			System.out.print(stack.pop() + " ");
		}
	}

	// 이진 탐색: arr[i]가 들어갈 위치
	public static int binary(int num) {
		int start = 0;
		int end = lisIdx.size() - 1;
		while (start <= end) {
			int mid = (start + end) / 2;
			if (arr[lisIdx.get(mid)] < num) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		return start;
	}

	public static void init() throws Exception {
		n = Integer.parseInt(input.readLine());
		arr = new int[n];
		token = new StringTokenizer(input.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(token.nextToken());
		}
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
	}
}
