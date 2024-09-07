import java.util.*;
import java.io.*;

public class Solution {
	static int N,max;
	static char[] ca;
	
	static void swap(int i,int j){
		char T=ca[i]; ca[i]=ca[j]; ca[j]=T;
	}
	static void dfs(int cnt,int start){
		if(cnt==N){
			max=Math.max(max,Integer.parseInt(String.valueOf(ca)));
			return;
		}
		for(int i=start; i<ca.length; i++){
			for(int j=i+1; j<ca.length; j++){
				swap(i,j);
				dfs(cnt+1,i);
				swap(i,j);
			}
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=null;
        StringBuilder sb=new StringBuilder();
        int T=Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++){
        	st=new StringTokenizer(br.readLine()," ");
        	ca=st.nextToken().toCharArray();
        	N=Integer.parseInt(st.nextToken());
			max=0;
			dfs(0,0);
            sb.append("#").append(tc).append(" ").append(max).append("\n");
        }
        System.out.print(sb.toString());
        br.close();
    }
}