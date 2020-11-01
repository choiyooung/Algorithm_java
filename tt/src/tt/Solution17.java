package tt;

import java.io.*;
import java.util.*;

public class Solution17 {
	//20058번 마법사 상어와 토네이도
	public int N;
	public int[][] map;
	public int totalSend = 0;
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for(int i = 0 ; i< N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		move();
		System.out.println(totalSend);
	}
	public void move() {
		int step = N/2;
		int x = N/2;
		int y = N/2;
		for(int i = 0; i <step ;i++) {
			//왼쪽
			for(int j = 0; j < i*2+1 ; j++) {
				calculate(x,--y,1);
				//printmap();
			}
			//아랫쪽
			for(int j = 0; j < i*2+1 ; j++) {
				calculate(++x,y,2);
				//printmap();
			
			}
			//오른쪽
			for(int j = 0; j < i*2+2 ; j++) {
				calculate(x,++y,3);
				//printmap();

			}
			//위로
			for(int j = 0; j < i*2+2 ; j++) {
				calculate(--x,y,4);
				//printmap();
		
			}
		}
		//마지막 오른쪽 N-1번 이동
		for(int j = 0; j < N-1 ; j++) {
			calculate(x,--y,1);
			//printmap();
		}
	}
	private void printmap() {
		for(int i = 0 ; i <N ; i++) {
			for(int j = 0 ; j< N ;j ++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println( " ");
		}
		System.out.println("________________________________________________");
	}
	private void calculate(int x, int y,int d) {
		int[] persent = {5,0, 10, 10, 0, 7,7,2,2};
		int nx = x;
		int ny = y;
		int send = map[x][y];
		int remain = send;
		int idx = 0;
		// TODO Auto-generated method stub
		if(d == 1) {
			int[] dx = { 0,-1,1,-2,2,3,-3};
			int[] dy = {-2,-1,0};
			
			for(int i = 0 ; i< dy.length ; i++) {
				ny = y + dy[i];
				for(int j = 0; j< i*2+1 ; j++) {
					nx = x + dx[j];
					int tmp = send*persent[idx]/100;
					idx++;
					if(nx < 0 || ny <0 || nx >=N || ny >= N) {
						totalSend += tmp;
						remain -= tmp;
						continue;
					}
					map[nx][ny] += tmp;
					remain -= tmp;
				}
			}
			//나머지 두개
			nx = x -1;
			ny = y+1;
			int tmp = send/100;
			if(nx < 0 || ny <0 || nx >=N || ny >= N) {
				totalSend += tmp;
				remain -= tmp;
			}else {
				map[nx][ny] +=  tmp;
				remain -= tmp;
			}
			nx = x+1;
			ny = y+1;
			if(nx < 0 || ny <0 || nx >=N || ny >= N) {
				totalSend += tmp;
				remain -= tmp;
			}else {
				map[nx][ny] +=  tmp;
				remain -= tmp;
			}
			nx = x;
			ny = y-1;
			if(nx < 0 || ny <0 || nx >=N || ny >= N) {
				totalSend += remain;
			}else {
				map[nx][ny] += remain;
			}	
			
		}else if(d == 2) { //아래방향
			int[] dx = {2,1,0};
			int[] dy = { 0,-1,1,-2,2,3,-3};
			for(int i = 0 ; i< dx.length ; i++) {
				nx = x + dx[i];
				for(int j = 0; j< i*2+1 ; j++) {
					ny = y + dy[j];
					int tmp = send*persent[idx]/100;
					idx++;
					if(nx < 0 || ny <0 || nx >=N || ny >= N) {
						totalSend += tmp;
						remain -= tmp;
						continue;
					}
					map[nx][ny] += tmp;
					remain -= tmp;
				}
			}
			//나머지 두개
			nx = x-1;
			ny = y-1;
			int tmp = send/100;
			if(nx < 0 || ny <0 || nx >=N || ny >= N) {
				totalSend += tmp;
				remain -= tmp;
			}else {
				map[nx][ny] +=  tmp;
				remain -= tmp;
			}
			nx = x-1;
			ny = y+1;
			if(nx < 0 || ny <0 || nx >=N || ny >= N) {
				totalSend += tmp;
				remain -= tmp;
			}else {
				map[nx][ny] +=  tmp;
				remain -= tmp;
			}
			nx = x+1;
			ny = y;
			if(nx < 0 || ny <0 || nx >=N || ny >= N) {
				totalSend += remain;
			}else {
				map[nx][ny] += remain;
			}		
		}else if(d == 3){ //오른쪽
			int[] dx = { 0,-1,1,-2,2,3,-3};
			int[] dy = {2,1,0};
			for(int i = 0 ; i< dy.length ; i++) {
				ny = y + dy[i];
				for(int j = 0; j< i*2+1 ; j++) {
					nx = x + dx[j];
					int tmp = send*persent[idx]/100;
					idx++;
					if(nx < 0 || ny <0 || nx >=N || ny >= N) {
						totalSend += tmp;
						remain -= tmp;
						continue;
					}
					map[nx][ny] += tmp;
					remain -= tmp;
				}
			}
			//나머지 두개
			nx = x -1;
			ny = y - 1;
			int tmp =send/100;
			if(nx < 0 || ny <0 || nx >=N || ny >= N) {
				totalSend += tmp;
				remain -= tmp;
			}else {
				map[nx][ny] +=  tmp;
				remain -= tmp;
			}
			nx = x+1;
			ny = y-1;
			if(nx < 0 || ny <0 || nx >=N || ny >= N) {
				totalSend += tmp;
				remain -= tmp;
			}else {
				map[nx][ny] +=  tmp;
				remain -= tmp;
			}
			nx = x;
			ny = y+1;
			if(nx < 0 || ny <0 || nx >=N || ny >= N) {
				totalSend += remain;
			}else {
				map[nx][ny] += remain;
			}	
			
		}else if(d == 4) { //위
			int[] dx = {-2,-1,0};
			int[] dy = { 0,-1,1,-2,2,3,-3};
			for(int i = 0 ; i< dx.length ; i++) {
				nx = x + dx[i];
				for(int j = 0; j< i*2+1 ; j++) {
					ny = y + dy[j];
					int tmp = send*persent[idx]/100;
					idx++;
					if(nx < 0 || ny <0 || nx >=N || ny >= N) {
						totalSend += tmp;
						remain -= tmp;
						continue;
					}
					map[nx][ny] += tmp;
					remain -= tmp;
				}
			}
			//나머지 두개
			nx = x+1;
			ny = y-1;
			int tmp = send/100;
			if(nx < 0 || ny <0 || nx >=N || ny >= N) {
				totalSend += tmp;
				remain -= tmp;
			}else {
				map[nx][ny] +=  tmp;
				remain -= tmp;
			}
			nx = x+1;
			ny = y+1;
			if(nx < 0 || ny <0 || nx >=N || ny >= N) {
				totalSend += tmp;
				remain -= tmp;
			}else {
				map[nx][ny] +=  tmp;
				remain -= tmp;
			}
			nx = x-1;
			ny = y;
			if(nx < 0 || ny <0 || nx >=N || ny >= N) {
				totalSend += remain;
			}else {
				map[nx][ny] += remain;
			}	
			
		}
		map[x][y] =0;
		
	}
}
