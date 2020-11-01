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
		// �ʰ� ��� ��ġ�� �ʱ�ȭ
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
			//bfs�� ��� �����µ�, ���� �ð��� ���̰� ���ٸ� ����
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
				//���� �¿�� �̵�.
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
				if(visited[nx][ny]) continue;

				if(map[nx][ny] > sharckSize) continue; // ���� ������� ũ�Ⱑ ��� ũ�⺸�� ũ�� �̵��Ҽ� ����
				if(minCnt < cnt+1) break; //minCn�� cnt+1 ���� ū ���� �̹� �ּ� ��� ����⸦ ã�� ��� �̹Ƿ� ���̻� ã�� �ʿ䰡 ����
				if(map[nx][ny] < sharckSize && map[nx][ny] > 0) { // ������� ũ��� ��Ŀ ũ�⺸�� �۴ٸ�, �� ��ġ�� tmpqueue�� ������.
					minCnt = cnt+1;
					int[] nPosition = {nx,ny,cnt+1};
					tmpqueue.add(nPosition);
				}
				int[] nPosition = {nx,ny,cnt+1};
				visited[nx][ny] = true;
				queue.offer(nPosition);
			}
		}
		//tmpqueue�� �ƹ��͵� ���ٸ�, ����⸦ ���� ���Ѱ���. �ƹ��͵� ����⸦ ���� ���س� ������ dfs�� ��������
		if(tmpqueue.size()  == 0)
			return;
		int min = 9999;
		while(tmpqueue.size() > 0) {
			int[] tPostion = tmpqueue.poll();
			// val�� ���� ���� ������ ���� ������ ������ ã�Ե�
			int val = tPostion[0]* 100 + tPostion[1];
			if(min > val)
				min = val;
		}
		//����� ��ġ�� �ð��� ������Ʈ�ϰ�, ���� ���� ������Ʈ��. ����� ���� ���� ��� ũ��� �������� ���ũ�⸦ �ø���, ���� �� �ʱ�ȭ.
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
