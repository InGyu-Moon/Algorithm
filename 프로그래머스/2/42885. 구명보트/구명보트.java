import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int ans=0;
        Arrays.sort(people);
        int start = 0, end = people.length-1;
        while(start<=end){
            int small = people[start];
            int big = people[end];
            if(small+big <=limit)
                start++;
            end--;
            ans++;
        }
        return ans;
    }
}