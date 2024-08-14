
import java.io.*;
import java.util.Stack;

public class Solution {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();

	static Stack<Character> stk = new Stack<>();
	static boolean flag;
	static int testSize;

	public static void main(String[] args) throws Exception {
		for (int t = 1; t <= 10; t++) {
			testSize = Integer.parseInt(input.readLine());
			String str = input.readLine();

			for (int i = 0; i < testSize; i++) {
				char temp = str.charAt(i);
				if (temp == '(' || temp == '{' || temp == '<' || temp == '[') {
					stk.push(temp);
				} else {
					if (stk.peek() == '(' && temp == ')')
						stk.pop();
					else if (stk.peek() == '[' && temp == ']')
						stk.pop();
					else if (stk.peek() == '{' && temp == '}')
						stk.pop();
					else if (stk.peek() == '<' && temp == '>')
						stk.pop();
					else {
						flag = true;
						break;
					}

				}
			}
			output.append("#").append(t).append(" ");
			if (flag) {
				output.append("0\n");
			} else if (!flag && stk.empty()) {
				output.append("1\n");
			} else {
				output.append("0\n");
			}
			stk.clear();
			flag = false;
		}
		System.out.println(output);
	}

}
