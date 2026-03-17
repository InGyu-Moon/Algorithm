import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static int n, m;
	static int[] arr;
	static Set<Integer> set = new HashSet<>();

	public static void init() throws Exception {
		n = Integer.parseInt(input.readLine());
		token = new StringTokenizer(input.readLine());
		for (int i = 0; i < n; i++) {
			set.add(Integer.parseInt(token.nextToken()));
		}
		m = Integer.parseInt(input.readLine());
		token = new StringTokenizer(input.readLine());
		for(int i=0;i<m;i++) {
			if(set.contains(Integer.parseInt(token.nextToken()))) {
				output.append("1\n");
			}else {
				output.append("0\n");
			}
		}
	}

	public static void main(String[] args) throws Exception {
		init();
		System.out.println(output);
	}
}
