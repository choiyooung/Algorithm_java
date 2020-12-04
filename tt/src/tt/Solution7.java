package tt;

import java.util.LinkedList;
import java.util.Queue;

public class Solution7 {
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	static int N;
	public int solution(int[][] board) {
        int answer = bfs(board);
        return answer;
    }
	public int bfs(int[][] board) {
		N = board.length;
		boolean[][][] visited = new boolean[N][N][2];
		visited[0][1][0] = true;
        visited[0][0][0] = true;
		int[] init = {0,1,0,0,0,0};
		Queue<int[]> g = new LinkedList<>();
		g.offer(init);
		
		while(g.size() != 0) {
			int[] m = g.poll();
			int x1 = m[0];
			int y1 = m[1];
			int x2 = m[2];
			int y2 = m[3];
			int d = m[4];
			int cnt = m[5];
			if ((x1 == N-1 && y1==N-1) || (x2==N-1 && y2==N-1)) return cnt;
			//이동
			for(int i = 0 ; i < 4 ; i++) {
				int nx1 = x1 + dx[i];
                int ny1 = y1 + dy[i];
                int nx2 = x2 + dx[i];
                int ny2 = y2 + dy[i];
                if (nx1 <0 || nx2<0 ||ny1<0 || ny2<0 || nx1>=N || nx2>= N || ny1>=N || ny2>=N) continue;
                if (visited[nx1][ny1][d]&&visited[nx2][ny2][d]) continue;
                if (board[nx1][ny1] != 0 || board[nx2][ny2] != 0) continue;
                int[] t = {nx1,ny1,nx2,ny2,d,cnt+1};
                visited[nx1][ny1][d] = true;
                visited[nx2][ny2][d] = true;
                g.offer(t);
			}
			//회전
			
			if(d == 0) {
				//1축 회전
				int nx = x1 + 1;
				int ny = y1;
				int nd = 1;
				if(boundCheck(nx,ny)) {
					if(ny!= 0 && board[nx][ny] == 0 && board[nx][ny-1] == 0 && !visited[nx][ny][nd] && !visited[x1][y1][nd]) {
						visited[x1][y1][nd] = true;
						visited[nx][ny][nd] = true;
						int[] nt = {nx,ny, x1,y1,nd,cnt +1};
						g.offer(nt);
					}
				}
				nx = x1 -1;
				ny = y1;
				if(boundCheck(nx,ny)) {
					if(ny!= 0 && board[nx][ny] == 0 && board[nx][ny-1] == 0 && !visited[nx][ny][nd] && !visited[x1][y1][nd]) {
						visited[x1][y1][nd] = true;
						visited[nx][ny][nd] = true;
						int[] nt = {x1,y1,nx,ny,nd,cnt +1};
						g.offer(nt);
					}
					
				}
				//2축 회전
				nx = x2 +1;
				ny = y2;
				if(boundCheck(nx,ny)) {
					if(ny!= N-1 && board[nx][ny] == 0 && board[nx][ny+1] == 0 && !visited[nx][ny][nd] && !visited[x2][y2][nd]) {
						visited[x2][y2][nd] = true;
						visited[nx][ny][nd] = true;
						int[] nt = {nx,ny,x2,y2,nd,cnt +1};
						g.offer(nt);
					}
					
				}
				nx = x2 -1;
				ny = y2;
				if(boundCheck(nx,ny)) {
					if(ny!= N-1 && board[nx][ny] == 0 &&board[nx][ny+1]== 0 && !visited[nx][ny][nd] && !visited[x2][y2][nd]) {
						visited[x2][y2][nd] = true;
						visited[nx][ny][nd] = true;
						int[] nt = {x2,y2,nx,ny,nd,cnt +1};
						g.offer(nt);
					}
				}
			}else {
				//1축회전
				int nx = x1;
				int ny = y1+1;
				int nd = 0;
				if(boundCheck(nx,ny)) {
					if(nx!= 0 && board[nx][ny] == 0 && board[nx-1][ny] == 0 && !visited[nx][ny][nd] && !visited[x1][y1][nd]) {
						visited[x1][y1][nd] = true;
						visited[nx][ny][nd] = true;
						int[] nt = {nx,ny, x1,y1,nd,cnt +1};
						g.offer(nt);
					}
				}
				nx = x1;
				ny = y1-1;
				if(boundCheck(nx,ny)) {
					if(nx!= 0 && board[nx][ny] == 0 && board[nx-1][ny] == 0 && !visited[nx][ny][nd] && !visited[x1][y1][nd]) {
						visited[x1][y1][nd] = true;
						visited[nx][ny][nd] = true;
						int[] nt = {x1,y1, nx,ny,nd,cnt +1};
						g.offer(nt);
					}
				}
				nx = x2;
				ny = y2+1;
				if(boundCheck(nx,ny)) {
					if(nx!= N-1 && board[nx][ny] == 0 && board[nx+1][ny]== 0 && !visited[nx][ny][nd] && !visited[x2][y2][nd]) {
						visited[x2][y2][nd] = true;
						visited[nx][ny][nd] = true;
						int[] nt = {nx,ny, x2,y2,nd,cnt +1};
						g.offer(nt);
					}
				}
				nx = x2;
				ny = y2-1;
				if(boundCheck(nx,ny)) {
					if(nx!= N-1 && board[nx][ny] == 0 && board[nx+1][ny] == 0 && !visited[nx][ny][nd] && !visited[x2][y2][nd]) {
						visited[x2][y2][nd] = true;
						visited[nx][ny][nd] = true;
						int[] nt = {x2,y2, nx,ny,nd,cnt +1};
						g.offer(nt);
					}
				}
			}
			
		}
		return 0;
	}
	private boolean boundCheck(int nx, int ny) {
        if (nx<0 || ny<0 || nx>=N || ny>=N) {
            return false;
        }
        return true;
    }
	
	
	
	
	
}
