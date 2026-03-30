import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int g;
	static int ans = 0;

	public static void sol() {
		int s = 1;
		int e = 1;
		while (true) {
			int num = s * s - e * e;
			if (num == g) {
				output.append(s).append("\n");
				ans++;
				s++;
			}
			if (num > g && s - e == 1)
				break;
			
			
			if (num > g) {
				e++;
			} else if (num < g) {
				s++;
			}

			
		}
		if (ans == 0)
			output.append("-1");
	}

	public static void init() throws Exception {
		g = Integer.parseInt(input.readLine());
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
		System.out.println(output);
	}
}
