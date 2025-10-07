class Solution {
    int[] diffs;
    int[] times;
    long limit;
    int start = 1, end = 0, mid;
    
    public int solution(int[] diffs, int[] times, long limit) {
        this.diffs = diffs;
        this.times = times;
        this.limit = limit;
        
        // diffs 최대값 구하기
        for(int i=0;i<diffs.length;i++){
            end = Math.max(end,diffs[i]);
        }
        
        // 이진탐색
        while(start <= end) {
            mid = (start + end) / 2;
            long result = check(mid);
            
            if(result <= limit){
                end = mid - 1;
            }
            else{
                start = mid + 1;
            }
        }
        
        return start;
    }
    
    public long check(int level) {
        int n = diffs.length;
        long sum = 0;
        // i = 0
        if(diffs[0] <= level)
            sum += times[0];
        else
            sum += times[0] * (diffs[0]-level + 1);
        // 그외
        for(int i=1;i<n;i++){
            int diff = diffs[i];
            int time_curr = times[i];
            int time_prev = times[i-1];
            if(diff <= level){
                sum += time_curr;
            }
            else{
                sum += time_curr * (diff-level + 1) + time_prev * (diff - level);
            }
            // (선택) 조기 종료: 이미 limit 초과면 더 볼 필요 없음
            if (sum > limit) break;
        }
        
        return sum;
    }
}








