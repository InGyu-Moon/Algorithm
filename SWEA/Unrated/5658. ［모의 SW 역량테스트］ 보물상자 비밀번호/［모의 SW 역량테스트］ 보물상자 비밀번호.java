import java.io.*;
import java.util.*;
 
class Solution {
 
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer token;
 
    static int n, k;
    static String str;
    static int ans;
 
    public static void sol() {
        int size = n / 4; // 한변의 길이
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < 4; j++) {
                int idx = size * j + i;
                char[] cArr = new char[size];
                for (int k = 0; k < size; k++) {
                    cArr[k] = str.charAt(idx + k);
                }
                String numStr = String.valueOf(cArr);
                int num = Integer.parseInt(numStr, 16);
                list.add(num);
            }
        }
 
        Collections.sort(list, Collections.reverseOrder());
         
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).equals(list.get(i + 1))) {
                list.remove(i);
                i--;
            }
        }
        ans = list.get(k-1); // 실수
    }
 
    public static void init() throws Exception {
        token = new StringTokenizer(input.readLine());
        n = Integer.parseInt(token.nextToken());
        k = Integer.parseInt(token.nextToken());
        str = input.readLine();
        str += str;
    }
 
    public static void main(String args[]) throws Exception {
        int TC = Integer.parseInt(input.readLine());
        for (int t = 1; t <= TC; t++) {
            init();
            sol();
            output.append("#").append(t).append(" ").append(ans).append("\n");
        }
        System.out.println(output);
    }
}