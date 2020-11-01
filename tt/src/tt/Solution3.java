package tt;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Solution3 {
	private ArrayList<ArrayList<Integer>> array ;
	private final int Red = 1;
	private final int Blue = -1;
	private int[] colors;
	private boolean check;
	public void solution() throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
		
        int n = Integer.parseInt(br.readLine());
        
        for(int i = 0 ; i < n ; i++) {
        	String[] tmp = br.readLine().split(" ");
        	int V = Integer.parseInt(tmp[0]);
        	int E = Integer.parseInt(tmp[1]);
        	
        	array = new ArrayList<>();
        	colors = new int[V+1];
        	check = false;
        	
        	
         	for(int j= 0 ; j < V+1 ;j++) {
        		array.add(new ArrayList<>());
        	}
        	for(int j = 0; j <E ; j++) {
        		String[] tmp2 = br.readLine().split(" ");
            	int a = Integer.parseInt(tmp2[0]);
            	int b = Integer.parseInt(tmp2[1]);
            	
            	
        		array.get(a).add(b);
        		array.get(b).add(a);
        	}
        	
        	for(int j= 1 ; j <V+1 ; j++) {
        		if(check)
        			break;
        		
        		if(colors[j] == 0) {
        			dfs(j,Red);
        		}
        			
        	}
        	System.out.println(check ? "NO" : "YES");
        		
        }
		return;
	}
	public void dfs(int startV, int color) {
		colors[startV] = color;
		
		for(int adjV : array.get(startV)) {
			if(colors[adjV] == color) {
				check = true;
				return;
			}
			if(colors[adjV] == 0) {
				dfs(adjV,-color);
			}
		}
	}
}
