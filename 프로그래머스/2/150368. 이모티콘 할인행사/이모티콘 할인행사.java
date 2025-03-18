class Solution {
    
    int totalEmoticon;
    int numberOfUser;
    int[] discountArr;
    int[][] users;
    int[] emoticons;
    int maxPlus,maxSum;
    
    public void perm(int depth){
        if(depth==totalEmoticon){
            sol();
            return;
        }
        for(int i=10;i<=40;i+=10){
            discountArr[depth] = i;
            perm(depth+1);
        }
    }
    
    public void sol(){
        int whoBuyPlus = 0, sum = 0;
        int[] tempSum = new int[numberOfUser];
        for(int i=0;i<totalEmoticon;i++){
            //int cost = (100 - discountArr[i]) / 100 * emoticons[i];
            // int cost = (int) ((100 - discountArr[i]) / 100.0 * emoticons[i]);
            double cost1 = (100 - discountArr[i]) * emoticons[i] / 100.0;
            int cost = (int)cost1;
            
            for(int j=0;j<numberOfUser;j++){
                if(users[j][0]<= discountArr[i]){
                    tempSum[j] += cost;
                }
            }
        }
        for(int i=0;i<numberOfUser;i++){
            if(users[i][1] <= tempSum[i]){
                whoBuyPlus++;
            }else{
                sum+=tempSum[i];
            }
        }
        
        if(maxPlus<whoBuyPlus){
            maxPlus = whoBuyPlus;
            maxSum = sum;
        }else if(maxPlus==whoBuyPlus){
            maxSum = Math.max(maxSum,sum);
        }
    }
    
    public int[] solution(int[][] users, int[] emoticons) {
        this.users = users;
        this.emoticons=emoticons;
        numberOfUser=users.length;
        totalEmoticon = emoticons.length;
        discountArr = new int[totalEmoticon];
        
        perm(0);
        
        return new int[]{maxPlus, maxSum};
    }
}