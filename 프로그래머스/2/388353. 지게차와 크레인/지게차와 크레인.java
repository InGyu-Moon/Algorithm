import java.io.*;
import java.util.*;
class Solution {
    int[] dy={-1,0,1,0};
    int[] dx={0,1,0,-1};
    int n,m;
    char[][] arr;
    boolean[][] block;
    Deque<int[]> que = new ArrayDeque<>();
    
    public void init(String[] storage){
        n = storage.length;
        m = storage[0].length();
        arr = new char[n+2][m+2];
        block = new boolean[n+2][m+2];
        
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                arr[i][j] = storage[i-1].charAt(j-1);
                block[i][j] = true;
            }
        }
    }
    
    public void sol(String[] requests){
        for(String str: requests){
            if(str.length()==2){
                // 전체 제거
                for(int i=1;i<=n;i++){
                    for(int j=1;j<=m;j++){
                        if(arr[i][j]==str.charAt(0))
                            block[i][j]=false;
                    }
                }
            }else{
                char word = str.charAt(0);
                List<int[]> temp = new ArrayList<>();
                que.add(new int[] {0,0});
                boolean[][] visited = new boolean[n+2][m+2];
                visited[0][0]=true;
                while(!que.isEmpty()){
                    int[] top = que.poll();
                    int y = top[0];
                    int x = top[1];
                    for(int i=0;i<4;i++){
                        int ny = y + dy[i];
                        int nx = x + dx[i];
                        if(ny < 0 || nx < 0 || ny > n + 1 || nx > m + 1 )
                            continue;
                        if(visited[ny][nx])
                            continue;
                        if(block[ny][nx] && arr[ny][nx] == word){
                            temp.add(new int[] {ny,nx});
                        } else if(!block[ny][nx]){
                            visited[ny][nx]=true;
                            que.add(new int[]{ny,nx});
                        }
                    }
                }
                for(int[] pos:temp){
                    int y = pos[0];
                    int x = pos[1];
                    block[y][x]=false;
                }
            }
        }
        
    }
    
    public int solution(String[] storage, String[] requests) {
        init(storage);
        
        sol(requests);
        int ans=0;
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(block[i][j]==true)
                    ans++;
            }
        }

        return ans;
    }
}