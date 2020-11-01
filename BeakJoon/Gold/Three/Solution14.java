package tt;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


//1005 ACM craft
public class Solution14 {

	public HashMap<Integer,ArrayList<Integer>> graph;

	public void solution() throws IOException{
		StringTokenizer st;
		int T;
		int N;
		int K;
		int W;
		BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int i = 0 ; i< T ;i++) {
			st = new StringTokenizer(br.readLine());
			graph = new HashMap<>();
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			int[] dist = new int[N+1];
			int[] indegree = new int[N+1];
			
			st = new StringTokenizer(br.readLine());
			for(int j = 1 ; j <= N ; j++) {
				dist[j] = Integer.parseInt(st.nextToken());
			}
			
			for(int j = 0; j <K; j++) {
				st = new StringTokenizer(br.readLine());
				int X = Integer.parseInt(st.nextToken());
				int Y = Integer.parseInt(st.nextToken());
				if(graph.containsKey(X)) {
					graph.get(X).add(Y);

				}else {
					ArrayList<Integer> tmp3 = new ArrayList<>();
					tmp3.add(Y);
					graph.put(X, tmp3);
				}
				 indegree[Y]++;
			}
			W = Integer.parseInt(br.readLine());
			//시간 찾기
			dfs(W,dist,indegree,N);
			graph.clear();
		}
		
	}
	public void dfs(int start, int[] dist, int[] indegree,int N) {
		Queue<Integer> queue = new LinkedList<>();
		int[] result = new int[N+1];
		for(int i = 0 ; i<=N ; i++) {
			result[i] = dist[i];
			if(indegree[i] == 0)
				queue.offer(i);
		}
		
		while(queue.size() > 0) {
			int node = queue.poll();
			
			ArrayList<Integer> edges = null;
			if(!graph.containsKey(node)) continue;
			
			edges = graph.get(node);
			
			int edge;
			for( int i = 0 ; i < edges.size() ; i++) {
				edge = edges.get(i);
				result[edge] = Math.max(result[edge],result[node]+ dist[edge]);
				indegree[edge]--;
				if(indegree[edge] == 0)
					queue.offer(edge);
			}
		}
		System.out.println(result[start]);
	}



}
