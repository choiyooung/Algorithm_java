package tt;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Solution11 {
	public int[][] map;
	public int[] sharckPostion;
	public int sharckSize = 2;
	public int[] dx = {-1,1, 0,0};
	public int[] dy = {0,0, 1,-1};
	public int stack = 0;
	public int time = 0;
	public void solution() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		sharckPostion = new int[2];
		// 맵과 상어 위치를 초기화
		for(int i = 0 ; i < N ; i++) {
			String[] line = br.readLine().split(" ");
			for(int j = 0 ; j<N ; j++) {
				int key = Integer.parseInt(line[j]);
				map[i][j] = key;
				if(key == 9) {
					sharckPostion[0] = i;
					sharckPostion[1] = j;
					continue;
				}
				
			}
		}
		while(true) {
			//bfs를 계속 돌리는데, 그전 시간과 차이가 없다면 끝냄
			int beforeTime = time;
			bfs(N);
			if(time == beforeTime)
				break;
		}
		System.out.println(time);
	}
	private void bfs(int N) {
		// TODO Auto-generated method stub
		boolean[][] visited = new boolean[N][N];
		Queue<int[]> queue = new LinkedList<>();
		Queue<int[]> tmpqueue = new LinkedList<>();
		int[] t = new int[3];
		t[0] = sharckPostion[0];
		t[1] = sharckPostion[1];
		t[2] = 0;
		queue.add(t);
		visited[t[0]][t[1]] = true;
		map[t[0]][t[1]] = 0;
		
		int minCnt = N*N;
		while(queue.size() > 0) {
			int[] postion = queue.poll();
			int x = postion[0];
			int y = postion[1];
			int cnt = postion[2];
			for(int i = 0; i< 4 ; i++) {
				//상하 좌우로 이동.
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
				if(visited[nx][ny]) continue;

				if(map[nx][ny] > sharckSize) continue; // 만약 물고기의 크기가 상어 크기보다 크면 이동할수 없음
				if(minCnt < cnt+1) break; //minCn가 cnt+1 보다 큰 경우는 이미 최소 경로 물고기를 찾은 경우 이므로 더이상 찾을 필요가 없음
				if(map[nx][ny] < sharckSize && map[nx][ny] > 0) { // 물고기의 크기기 상커 크기보다 작다면, 그 위치를 tmpqueue에 저장함.
					minCnt = cnt+1;
					int[] nPosition = {nx,ny,cnt+1};
					tmpqueue.add(nPosition);
				}
				int[] nPosition = {nx,ny,cnt+1};
				visited[nx][ny] = true;
				queue.offer(nPosition);
			}
		}
		//tmpqueue에 아무것도 없다면, 물고기를 먹지 못한거임. 아무것도 물고기를 먹지 못해끼 때문에 dfs를 끝내버림
		if(tmpqueue.size()  == 0)
			return;
		int min = 9999;
		while(tmpqueue.size() > 0) {
			int[] tPostion = tmpqueue.poll();
			// val의 값이 가장 작으면 가장 위쪽의 왼쪽을 찾게됨
			int val = tPostion[0]* 100 + tPostion[1];
			if(min > val)
				min = val;
		}
		//상어의 위치와 시간을 업데이트하고, 먹은 수도 업데이트함. 물고기 먹은 수가 상어 크기와 같아지면 상어크기를 늘리고, 먹은 수 초기화.
		int x = min /100;
		int y = min % 100;
		sharckPostion[0] = x;
		sharckPostion[1] = y;
		stack++;
		time += minCnt;
		if(stack == sharckSize) {
			sharckSize++;
			stack = 0;
		}
	}

}
