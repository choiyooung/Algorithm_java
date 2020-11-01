package tt;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Solution13 {
	public int N;
	public int M;
	public int K;
	public long[] array;
	public long[] segmentTree;
	//public Queue<Long> queue;
	public void solution() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
		String[] tmp = br.readLine().split(" ");
		N = Integer.parseInt(tmp[0]);
		M = Integer.parseInt(tmp[1]);
		K = Integer.parseInt(tmp[2]);
		//queue = new LinkedList<>();
		array = new long[N];
		segmentTree = new long[N*4];
		for(int i = 0 ; i<N; i++) {
			array[i] = Long.parseLong(br.readLine());
		}
		init(0,N-1,1);
		
		for(int i = 0 ; i <M+K ;i++) {
			String[] tmp2 = br.readLine().split(" ");
			int a = Integer.parseInt(tmp2[0]);
			int b = Integer.parseInt(tmp2[1]);
			int c = Integer.parseInt(tmp2[2]);
			if(a == 1) {
				long dif = c - array[b-1];
				array[b-1] = c;
				update(0,N-1,1,b-1,dif);
			}else {
		//		queue.add(sum(0,N-1,1,b-1,c-1));
			}
		}
	//	while(queue.size()>0) {
		//	System.out.println(queue.poll());
	//	}
	}
	
	public long init(int start, int end, int node) {
		if(start == end) return segmentTree[node] = array[start];
		int	mid = (end + start)/2 ;
		return segmentTree[node] = init(start, mid, node*2) + init(mid+1, end, node*2+1);
	}
	
	public long sum(int start, int end, int node, int left , int right) {
		if(left > end || right < start) return 0;
		if(left <= start && right >= end ) return segmentTree[node];
		
		int mid = (start + end)/2;
		
		return sum(start, mid, node*2, left,right) + sum(mid+1, end ,node*2 +1 , left, right);
	}
	public void update(int start,int end, int node, int index, long dif) {
		if(index < start || index > end) return;
		segmentTree[node] += dif;
		if(start == end) return;
		int mid = (start + end)/2;
		update(start,mid, node*2, index, dif);
		update(mid+1,end, node*2+1, index, dif);		
	}

}
