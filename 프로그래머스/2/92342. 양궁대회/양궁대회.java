class Solution {
    
    int n;
    int[] input = new int[11];
    int[] selected = new int[11];
    
    int[] answer = new int[11];
    
    boolean flag = false;
    int max = 0;
    boolean flag2 = false;
    
    public void compare(){
        int a = 0, r = 0;
        for(int i=0;i<11;i++){
            int first = input[i];
            int second = selected[i];
            if(first== 0 && second ==0)
                continue;
            if(first >= second)
                a += (10-i);
            else
                r += (10-i);
        }
        
        if(max<r-a){
            flag = true;
            max = r-a;
            for(int i=0;i<11;i++){
                answer[i] = selected[i];
            }
        }else if(max==r-a){
            flag2 = false;
            for(int i=10;i>=0;i--){
                // if(selected[i]==answer[i])
                //     continue;
                // if(selected[i]>answer[i]){
                //     flag2 = true;
                //     break;
                // }
                if (selected[i] > answer[i]) {
                    answer = selected.clone();
                    break;
                } else if (selected[i] < answer[i]) {
                    break;
                }
            }
            if(flag2){
                for(int i=0;i<11;i++){
                    answer[i] = selected[i];
                }
            }
        }
    }
    
    public void perm(int depth, int sum){
        if(sum > n)
            return;
        if(depth==11){
            compare();
            return;
        }
        
        for(int i=0;i<=n;i++){
            selected[depth] = i;
            perm(depth+1, sum+i);
        }
        
    }
    
    public int[] solution(int n, int[] info) {
        this.n=n;
        for(int i=0;i<11;i++){
            input[i]=info[i];
        }
        
        perm(0,0);
        
        if(flag){
            return answer;
        }
        else{
            return new int[] {-1};
        }
    }
}