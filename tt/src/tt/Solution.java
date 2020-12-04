package tt;

import java.util.HashSet;
import java.util.Iterator;

public class Solution {
	public static  HashSet<String> subset;
	public static HashSet<String> remove;
    public int solution(String[][] relation) {
    	subset = new HashSet<String>();
    	remove = new HashSet<String>();
        int answer = 0;
        int columNum = relation[0].length;
        int[] arr = new int[columNum];
        boolean[] visited = new boolean[columNum];
        for(int i = 0; i<columNum; i++) {
        	arr[i] = i;
    	}
        powerSet(arr, visited, columNum, 0);
        for(int i= 0; i < columNum ; i++) {
        	 for(String element: subset) {
        		 if(element.length() == i+1) {
        			 // i+1 column의 subset을 검사
        			 
        			 if(checkUniqueness(element,relation)) {
        				 answer++;
        				 System.out.println("Key : " + element);
        				 removeSubeset(element);
        				 
        			 }
        			 
        		 }
        	 }
        	 subset.removeAll(remove);
        	 remove.clear();
        }
			
      
        
  
     
        return answer;
    }
    private void removeSubeset(String element) {
		// TODO Auto-generated method stub
    	int elLength = element.length();
    	
    	
    	for(String e : subset) {
    		boolean check = false;
    		for(int i = 0 ; i < elLength ; i ++) {
    			if(!e.contains(element.substring(i, i+1)))
    				check = true;
        	}
    		if(check == false) {
    			System.out.println("remove Element : " + e);
    			remove.add(e);
    		}
		}

	}
	public void powerSet(int[] arr, boolean[] visited, int n, int idx) {
        if(idx == n) {
        	String tmp = new String();
        	for(int i = 0; i < n ; i++) {
        		if(visited[i]) {
        			tmp += i+"";
        		}
        	}
        	if(!tmp.equals("")) {
        		subset.add(tmp);
        	}
  
            return;
        }
     
        visited[idx] = false;
        powerSet(arr, visited, n, idx + 1);
     
        visited[idx] = true;
        powerSet(arr, visited, n, idx + 1);
    }
    public boolean checkUniqueness(String element,String[][] relation) {
    	int rowLength = relation.length;
    	HashSet<String> subRelation = new HashSet<>();
    	
    	for(int rowIdx = 0 ; rowIdx < rowLength ; rowIdx ++) {
    		String tmpString = new String();
    		for(int j = 0; j< element.length(); j ++){
    			int columIdx = Integer.parseInt(element.substring(j,j+1));
    			
    			tmpString += relation[rowIdx][columIdx];  
    		}
    		subRelation.add(tmpString);
    	}
    	if(subRelation.size() == rowLength)
    		return true;
    	return false;
    }
    public void checkMinimality() {
    	
    }
    
}
