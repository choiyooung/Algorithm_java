package tt;

import java.util.HashSet;
import java.util.Map.Entry;
import java.util.HashMap;

//감시카메라
public class Solution1 {
	HashMap<Integer, Integer> xlineSet;
	HashMap<Integer, Integer> ylineSet;
	public Solution1() {
		xlineSet = new HashMap<>();
		ylineSet = new HashMap<>();
	}
	public void makeLine(String[] location) {
		int xLine = Integer.parseInt(location[0]);
		int yLine = Integer.parseInt(location[1]);
		
		
		if(xlineSet.containsKey(xLine)){
			int num = xlineSet.get(xLine);
			xlineSet.put(xLine,num+1);
		}else {
			xlineSet.put(xLine,1);
		}
		if(ylineSet.containsKey(yLine)){
			int num = ylineSet.get(yLine);
			ylineSet.put(yLine,num+1);
		}else {
			ylineSet.put(yLine,1);
		}
	}
	public int findThreeLine(int n) {
		// 3 : 0
		if(xlineSet.size() == 3) {
			return 1;
		}
		// 0 : 3
		if(ylineSet.size() == 3) {
			return 1;
		}
		// 2 : 1 or 1: 2
		int xFirst =0;
		int xSecond =0;
		
		for(Entry<Integer, Integer> entry : xlineSet.entrySet()) {
			int value = entry.getValue();
			if(value > xSecond) {
				if(value > xFirst) {
					xSecond = xFirst;
					xFirst = value;
				}
				else
					xSecond = value;
			}
		}
		int yFirst =0;
		int ySecond =0;
		
		for(Entry<Integer, Integer> entry : ylineSet.entrySet()) {
			int value = entry.getValue();
			if(value > ySecond) {
				if(value > yFirst)
				{
					ySecond = yFirst;
					yFirst = value;
				}
				else
					ySecond = value;
			}
		}
		int check;
		if(ySecond> xSecond) {
			check = ySecond + yFirst + xFirst;
		}else {
			check = xSecond + xFirst + yFirst;
		}
		if(check >= n+1) {
			return 1;
		}
		return 0;
		// 1 : 2
	}
}
