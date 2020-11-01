package tt;

import java.io.BufferedReader;
import java.io.IOException;

public class Solution4 {
	public int INF = 100000000;
	public void solution() throws NumberFormatException, IOException {
		int answer = 0;
		
		BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
		
        int V = Integer.parseInt(br.readLine());
        int E = Integer.parseInt(br.readLine());
        int[][] dist = new int[V][V];
        
        //dist √ ±‚»≠
        for(int i = 0; i < V ; i++) {
        	for(int j = 0 ; j <V ; j++) {
        		if(i == j)
            		dist[i][j] = 0;
        		else
        			dist[i][j] = INF;
        	}
        }
        for(int i = 0 ; i<E; i++) {
        	String[] tmp = br.readLine().split(" ");
        	
        	int a = Integer.parseInt(tmp[0]);
        	int b = Integer.parseInt(tmp[1]);
        	
        	dist[a-1][b-1] = 1;
        	dist[b-1][a-1] = 1;
        }
        
        
        for(int i = 0; i<V ; i++) {
        	for(int j = 0 ; j <V; j++) {
        		if(j != i) {
        			for(int k = 0; k<V ; k++) {
            			if(!(k == i || k == j)) {
            				int d1 = dist[j][k];
                			int d2 = dist[j][i] + dist[i][k];
                			if(d1 > d2)
                				dist[j][k] = d2;
            			}
            			
            		}
        			
        		}
        		
        
        	}
        }
        for(int i = 1 ; i<V ; i++) {
        	if(dist[0][i] < INF)
        		answer++;
        }
        System.out.println(answer);
	}

}
