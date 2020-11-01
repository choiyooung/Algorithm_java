package tt;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution12 {
	public int[][] board;
	public int[] dx = {0,0,1,-1};
	public int[] dy = {1,-1,0,0};
	public int total = 0;
	public int N;
	public int minVal = 999999999;
	public int[] tmp;
	public void solution() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new int[N+1][N+1];
		tmp = new int[4];
		for(int i = 1 ; i<=N ;i++) {
			String[] tmp = br.readLine().split(" ");
			for(int j = 1 ; j <= N ; j++) {
				int val = Integer.parseInt(tmp[j-1]);
				board[i][j] = val; 
				total+= val;
			}
		}
		for(int x = 1; x < N ; x++) {
			for(int y = 1 ; y< N ; y++) {
				for(int d1 = 1; d1< N ; d1++) {
					for(int d2 = 1; d2< N ;d2++) {
						if(x+d1+d2<=N && y-d1>=1 && y+d2<=N)
							divide(x,y,d1,d2);
					}
				}
			}
			
		}
		System.out.println(minVal);
		
	}
	private void divide(int x, int y, int d1, int d2) {
		// TODO Auto-generated method stub
		int[] area = new int[5];
		//1번 선거구
		area[0] =bfs(1,1,1,1, x+ d1-1, y,x+y-2);
		area[1] =bfs(1,N,1,y+1,x+d2,N,N-y+x-1);
		area[2] =bfs(N,1,x+d1,1,N,y-d1+d2-1,N+y-x-2*d1-1);
		area[3] =bfs(N,N,x+d2+1,y-d1+d2,N,N,2*N-x-y-2*d2);
		area[4] = total - (area[0] + area[1] + area[2] + area[3]);
		Arrays.sort(area);
		int max_M_min = Math.abs(area[0]-area[4]);
		if(minVal>max_M_min ) {
			minVal = max_M_min;
			tmp[0] = x;
			tmp[1] = y;
			tmp[2] = d1;
			tmp[3] = d2;
		}
			
		
	}
	private int bfs(int x, int y, int sx, int sy, int ex, int ey, int condition) {
		// TODO Auto-generated method stub
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N+1][N+1];
		int[] init = {x,y,0};
		queue.offer(init);
		visited[x][y] = true;
		int number = board[x][y];
		while(queue.size()>0) {
			int[] item = queue.poll();
			int tx = item[0];
			int ty = item[1];
			int tcnt = item[2];
			for(int i = 0 ; i<4 ;i++) {
				int nx = tx + dx[i];
				int ny = ty + dy[i];
				int ncnt = tcnt+1;
				
				if(nx < sx || ny < sy || nx>ex || ny > ey ) continue;
				if(visited[nx][ny]) continue;
				if(ncnt >= condition ) continue;
				
				visited[nx][ny] =true;
				int[] newItem = {nx,ny,ncnt};
				queue.offer(newItem);
				number+= board[nx][ny];
			}
		}
		return number;
	}
	

	

}
