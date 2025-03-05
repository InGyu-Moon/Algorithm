class Solution {
    
    int result = 0;
    int[] selected,globalAns;
    int[][] globalQ;
    int nn,m;
    
    public boolean compare(int[] code, int[] selected, int idx){
        int i=0,j=0;
        int num=0;
        while( i<5 && j<5){
            int a = code[i];
            int b = selected[j];
            if(a==b){
                i++;
                j++;
                num++;
            }else if(a > b){
                j++;
            }else if(a < b){
                i++;
            }
        }
        if(globalAns[idx]==num){
            return true;
        }else{
            return false;
        }
    }
    
    public void check(){
        boolean flag = true;
        for(int i=0;i<m;i++){
            int[] code = globalQ[i];
            if(!compare(code,selected,i)){
                flag = false;
                break;
            }
        }
        if(flag){
            result++;
        }
    }
    
    public void dfs(int curr, int cnt){
        if(cnt==5){
            check();
            return;
        }
        for(int i=curr;i<=nn;i++){
            selected[cnt] = i;
            dfs(i+1,cnt+1);
        }
    }
    
    public int solution(int n, int[][] q, int[] ans) {
        nn=n;
        m = q.length;
        selected = new int[n];
        globalQ=q;
        globalAns=ans;
        
        dfs(1,0);
        
        
        
        
        return result;
    }
}