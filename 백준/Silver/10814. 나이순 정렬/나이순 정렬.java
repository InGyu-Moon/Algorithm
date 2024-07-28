import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();
        StringTokenizer token;

        List<Member> list = new ArrayList<>();

        int n = Integer.parseInt(input.readLine());
        for (int i = 0; i < n; i++) {
            String line = input.readLine();
            token = new StringTokenizer(line);
            list.add(new Member(i,Integer.parseInt(token.nextToken()),token.nextToken()));
        }

        Collections.sort(list);

        for(Member m : list){
            output.append(m.age).append(" ").append(m.name).append("\n");
        }

        System.out.println(output);

    }
    static class Member implements Comparable<Member>{
        int order;
        int age;
        String name;
        public Member(int order, int age, String name) {
            this.order = order;
            this.age = age;
            this.name = name;
        }

        @Override
        public int compareTo(Member o) {
            if(o.age == this.age){
                return Integer.compare(this.order,o.order);
            }else{
                return Integer.compare(this.age,o.age);
            }
        }
    }



}
