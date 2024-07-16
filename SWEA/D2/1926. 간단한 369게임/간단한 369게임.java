import java.util.Scanner;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder output = new StringBuilder();

		int num = Integer.parseInt(bf.readLine());
		for (int i = 1; i <= num; i++) {
			String str = i + "";
			int cnt = 0;
			int len = str.length();
			for (int j = 0; j < len; j++) {
				int temp = Integer.parseInt(str.charAt(j) + "");
				if (temp == 3 || temp == 6 || temp == 9) {
					cnt++;
				}
			}
			if (cnt == 0) {
				output.append(i + " ");
			} else {
				String dash = "";
				for (int j = 0; j < cnt; j++) {
					dash += "-";
				}
				output.append(dash + " ");
			}
		}
		System.out.println(output);
	}
}