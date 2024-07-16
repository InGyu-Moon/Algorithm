import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder output = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());
		for (int t = 1; t <= testCase; t++) {
			int cnt = 0;
			boolean flag = false;

			String input = br.readLine();
			String[] boxs = input.split(" ");
			// 정수 배열로 변환
			int[] numbers = new int[boxs.length];
			for (int i = 0; i < boxs.length; i++) {
				numbers[i] = Integer.parseInt(boxs[i]);
			}
			Loop1: for (int i = numbers.length - 1; i > 0; i--) {
				while (numbers[i] <= numbers[i - 1]) {
					numbers[i - 1]--;
					if (numbers[i - 1] <= 0) {
						flag = false;
						break Loop1;
					}
					cnt++;
				}
				if (i == 1)
					flag = true;
			}

			if (flag) {
				System.out.println("#" + t + " " + cnt);
			} else {
				System.out.println("#" + t + " " + -1);
			}

		}

	}
}