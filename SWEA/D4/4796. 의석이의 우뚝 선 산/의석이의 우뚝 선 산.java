

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {

	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;
	static int n;
	static int ans;
	static int[] arr;

	private static List<Integer> findMountain() {
		List<Integer> list = new ArrayList<>();
		for (int i = 1; i < n - 1; i++) {
			if (arr[i - 1] < arr[i] && arr[i] > arr[i + 1]) {
				list.add(i);
			}
		}
		return list;
	}

	private static void sol() {
		List<Integer> list = findMountain();
		for (int i : list) {
			int inc = 0, dec = 0;
			int temp = i;
			// 왼쪽 탐색
			while (temp > 0 && arr[temp - 1] < arr[temp]) {
				dec++;
				temp--;
			}
			// 오른쪽 탐색
			temp = i;
			while (temp < n-1 && arr[temp] > arr[temp + 1]) {
				inc++;
				temp++;
			}
			ans += inc * dec;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = Integer.parseInt(sc.nextLine());
		for (int tc = 0; tc < T; tc++) {
			n = sc.nextInt();
			arr = new int[n];

//			token = new StringTokenizer(sc.nextLine());
			for (int i = 0; i < n; i++) {
				arr[i] = sc.nextInt();
			}

			sol();

			// 출력
			output.append("#").append(tc + 1).append(" ").append(ans).append("\n");

			// 초기화
			ans = 0;
		}
		System.out.println(output);

	}
}
