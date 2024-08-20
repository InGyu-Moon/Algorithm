
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;
	static int n, l;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		l = Integer.parseInt(token.nextToken());

		arr = new int[n];

		token = new StringTokenizer(input.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(token.nextToken());
		}
		
		Arrays.sort(arr);
		
		for (int i = 0; i < n; i++) {
			if (arr[i] > l)
				break;
			l++;
		}
		System.out.println(l);
	}

}
