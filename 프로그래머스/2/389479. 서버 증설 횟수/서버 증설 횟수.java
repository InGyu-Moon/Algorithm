class Solution {
    public int solution(int[] players, int m, int k) {
        int ans=0;
        int[] server = new int[24+k];
        for(int i=0;i<24;i++){
            int diff = players[i] - m * (server[i] + 1);
            if(diff >= 0){
                int add = diff / m + 1;
                for(int j=0;j<k;j++){
                    server[i+j] += add;
                }
                ans += add;
            }
        }
        return ans;
    }
}