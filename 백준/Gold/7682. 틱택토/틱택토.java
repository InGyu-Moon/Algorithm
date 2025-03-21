import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;

	static char[][] arr;

	public static boolean bingo(char c) {
		// 가로
		for (int i = 0; i < 3; i++) {
			if (arr[i][0] == c && arr[i][1] == c && arr[i][2] == c)
				return true;
		}
		// 세로
		for (int i = 0; i < 3; i++) {
			if (arr[0][i] == c && arr[1][i] == c && arr[2][i] == c)
				return true;
		}
		// 대각선
		if (arr[0][0] == c && arr[1][1] == c && arr[2][2] == c)
			return true;
		if (arr[0][2] == c && arr[1][1] == c && arr[2][0] == c)
			return true;

		return false;
	}

	public static void sol(String str) {
		arr = new char[3][3];
		int idx = 0, xCnt = 0, oCnt = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				arr[i][j] = str.charAt(idx++);
				if (arr[i][j] == 'X') {
					xCnt++;
				} else if (arr[i][j] == 'O') {
					oCnt++;
				}
			}
		}
		if (xCnt == oCnt + 1) { // x가 이김
			if (xCnt + oCnt == 9 && !bingo('O')) {
				output.append("valid").append("\n");
			} else if (!bingo('O') && bingo('X')) {
				output.append("valid").append("\n");
			} else {
				output.append("invalid").append("\n");
			}
		} else if (xCnt == oCnt) { // o가 이김
			if (!bingo('X') && bingo('O')) {
				output.append("valid").append("\n");
			} else {
				output.append("invalid").append("\n");
			}
		} else {
			output.append("invalid").append("\n");
		}
	}

	public static void main(String[] args) throws Exception {
		while (true) {
			String str = input.readLine();
			if (str.equals("end"))
				break;
			sol(str);
		}
		System.out.println(output);
	}

}
