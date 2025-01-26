import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static String str, bomb;

	static Stack<Character> stk;

	public static void check() {
		boolean flag = true;
		for (int i = bomb.length() - 1; i >= 0; i--) {
			char top = stk.get(stk.size() - 1 - i);
			if (top != bomb.charAt(bomb.length() - 1 - i)) {
				flag = false;
				break;
			}
		}
		if (flag) {
			for (int i = 0; i < bomb.length(); i++) {
				stk.pop();
			}
		}
	}

	public static void sol() {
		for (int i = 0; i < str.length(); i++) {
			char currChar = str.charAt(i);
			stk.add(currChar);

			if (stk.size() >= bomb.length()) {
				check();
			}
		}

		if (stk.size() == 0) {
			output.append("FRULA");
		} else {
			List<Character> temp = new ArrayList<>(stk);
			for (int i = 0; i < temp.size(); i++) {
				output.append(temp.get(i));
			}
		}
	}

	public static void init() throws Exception {
		stk = new Stack<>();
		str = input.readLine();
		bomb = input.readLine();
	}

	public static void main(String[] args) throws Exception {
		init();
		sol();
		System.out.println(output);
	}
}