package tt;

public class Solution5 {
	 public int b_size;
	 public int[][] solution(int n, int[][] build_frame) {
	        int[][] answer = {};
	        int[][] map= new int[build_frame.length +1][build_frame.length+1];
	        b_size = build_frame.length;
	        bfs(map,build_frame,0); 
	        return answer;
	        }
	 public void bfs(int[][] map, int[][] build_frame, int index){
		 int[] entry = build_frame[index];
		 int x = entry[0];
		 int y = entry[1];
		 int a = entry[2];
		 int b = entry[3];
		 
		 if( index == b_size ) {
			 return;
		 }
		 // 기둥
		 if(a == 0) {
			 //설제
			 if(b == 1) {
				 if(y == 0 || map[x][y] == 1 || map[x][y] == 2) {
					 map[x][y] += 1;
					 map[x][y+1] += 1; 
				 }
			 }else {
				 //삭제
				 if(y == 0 || map[x][y] == 3 || map[x][y] == 2)  {
					 map[x][y] -= 1;
					 map[x][y+1] -= 1;
					 bfs(map,build_frame, index+1);
				 }
			 }
		 }else {
		// 보
			 if(b == 1) {
				 if(map[x][y] == 1) {
					 map[x][y] += 3;
					 map[x+1][y] += 3; 
				 }else if(map[x][y] == 3 &&( map[x][y+1]== 3|| map[x][y+1]== 1)) {
					 map[x][y] += 3;
					 map[x+1][y] += 3; 
				 }
			 }else {
				 //삭제
				 if(map[x][y] == 4 && map[x][y] == 3)  {
					 if(map[x][y-1] == 1 || map[x][y-1] == 2) {
						 map[x][y] -= 3;
						 map[x+1][y+1] -= 3;
						 bfs(map,build_frame, index+1); 
					 }
				 }else if(map[x][y] == 6 && map[x][y+1] == 4) {
					 
				 }
			 }
			 
		 }

		 
		 return;
	 }

}
