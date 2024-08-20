import java.io.*;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int N;
	static int ans;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(input.readLine());
		while (true) {
			if (N % 5 == 0) {
				ans += N / 5;
                break;
			}
			N -= 3;
			if (N < 0) {
				ans = -1;
				break;
			}
			ans++;

		}

		System.out.println(ans);
	}

}
