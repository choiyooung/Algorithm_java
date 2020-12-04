package tt;
import java.io.*;
import java.util.*;

class Main {
	public static int N; // 2^N개의 격자
	public static int Q;  // 시전 횟수
	public static int[][] map;
	public static int mapSize;
	public static int[] dx = {1,-1, 0, 0};
	public static int[] dy = {0, 0, 1,-1};
	public static int totalIce = 0;
	public static boolean[][] visitied;
	public static void main(String[] args) throws IOException{
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
	private static void printMap() {
		// TODO Auto-generated method stub
		for(int i = 0 ; i < mapSize ; i++) {
			for(int j = 0 ; j< mapSize ; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println(" ");
		}
		System.out.println("=======================================");
		
	}
	private static int findLargeIce() {
		int largeIce = 0;
		for(int i = 0 ; i< mapSize ; i++) {
			for(int j = 0 ; j < mapSize ; j++) {
				if(map[i][j] <= 0 || visitied[i][j] ) continue;
				largeIce = Math.max(largeIce, bfs2(i,j));
			}
		}
		return largeIce;
	}
	private static int bfs2(int i, int j) {
		// TODO Auto-generated method stub
		Queue<int[]> q = new LinkedList<>();
		int[] init = {i,j};
		int cnt = 1;
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
	private static void fireStorm(int L) {
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
	}
	private static void bfs() {
		// TODO Auto-generated method stub
		int[][] tmpMap = new int[mapSize][mapSize];

		for (int x = 0; x < mapSize; x++) {
	        for (int y = 0; y < mapSize; y++) {
	            int cnt = 0;
	            // 인접한 방향 중에서 얼음이 있는지 확인
	            for (int i = 0; i < 4; i++) {
	                int nx = x + dx[i];
	                int ny = y + dy[i];
	 
	                if (nx < 0 || ny < 0 || nx >= mapSize || ny >= mapSize) continue;
	                if (map[nx][ny] <= 0) continue;
	                cnt++;
	            }
	            // 3곳 이상 존재하는 경우
	            if (cnt < 3) tmpMap[x][y] = -1;
	        }
	    }
		for(int i = 0 ; i < mapSize ; i++) {
			for(int j = 0; j<mapSize ; j++) {
				if(map[i][j] >= 1 && tmpMap[i][j] == -1) {
					map[i][j] += tmpMap[i][j];
					totalIce--;
				}
					
			}
		}
	
		
	}
	private static void mapRotation(int x, int y, int Lsize) {
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
