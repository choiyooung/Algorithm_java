package tt;

import java.util.ArrayList;
public class Solution2 {
	public String solution(String sentence) {
        String answer = "";
        int sLength = sentence.length();
        ArrayList<Character> cArray = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        if(sLength <=2) {
        	return "invalid";
        }
        for(int i = 0; i<sLength ; i++) {
        	//앞글자가 대문자인지, 소문자인지 확인
        	if(sentence.charAt(i) >= 'A' && sentence.charAt(i) <= 'Z') {
        		//대문자라면, 2번 규칙 적용
        		int wCount  = 0;
        		char abChar = ' ';
        		char tmpChar = 0;
        		int j;
        		for(j = i; j <sLength ; j++) {
        			tmpChar = sentence.charAt(j);
        			//공백문자면 invaild;
        			if(tmpChar == ' ')
        				return "invalid";
        			

        			if(wCount%2 == 0) { // wCount/2가 0이면, 글자파트
        				if(tmpChar >= 'A' && tmpChar <= 'Z' ) {
        					sb.append(tmpChar);
        				}else {
        					return "invalid";
        				}
        			}else { // wCount/2가 1이면, 광고 파트
        				//대문자가 아니라면
        				if(!(tmpChar >= 'A' && tmpChar <= 'Z') ) {
        					if(abChar == ' ') {
            					if(cArray.contains(tmpChar)) //전에 썻던 기호가 다시 쓰인경우라면, invaild;
            						return  "invalid";
            					
        						abChar = tmpChar;
            					
            				}else if (abChar != tmpChar) {
            					i = j-1;
            					sb.append(' ');
            					cArray.add(abChar);
            					break;
            				}
        				}else { //규칙 1번의 끝난 부분
        					if(abChar == ' ') {
        						for(int k = j ;j<sLength; k++) {
        							tmpChar = sentence.charAt(k);
        							if(tmpChar >= 'A' && tmpChar <= 'Z' ) {
        								sb.append(tmpChar);
        							}else {
        								sb.deleteCharAt(sb.length()-1);
        								sb.append(' ');
        	        					i = j-1;
        	        					break;
        							}
        						}
       
        					}else {
        						i = j-1;
            					sb.append(' ');
            					cArray.add(abChar);
            					break;
        						
        					}
        					
        				}
        				
        			}
        			wCount++;
        		}
        		if(j>=sLength) {
        			if(tmpChar >= 'A' && tmpChar <= 'Z'){
            			i = sLength;
            			sb.append(' ');
            		}else {
            			return "invaild";
            		}
        		}	
        	}
        	else { //소문자라면, 1번규칙 적용
        		char abChar = sentence.charAt(i);
        		int j;
        		if(cArray.contains(abChar)) //전에 썻던 기호가 다시 쓰인경우라면, invaild;
					return  "invalid";
        		
        		
        		for(j = i+1; j <sLength ; j++) {
        			char tmpChar = sentence.charAt(j);
        			if(tmpChar == ' ')
        				return "invalid";
        			
        			if(tmpChar >= 'A' && tmpChar <= 'Z') {
        				sb.append(tmpChar);
        			}else {
        				if(tmpChar == abChar) {
        					i= j;
        					sb.append(' ');
        					cArray.add(abChar);
        					break;
        				}else {
        					//2번규칙이 있는가?
        					int wCount  = 1;
        					char abChar2 = ' ';
        					if(cArray.contains(tmpChar)) //전에 썻던 기호가 다시 쓰인경우라면, invaild;
        						return  "invalid";
        					
        					for(int k = j ; k < sLength; k++) { // 1,2번규칙 둘다 적용
        						tmpChar = sentence.charAt(k);
        	        			//공백문자면 invaild;
        	        			if(tmpChar == ' ')
        	        				return "invalid";
        	        			

        	        			if(wCount%2 == 0) { // wCount/2가 0이면, 글자파트
        	        				if(tmpChar >= 'A' && tmpChar <= 'Z' ) {
        	        					sb.append(tmpChar);
        	        				}else {
        	        					return "invalid";
        	        				}
        	        			}else { // wCount/2가 1이면, 광고 파트
        	        				//대문자가 아니라면
        	        				if(!(tmpChar >= 'A' && tmpChar <= 'Z') ) {
        	        					if(abChar2 == ' ') {
        	            					if(cArray.contains(tmpChar) || abChar2 == abChar) //전에 썻던 기호가 다시 쓰인경우라면, invaild;
        	            						return  "invalid";
        	            					
        	        						abChar2 = tmpChar;
        	            					
        	            				}else if(tmpChar == abChar) {
        	            					j = k-1;
        	            					cArray.add(abChar2);
        	            					break;
        	            				}
        	        					else if (abChar2 != tmpChar) {
        	            					return "invalid";
        	            					
        	            				}
        	        				}else { //규칙 1번의 끝난 부분
        	        					j = k-1;
        	        					sb.append(' ');
        	        					cArray.add(abChar2);
        	        					break;
        	        				}
        	        				
        	        			}
        	        			wCount++;
        						
        					}
        				}
        			}
        		
        		}
        		if(j >= sLength)
        			return "invalid";
        	}
        }
        sb.deleteCharAt(sb.length()-1);
        answer = sb.toString();
        
        return answer;
    }
}
