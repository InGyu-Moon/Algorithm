import java.io.*;
import java.util.*;
class Solution {
    
    Map<Integer,Integer> in, out;
    
    int d,m,p;
    
    public int[] solution(int[][] edges) {
        int[] ans = new int[4];
        in = new HashMap<>();
        out = new HashMap<>();
        
        for(int[] edge : edges){
            out.put(edge[0], out.getOrDefault(edge[0], 0) + 1);
            in.put(edge[1], in.getOrDefault(edge[1], 0) + 1);
        }
        
        for(Integer node : out.keySet()){
            if(out.get(node) >= 2){
                if(!in.containsKey(node))
                    ans[0] = node; // 정점
                else
                    ans[3]++; // 8자
            }
        }
        
        for(Integer node : in.keySet()){
            if(!out.containsKey(node))
                ans[2]++; // 막대
        }
        
        ans[1] = out.get(ans[0]) - ans[2] - ans[3];
        
        return ans;
    }
    
}