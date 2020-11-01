package tt;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
//������ ���� ����̵� (Hashmap �ð��ʰ�)
public class Solution15 {
	public HashMap<Integer,ArrayList<FireBall>> map;
	public ArrayList<FireBall> array;
	public int fireBallMass = 0;
	public void solution() throws IOException {
		BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		array = new ArrayList<>();
		map = new HashMap<>();
		for(int i = 0 ; i< M; i++) {
			String[] items = br.readLine().split(" ");
			fireBallMass += Integer.parseInt(items[2]);
			array.add(new FireBall(items));
			int tmp2 = Integer.parseInt(items[0])*100 + Integer.parseInt(items[1]);
			map.put(tmp2, new ArrayList<>());
			map.get(tmp2).add(array.get(i));
		}
		for(int i = 0 ; i<K ;i++) {
			moveFireall(N);
			splitFireBall();
		}
		System.out.println(fireBallMass);
				
		
	}
	
	private void splitFireBall() {
			for(int entry : map.keySet() ) {
				ArrayList<FireBall> tmp = map.get(entry);
				if(tmp.size() == 1) continue;
				int x = entry/100;
				int y = entry%100;
				int mass = 0;
				int speed = 0;
				int direction = 0;
				int fireballNum = tmp.size();
				ArrayList<FireBall> removeList = new ArrayList<>();
				for(int j = 0 ; j < fireballNum ; j++) {
					direction +=  tmp.get(j).direction % 2;
					mass += tmp.get(j).mass;
					speed += tmp.get(j).speed;
					removeList.add(tmp.get(j));
				}
				array.removeAll(removeList);
				map.get(entry).clear();
				//fireBall ����
				if(mass/5 == 0) {
					fireBallMass -= mass;
					continue;
				}
			
				int splitmass = mass/5;
				int splitspeed = speed/fireballNum;
				fireBallMass = fireBallMass - (mass - splitmass*4);
				for(int j = 0; j< 4 ; j++  ) {
					//��� ¦�� Ȥ�� ��� Ȧ��
					if(direction == fireballNum || direction == 0) {
						array.add(new FireBall(x,y,splitmass,splitspeed , j*2));
					}else {
						array.add(new FireBall(x,y,splitmass,splitspeed , j*2+1));
					}
					map.get(entry).add(array.get(array.size()-1));
					
				}
				
				
			
		}
		
	}

	private void moveFireall(int N) {
		for(int i = 0 ; i < array.size() ; i++) {
			FireBall b = array.get(i);
			int tmp = b.x*100 + b.y;
			map.get(tmp).remove(b);
			if(map.get(tmp).size() == 0) map.remove(tmp);
			//x���̵�
			if(b.direction == 5 || b.direction == 6 ||  b.direction == 7)
				b.y -= b.speed%N;
			if(b.direction == 1 || b.direction == 2 ||  b.direction == 3)
				b.y += b.speed%N;
			//y���̵�
			if(b.direction == 7 || b.direction == 0 || b.direction == 1)
				b.x -= b.speed%N;
			if(b.direction == 5 || b.direction == 4 || b.direction == 3)
				b.x += b.speed%N;
			//x,y�� N�� �Ѿ�ų� �����ΰ�� ������Ʈ
			if(b.x <= 0) b.x = N + b.x;
			if(b.x > N) b.x = b.x- N;
			if(b.y <= 0) b.y = N + b.y;
			if(b.y > N) b.y = b.y- N;
			int tmp2 = b.x*100 + b.y;
			if(!map.containsKey(tmp2)) {
				map.put(tmp2, new ArrayList<>());
			}
			map.get(tmp2).add(b);
				
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
		
	}
}
