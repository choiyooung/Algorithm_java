package tt;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import java.util.StringTokenizer;
//마법사 상어와 토네이토  arrayList로 구현
public class Solution16 {
	public ArrayList<FireBall>[][] map;
	public int fireBallMass = 0;
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		map = new ArrayList[N+1][N+1];
		for(int i = 1; i <= N ;i++ ) {
			for(int j=1; j <= N ; j++) {
				map[i][j] = new ArrayList<>();
			}
		}
		for(int i = 0 ; i< M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			map[x][y].add(new FireBall(x,y,m,s,d));
			fireBallMass += m;
		}
		for(int i = 0 ; i<K ;i++) {
			moveFireall(N);
			splitFireBall(N);
		}
		System.out.println(fireBallMass);
				
		
		
	}
	private void splitFireBall(int n) {
		// TODO Auto-generated method stub
		for(int i =1 ; i<=n ; i++) {
			for(int j = 1; j<= n ; j++) {
				if(map[i][j].size() == 0 || map[i][j].size() == 1 ) continue;
				ArrayList<FireBall> fireBalls = map[i][j];
				int mass = 0;
				int direction = 0;
				int speed = 0;
				int fireballNum =fireBalls.size();
				for(int k = 0 ; k< fireballNum ; k++) {
					FireBall fireball = fireBalls.get(k);
					mass += fireball.mass;
					speed += fireball.speed;
					direction += fireball.direction %2;
				}
				map[i][j].clear();
				
				if(mass/5 == 0) {
					fireBallMass -= mass;
					continue;
				}
				
				int splitmass = mass/5;
				int splitspeed = speed/fireballNum;
				fireBallMass = fireBallMass - (mass - splitmass*4);
				
				for(int k = 0 ; k < 4 ; k ++) {
					if(direction == fireballNum || direction == 0) {
						map[i][j].add(new FireBall(i,j,splitmass,splitspeed,k*2));
					}else {
						map[i][j].add(new FireBall(i,j,splitmass,splitspeed,k*2+1));
					}
				}
			}
		}
		
	}
	private void moveFireall(int n) {
		// TODO Auto-generated method stub
		ArrayList<FireBall> tmp = new ArrayList<>();
		for(int i =1 ; i<=n ; i++) {
			for(int j = 1; j<= n ; j++) {
				if(map[i][j].size() == 0) continue;
				ArrayList<FireBall> fireBalls = map[i][j];
				for(int k = 0 ; k< fireBalls.size() ; k++) {
					FireBall fireball = fireBalls.get(k);
					if(fireball.direction == 7 || fireball.direction == 6 || fireball.direction == 5 )
						fireball.y -= fireball.speed % n;
					if(fireball.direction == 1 || fireball.direction == 2 || fireball.direction == 3 )
						fireball.y +=fireball.speed % n;
					if(fireball.direction == 7 || fireball.direction == 0 || fireball.direction == 1 )
						fireball.x -= fireball.speed % n;
					if(fireball.direction == 5 || fireball.direction == 4 || fireball.direction == 3 )
						fireball.x += fireball.speed % n;
					
					if(fireball.x <= 0) fireball.x = n + fireball.x;
					if(fireball.x > n) fireball.x = fireball.x- n;
					if(fireball.y <= 0) fireball.y = n + fireball.y;
					if(fireball.y > n) fireball.y = fireball.y- n;
					
					FireBall movedFireball = new FireBall(fireball);
					tmp.add(movedFireball);
				}
				map[i][j].clear();
			}
		}
		for(FireBall fireball : tmp) {
			map[fireball.x][fireball.y].add(new FireBall(fireball));
		}
		
	}
	class FireBall{
		public int x;
		public int y;
		public int mass;
		public int speed;
		public int direction;
		public FireBall(int nx,int ny, int nmass, int ns, int nd) {
			this.x = nx;
			this.y = ny;
			this.mass = nmass;
			this.speed = ns;
			this.direction = nd;
		}
		public FireBall(String[] items) {
			this.x = Integer.parseInt(items[0]);
			this.y = Integer.parseInt(items[1]);
			this.mass = Integer.parseInt(items[2]);
			this.speed = Integer.parseInt(items[3]);
			this.direction = Integer.parseInt(items[4]);
		}
		public FireBall(FireBall f) {
			this.x = f.x;
			this.y = f.y;
			this.mass = f.mass;
			this.speed = f.speed;
			this.direction = f.direction;
		}
	}
}

