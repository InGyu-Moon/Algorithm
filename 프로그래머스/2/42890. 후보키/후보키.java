import java.io.*;
import java.util.*;

class Solution {
    String[][] relation;
    int ans = 0, col;
    int[] arr;
    List<Set<Integer>> candidateKeys = new ArrayList<>();
    
    public int solution(String[][] relation) {
        this.relation = relation;
        col = relation[0].length;
        for(int i=1;i<=col;i++){
            arr = new int[i];
            comb(i,0,0);
        }
        return ans;
    }
    public void comb(int size, int curr, int start){
        // 종료
        if(curr == size){
            Set<Integer> keySet = new HashSet<>();
            for (int val : arr) keySet.add(val);

            if (isUnique(keySet) && isMinimal(keySet)) {
                candidateKeys.add(keySet);
                ans++;
            }
            return;
        }
        // 진행
        for(int i=start;i<col;i++){
            arr[curr]=i;
            comb(size,curr+1,i+1);
        }
    }
    
    public boolean isUnique(Set<Integer> keySet) {
        Set<String> seen = new HashSet<>();

        for (String[] row : relation) {
            StringBuilder sb = new StringBuilder();
            for (int idx : keySet) {
                sb.append(row[idx]).append(",");
            }
            if (!seen.add(sb.toString())) return false; // 중복 있음
        }

        return true;
    }

    // 최소성 검사
    public boolean isMinimal(Set<Integer> keySet) {
        for (Set<Integer> existing : candidateKeys) {
            if (keySet.containsAll(existing)) return false;
        }
        return true;
    }
    
}