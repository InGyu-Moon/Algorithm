import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;


public class Solution {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer tokens;

    static int T;
    static int N;
    static int[] trees;
    static int days;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(input.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(input.readLine());
            tokens = new StringTokenizer(input.readLine());
            trees = new int[N];
            int max = Integer.MIN_VALUE;
            for (int n = 0; n < N; n++) {
                trees[n] = Integer.parseInt(tokens.nextToken());
                max = Math.max(max, trees[n]);
            }

            days = 0;

            int ones = 0;
            int twos = 0;
            int total = 0;
            // 2씩 늘어나야 하는 개수와 1씩 늘어나야 하는 경우를 체크해주자.
            for (int n = 0; n < N; n++) {
                int remain = max - trees[n];
                total += remain;    // 
                twos += remain / 2;
                ones += remain % 2;
            }

            // 1cm, 2cm의 순으로 물을 준다.
            // 개수가 같을 때는 홀수 주고 짝수 줘서 최대한 이어서 주면 됨 - 둘이 짝으로 진행됨
            if (ones == twos) {
                days = ones * 2;
            }
            // 1은 2로 대체 불가 따라서 1이 많은 것은 two와 함께 페어로 하나씩 줄이고 마지막에 홀수 하나가 필요하므로 짝수는 빼줌
            else if (ones > twos) {
                days = ones * 2 - 1;
            }
            // two가 많은 경우는 최소 개수의 two를 one으로 넘겨주면 되는데
            else {
                if (ones > 0) {
                    days = ones * 2; // ones 만큼은 모두 소진 (one + two 페어로 지워짐)
                    twos -= ones;    //
                    total = twos * 2; // 남은 날짜는 twos의 2배
                }
                //System.out.println("ones 처리 완료: "+ones + " :" + twos +" : "+total +" : "+days);
                // 이제 짝수로만 이뤄짐 - 상황에 따라 홀수로 바꿀 수 있음
                // 높이가 3이상이면 
                if (total >= 3) {
                    int nday = total / 3;
                    days += nday * 2;
                    total -= nday * 3;
                }
                //System.out.println("3일씩 처리 완료 "+ones + " :" + twos +" : "+total +" : "+days);
                // 이제 남은건 2 or 1 or 0
                if (total == 1) {
                    days += 1;
                } else if (total == 2) {
                    days += 2;
                }
            }
            output.append("#").append(t).append(" ").append(days).append("\n");
        }
        System.out.println(output);
    }

}