package tt;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Solution9 {
	//백준 17140 : 이차원 배열과 연산
	public int r;
	public int c;
	public int k;
	public ArrayList<ArrayList<Integer>> Amatrix;
	public int rSize = 3;
	public int cSize = 3;
	public void solution() throws IOException {
		Amatrix = new ArrayList<>();
		BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
		String[] r_c_k = br.readLine().split(" ");
		r = Integer.parseInt(r_c_k[0]);
		c = Integer.parseInt(r_c_k[1]);
		k = Integer.parseInt(r_c_k[2]);
		for(int i = 0 ; i<3 ;i ++) {
			String[] items = br.readLine().split(" ");
			ArrayList<Integer> row = new ArrayList<Integer>();
			Amatrix.add(row);
			Amatrix.get(i).add(Integer.parseInt(items[0]));
			Amatrix.get(i).add(Integer.parseInt(items[1]));
			Amatrix.get(i).add(Integer.parseInt(items[2]));
		}
		 printMatrix();
		for(int i = 0 ; i< 100 ; i++) {
			R();
			printMatrix();
			C();
		}
	}
	public void R() {
		if(!(rSize >= cSize))
			return;
		int tmpcSize = 0;
		for(int k = 0 ; k < Amatrix.size() ; k++){
			ArrayList<Integer> entry = Amatrix.get(k);
			TreeMap<Integer,Integer> tmap = new TreeMap<Integer,Integer>();
			for(int i = 0; i< entry.size() ; i++ ) {
				if(tmap.containsKey(entry.get(i))){
					int key = entry.get(i);
					int value = tmap.get(key) +1;
					tmap.remove(key);
					tmap.put(key, value);
				}else {
					tmap.put(entry.get(i), 1);
				}
			}
				
				Iterator<Integer> iter = tmap.descendingKeySet().iterator();
				int key = iter.next();
				int length = (int)(Math.log10(key) +1);
				int value = (int)(Math.pow(10, length))*tmap.get(key) + key;
				int[] tmpArray = new int[tmap.size()];
				tmpArray[0] = value;
				int idx = 1;
				//. 다 넣으면
				while(iter.hasNext()) {
					key = iter.next();
					tmpArray[idx] = (int)(Math.pow(10, length))*tmap.get(key) + key;
					idx++;
				}
				// 정렬
				Arrays.sort(tmpArray);
				ArrayList<Integer> next = new ArrayList<>();
				for(int j = 0; j < tmpArray.length ; j++) {
					next.add(tmpArray[j] % (int)(Math.pow(10, length)));
					next.add(tmpArray[j] / (int)(Math.pow(10, length)));
				}
				if(tmpArray.length >tmpcSize)
					tmpcSize = tmpArray.length;
				
				Amatrix.add(k, next);
				Amatrix.remove(k+1);
			}
		cSize = tmpcSize;
		
	}
	public void C() {
		if(!(cSize > rSize))
			return;
		for(int i = 0 ; i <cSize ; i++) {
			TreeMap<Integer,Integer> tmap = new TreeMap<Integer,Integer>();
			for(int j = 0; j<Amatrix.size() ; j++ ) {
				ArrayList<Integer> entry = Amatrix.get(j);
				if(entry.size() > i) {
					int key = Amatrix.get(j).get(i);
					if(tmap.containsKey(key)){
						int value = tmap.get(key) +1;
						tmap.remove(key);
						tmap.put(key, value);
					}else {
						tmap.put(entry.get(i), 1);
					}
				}
			}
			//
			Iterator<Integer> iter = tmap.descendingKeySet().iterator();
			int key = iter.next();
			int length = (int)(Math.log10(key) +1);
			int value = (int)(Math.pow(10, length))*tmap.get(key) + key;
			int[] tmpArray = new int[tmap.size()];
			tmpArray[0] = value;
			int idx = 1;
			//. 다 넣으면
			while(iter.hasNext()) {
				key = iter.next();
				tmpArray[idx] = (int)(Math.pow(10, length))*tmap.get(key) + key;
				idx++;
			}
			Arrays.sort(tmpArray);
			for(int j = 0 ; j< tmpArray.length ; j++) {
				if(Amatrix.size() > j) {
					
				}
				
			}
			
			
		}
		
	}
	public void printMatrix() {
		for(int i = 0 ; i <Amatrix.size() ; i++) {
			ArrayList<Integer> tmp = Amatrix.get(i);
			for(int j = 0 ; j < tmp.size(); j ++) {
				System.out.print(tmp.get(j) + " ");
			}
			System.out.println(" ");
		}
	}
}

