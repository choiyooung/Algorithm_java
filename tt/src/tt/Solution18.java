package tt;

import java.io.*;
import java.util.*;

public class Solution18 {
	public int N; // 2^N개의 격자
	public int Q; // 시전 횟수
	public int[][] map;
	public int mapSize;
	public int[] dx = {1,-1, 0, 0};
	public int[] dy = {0, 0, 1,-1};
	public int totalIce = 0;
	public boolean[][] visitied;
	public void solution() throws IOException{
		BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		mapSize = (int)Math.pow(2, N);
		map = new int[mapSize][mapSize];
		visitied =  new boolean[mapSize][mapSize];
		for(int i = 0; i < mapSize ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j <mapSize ; j++) {
				int ice = Integer.parseInt(st.nextToken());
				map[i][j] = ice;
				totalIce += ice;
			}
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i< Q; i++) {
			int L = Integer.parseInt(st.nextToken());
			fireStorm(L);
		}
		System.out.println(totalIce);
		System.out.println(findLargeIce());
	}
	private int findLargeIce() {
		int largeIce = 0;
		for(int i = 0 ; i< mapSize ; i++) {
			for(int j = 0 ; j < mapSize ; j++) {
				if(map[i][j] < 0 || visitied[i][j] ) continue;
				largeIce = Math.max(largeIce, bfs2(i,j));
			}
		}
		return largeIce;
	}
	private int bfs2(int i, int j) {
		// TODO Auto-generated method stub
		Queue<int[]> q = new LinkedList<>();
		int[] init = {i,j};
		int cnt = 0;
		q.offer(init);
		visitied[i][j] = true;
		while(q.size()>0) {
			int[] n = q.poll();
			int x = n[0];
			int y = n[1];
			for(int k = 0 ; k < 4 ; k++) {
				int nx = x +dx[k];
				int ny = y +dy[k];
				//범위 벗어남
				if(nx < 0 || ny < 0 || nx >= mapSize || ny >= mapSize) continue;
				if(map[nx][ny] <= 0) continue;
				if(visitied[nx][ny]) continue;
				
				int[] a = {nx,ny};
				q.offer(a);
				visitied[nx][ny] = true;
				cnt++;		
			}
		}
		
		return cnt;
	}
	private void fireStorm(int L) {
		// TODO Auto-generated method stub
		int Lsize = (int)Math.pow(2,L);
		if(Lsize !=1) {
			for(int i = 0; i< mapSize/Lsize; i++) {
				for(int j = 0 ; j< mapSize/Lsize ;j++) {
					int x = Lsize*i;
					int y = Lsize*j;
					mapRotation(x,y,Lsize);
				}
			}
		}
		bfs();
		//printMap();
	}
	private void bfs() {
		// TODO Auto-generated method stub
		boolean[][] visited =  new boolean[mapSize][mapSize];
		int[][] tmpMap = new int[mapSize][mapSize];
		Queue<int[]> q = new LinkedList<>();
		int[] init = {0,0};
		q.offer(init);
		while(q.size() > 0) {
			int[] next = q.poll();
			int bx = next[0];
			int by = next[1];
			int cnt = 0;
			if(visited[bx][by]) continue;
			visited[bx][by] = true;
			for(int i = 0 ; i < 4 ; i++) {
				int nx = bx +dx[i];
				int ny = by +dy[i];
				//범위 벗어남
				if(nx < 0 || ny < 0 || nx >= mapSize || ny >= mapSize) continue;

				// 범위 안이면, q에넣음
				int[] a = {nx,ny};
				q.offer(a);
				
				//3개이상 체크
				if(map[nx][ny] > 0) cnt ++;
			}
			if(cnt<3) {
				if(map[bx][by] >0) {
					tmpMap[bx][by] = -1;
					totalIce--;
				}
			}
				

		}
		for(int i = 0 ; i < mapSize ; i++) {
			for(int j=0; j<mapSize ; j++) {
				map[i][j] += tmpMap[i][j];
			}
		}
	
		
	}
	private void mapRotation(int x, int y, int Lsize) {
		// TODO Auto-generated method stub
		int[][] tmpMap = new int [Lsize][Lsize];
		for(int i = 0 ; i < Lsize; i++) {
			for(int j = 0 ; j < Lsize ; j++) {
				tmpMap[j][Lsize-i-1] = map[x+i][y+j];
			}
		}
		for(int i = 0 ; i < Lsize; i++) {
			for(int j = 0 ; j < Lsize ; j++) {
				map[x+i][y+j] = tmpMap[i][j];
			}
		}		
	}

}
