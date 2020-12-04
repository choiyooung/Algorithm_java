package Beakjoon;

import java.io.BufferedReader;
import java.io.IOException;

public class Solution1 {
	//미세먼지 안녕!
	public int[][][] map;
	public int[] robot;
	public int[] dx = {0,0,1,-1};
	public int[] dy = {1,-1,0,0};
	public int total = 0;
	public void solution() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
		String[] tmp = br.readLine().split(" ");
		
        int R = Integer.parseInt(tmp[0]);
        int C = Integer.parseInt(tmp[1]);
        int T = Integer.parseInt(tmp[2]);
        map = new int[R][C][2];
        robot = new int[2];
        int robotIdx = 0;
        for(int i = 0 ; i < R ; i++) {
        	String[] items = br.readLine().split(" ");
        	if(items[0].equals("-1")) {
    			robot[robotIdx] = i;
    			robotIdx++;
    		}
   
        	for(int j = 0 ; j <C ; j++) {
        		int item =Integer.parseInt(items[j]);
        		map[i][j][0] = item;
        		total += item;
        	}
        }
        total += 2;
        for(int i = 0 ; i<T;i++) {
        	dp(R,C);
        }
        System.out.println(total+"");
	}
	public void dp(int R, int C) {
//		if(d == 0) d = 1;
//		else d = 0;
		for(int x = 0 ; x< R ; x++) {
			for(int y =0 ; y<C ;y++) {
				int Axy = map[x][y][0]/5; 
				for(int i = 0 ; i< 4 ; i++) {
					int nx = x+dx[i];
					int ny = y+dy[i];
					if(nx <0 || ny <0 || nx>= R || ny >= C) continue;
					if(ny == 0 && (nx == robot[0] ||nx == robot[1] )) continue;
					map[x][y][0] -= Axy;
					map[nx][ny][1] += Axy;				
				}
				
				int nx2 = x-1;
				int ny2 = y;
				if(nx2 <0 || ny2 <0 || nx2>= R || ny2 >= C) continue;
				map[nx2][ny2][0] += map[nx2][ny2][1];
				map[nx2][ny2][1] = 0;
			}
		}
		
		for(int y = 0 ; y <C ;y ++) {
			map[R-1][y][0] += map[R-1][y][1];
			map[R-1][y][1] = 0;
		}
		//이동
		total -= map[robot[0]-1][0][0];
		total -= map[robot[1]+1][0][0];
		
		for(int x = robot[0]-1;  x>0 ; x--) {
			map[x][0][0] = map[x-1][0][0];
		}
		for(int x = robot[1]+1;  x<R-1 ; x++) {
			map[x][0][0] = map[x+1][0][0];
		}
		
		
		for(int y = 0 ; y<C-1 ;y++) {
			map[0][y][0] = map[0][y+1][0];
			map[R-1][y][0] = map[R-1][y+1][0];

		}
		
		for(int x = 0 ; x < robot[0] ;x++) {
			map[x][C-1][0] = map[x+1][C-1][0];
		}
		for(int x = R-1 ; x > robot[1] ;x--) {
			map[x][C-1][0] = map[x-1][C-1][0];
		}
		
		for(int y = C-1 ; y >0 ; y--) {
			map[robot[0]][y][0] = map[robot[0]][y-1][0];
			map[robot[1]][y][0] = map[robot[1]][y-1][0];
		}
		map[robot[0]][1][0] = 0;
		map[robot[1]][1][0] = 0;
		
	
		
	}
	public void printMap(int R, int C) {
		for(int x =0 ; x <R ; x++) {
			for(int y =0; y <C ;y++) {
				System.out.print(map[x][y][0] + " ");
			}
			System.out.println(" ");
		}
		System.out.println("-------------------------- ");
	}
	public void printMap2(int R, int C) {
		for(int x =0 ; x <R ; x++) {
			for(int y =0; y <C ;y++) {
				System.out.print(map[x][y][1] + " ");
			}
			System.out.println(" ");
		}
		System.out.println("-------------------------- ");
	}
}
