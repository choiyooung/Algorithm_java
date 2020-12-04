package tt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// LV3 외벽점검
public class Solution8 {
	public int[][] weaks;
	public ArrayList<ArrayList<Integer>> q;
	public ArrayList<Integer> tmp;
	boolean[] visited;
	public int solution(int n, int[] weak, int[] dist) {
        int answer = 0;
        Arrays.sort(dist);
        visited = new boolean[dist.length];
        q = new ArrayList<>();
        tmp = new ArrayList<>();
        if(weak.length == 1)
        	return 1;
        for(int i = 0; i< dist.length; i++){
        	 bfs(i+1,dist);
        	 for(int j = 0 ; j < weak.length; j++) {
        		 int[] w =w(j, weak, n);
        		 for(int k =0; k< q.size() ; k++){
        			 ArrayList<Integer> t = q.get(k);
        			 int start = 0;
        			 int idx = 0;
        			 int lastidx = t.size();
        			 int qq = t.get(idx);
        			 boolean check = true;
        			 for(int l = 0 ; l<weak.length ; l++) {
        				 int d = w[l] - w[start];
        				 if(d < qq) continue;
        				 else if (d == qq) {
        					 idx++;
        					 if(idx <lastidx) {
        						 qq = t.get(idx);
        						 start = l+1;

        					 }else {
        						 if(l == weak.length-1) return i+1;
        						 check = false;
        						 break;
        					 }
        				 }
        				 else if(d > qq){
        					 idx++;
        					 if(idx <lastidx) {
        						 qq = t.get(idx);
        						 start = l;
        					 }else {
        						 if(l == weak.length-1) return i+2;
        						 check = false;
        						 break;
        					 }
        					 
        				 }
        				 if(check) return i+1;
        				 
        			 }
        			 
        		 }
        	 }
        	 q.clear();
        	  
        }
        
        return -1;
    }
	public void bfs(int i,int[] dist) {
		if(i == 0) {
			ArrayList<Integer> tmp2 = new ArrayList<>();
			tmp2.addAll(tmp);
			q.add(tmp2);
			return;
		}
		for(int j = dist.length-1 ;j >= 0; j--) {
			if(!visited[j]) {
				tmp.add(dist[j]);
				visited[j] = true;
				bfs(i-1,dist);
				visited[j] = false;
				tmp.remove(tmp.size()-1);
			}
		}
	}
	public int[] w(int j, int[] weak,int  n) {
		int tmp[] = new int[weak.length];
		for(int i = 0; i<weak.length ; i++) {
			if(i+j <= weak.length -1 )
				tmp[i] = weak[i+j];
			else
				tmp[i] =  n + weak[(i+j)- weak.length];
		}
		return tmp;
	}
}
